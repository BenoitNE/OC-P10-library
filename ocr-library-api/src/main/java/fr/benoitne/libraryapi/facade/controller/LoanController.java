package fr.benoitne.libraryapi.facade.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import fr.benoitne.libraryapi.facade.assembler.LoanArchiveEntityBuilder;
import fr.benoitne.libraryapi.persistence.entity.*;
import fr.benoitne.libraryapi.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.benoitne.library.dto.LoanDTO;
import fr.benoitne.libraryapi.facade.assembler.LoanDTOAssembler;
import fr.benoitne.libraryapi.service.LoanDateManagement;
import fr.benoitne.libraryapi.service.SetLoanStatus;

@Controller
@RequestMapping(value = "/")
public class LoanController {

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private LoanArchiveRepository loanArchiveRepository;

	@Autowired
	private LoanDTOAssembler loanDTOAssembler;

	@Autowired
	private SetLoanStatus setLoanStatus;

	@Autowired
	LoanDateManagement loanDateManagement;

	@Autowired
	ReservationRequestRepository reservationRequestRepository;
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/loan")
	@ResponseBody
	public Stream<LoanDTO> allLoans(){
		return StreamSupport.stream(loanRepository.findAll().spliterator(), false)
				.map(loanEntity -> loanDTOAssembler.convertToDTO(loanEntity));
	}

	@RequestMapping(method = RequestMethod.GET, path = "/user/loan")
	@ResponseBody
	public Stream<LoanDTO> listByUser(String userName) {
		UserEntity userEntity = userRepository.getByUserName(userName);
		return StreamSupport.stream(userEntity.getLoanEntity().spliterator(), false)
				.map(loanEntity -> loanDTOAssembler.convertToDTO(loanEntity));
	}

	@RequestMapping(method = RequestMethod.GET, path = "/loan/{loanId}/extendDate")
	@ResponseBody
	public Optional<LoanDTO> extendDate(@PathVariable(value = "loanId") long id) {
		Optional<LoanEntity> loanEntity = loanRepository.findById(id);
		if ((loanEntity.map(x -> x.getStatus().equals(setLoanStatus.loanInProgress()))) != null) {
			loanEntity.map(x -> loanDateManagement.setLoanProlongationDate(loanEntity));
			loanEntity.map(x -> setLoanStatus.prolongationStatus(loanEntity));
			loanEntity.ifPresent(x -> loanRepository.save(x));
			return loanEntity.map(x -> loanDTOAssembler.convertToDTO(x));
		} else
			return null;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/loan/add")
	@ResponseBody
	public LoanDTO newLoan(long userId, long bookId) {
		LoanEntity loanEntity = add(userId, bookId);
		return loanDTOAssembler.convertToDTO(loanEntity);
	}

	private LoanEntity add(long userId, long bookId) {
		LoanEntity loanEntity = new LoanEntity();
		ReservationRequestEntity reservationRequestEntity = new ReservationRequestEntity();
		Optional<BookEntity> optBookEntity = bookRepository.findById(bookId);
		Optional<UserEntity> optUserEntity = userRepository.findById(userId);
		BookEntity bookEntity = optBookEntity.get();
		UserEntity userEntity = optUserEntity.get();
		List<LoanEntity> loanEntityList = bookEntity.getLoanEntity();
		List<String> userWaitingLine = bookEntity.getUserWaitingLine();
		List<ReservationRequestEntity> reservationRequestEntities = bookEntity.getReservationRequestEntities();

		if (optBookEntity.isPresent() && optUserEntity.isPresent()) {
			List<LoanEntity> loanEntities = (List<LoanEntity>) loanRepository.findAll();
			loanEntity.setId(loanEntities.size() + 1);
			loanEntity.setStartBorrowingDate(loanDateManagement.getStartBorrowingDate());
			loanEntity.setEndBorrowingDate(loanDateManagement.getEndBorrowingDate());
			loanEntity.setBookEntity(bookEntity);
			loanEntity.setUserEntity(userEntity);
			setLoanStatus.initialStatus(loanEntity);


			if ((bookEntity.getQuantity()) - (loanEntityList.size()) < 1) {
				userWaitingLine.add(userEntity.getUserName());
				bookEntity.setUserWaitingLine(userWaitingLine);
				reservationRequestEntity.setId(reservationRequestEntities.size()+1);
				reservationRequestEntity.setStatus("Demande de réservation en cours");
				reservationRequestEntity.setBookEntity(bookEntity);
				reservationRequestEntity.setUserEntity(userEntity);
				reservationRequestEntity.setStartingDate(loanDateManagement.getStartBorrowingDate());
				reservationRequestRepository.save(reservationRequestEntity);

			}

			if ((bookEntity.getQuantity()) - (loanEntityList.size()) <= 1) {
				bookEntity.setStatus("indisponible");
			}
		}
			bookRepository.save(bookEntity);


		if(bookEntity.getUserWaitingLine().isEmpty())
			loanRepository.save(loanEntity);

			return loanEntity;
		}

	@RequestMapping(method = RequestMethod.POST, path = "/loan/return")
	@ResponseBody
	public void loanReturn (long loanId){
		Optional<LoanEntity> optLoanEntity = loanRepository.findById(loanId);
		LoanArchiveEntityBuilder archiveBuilder = new LoanArchiveEntityBuilder();
		LoanEntity loanEntity = optLoanEntity.get();
		BookEntity bookEntity = loanEntity.getBookEntity();
		UserEntity userEntity = loanEntity.getUserEntity();
		LoanArchiveEntity loanArchiveEntity = archiveBuilder.getLoanArchiveEntity(loanEntity);

		if (!bookEntity.getUserWaitingLine().isEmpty()){
			loanArchiveRepository.save(loanArchiveEntity);
			bookEntity.setStatus("en attente NC");

			if (loanEntity.getWaiting48HDate()!=null){
				bookEntity.getUserWaitingLine().remove(0);
			}
		}

		if (bookEntity.getUserWaitingLine().isEmpty()){
			loanArchiveRepository.save(loanArchiveEntity);
			bookEntity.setStatus("disponible");
		}
		bookRepository.save(bookEntity);
		loanRepository.delete(loanEntity);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/loan/48hwaiting")
	@ResponseBody
	public void waitingLine48HInit (long bookId){
		Optional<BookEntity> bookEntityOptional = bookRepository.findById(bookId);
		BookEntity bookEntity = bookEntityOptional.get();
		bookEntity.setStatus("en attente 48h");
		bookEntity.setWaiting48HDate(loanDateManagement.get48HWaitingDate());
		bookRepository.save(bookEntity);
		}

	@RequestMapping(method = RequestMethod.POST, path = "/loan/48hwaiting/remove")
	@ResponseBody
	public void waitingLine48HRemove (long bookId){
		Optional<BookEntity> bookEntityOptional = bookRepository.findById(bookId);
		BookEntity bookEntity = bookEntityOptional.get();
		ReservationRequestEntity reservationRequestEntity = bookEntity.getReservationRequestEntities().get(0);
		bookEntity.getUserWaitingLine().remove(0);


		if (!bookEntity.getUserWaitingLine().isEmpty()){
			bookEntity.setStatus("en attente NC");
		}
		if (bookEntity.getUserWaitingLine().isEmpty()){
			bookEntity.setStatus("disponible");
		}


		bookRepository.save(bookEntity);
		reservationRequestRepository.delete(reservationRequestEntity);

	}

}
