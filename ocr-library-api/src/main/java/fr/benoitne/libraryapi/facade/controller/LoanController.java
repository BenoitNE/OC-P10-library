package fr.benoitne.libraryapi.facade.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import fr.benoitne.libraryapi.exeption.ResourceNotFoundException;
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
import fr.benoitne.libraryapi.service.LoanDateManagementService;
import fr.benoitne.libraryapi.service.SetLoanStatusService;

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
	private SetLoanStatusService setLoanStatusService;

	@Autowired
	LoanDateManagementService loanDateManagementService;

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

		Optional<LoanDTO> loanEntity1 = getLoanDTOWhenEndBorrowingDateIsPassed(loanEntity);
		if (loanEntity1 != null) return loanEntity1;

		return getLoanDTOWhenEndBorrowingDateIsNotPassed(loanEntity);
	}

	private Optional<LoanDTO> getLoanDTOWhenEndBorrowingDateIsNotPassed(Optional<LoanEntity> loanEntity) {
		if ((loanEntity.map(x -> x.getStatus().equals(setLoanStatusService.loanInProgress()))) != null
		&&!loanDateManagementService.dateListEndBorrowingDateIsPassed(loanEntity.get())) {
			loanEntity.map(x -> loanDateManagementService.setLoanProlongationDate(loanEntity));
			loanEntity.map(x -> setLoanStatusService.prolongationStatus(loanEntity));
			loanEntity.ifPresent(x -> loanRepository.save(x));
			return loanEntity.map(x -> loanDTOAssembler.convertToDTO(x));
		} else
			return null;
	}

	private Optional<LoanDTO> getLoanDTOWhenEndBorrowingDateIsPassed(Optional<LoanEntity> loanEntity) {
		if ((loanEntity.map(x -> x.getStatus().equals(setLoanStatusService.loanInProgress()))) != null
		&&loanDateManagementService.dateListEndBorrowingDateIsPassed(loanEntity.get())){
			loanEntity.map(x-> loanDateManagementService
					.setLoanProlongationDateWhenEndBorrowingDateIsPassed(loanEntity));
			loanEntity.map(x -> setLoanStatusService.prolongationStatus(loanEntity));
			loanEntity.ifPresent(x -> loanRepository.save(x));
			return loanEntity.map(x -> loanDTOAssembler.convertToDTO(x));
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/loan/add")
	@ResponseBody
	public LoanDTO newLoan(long userId, long bookId) {

			LoanEntity loanEntity = add(userId, bookId);
		return loanDTOAssembler.convertToDTO(loanEntity);

	}

	private LoanEntity add(long userId, long bookId) {
		try {
			LoanEntity loanEntity = new LoanEntity();
			ReservationRequestEntity reservationRequestEntity = new ReservationRequestEntity();
			Optional<BookEntity> optBookEntity = bookRepository.findById(bookId);
			Optional<UserEntity> optUserEntity = userRepository.findById(userId);
			BookEntity bookEntity = optBookEntity.get();
			UserEntity userEntity = optUserEntity.get();
			List<LoanEntity> loanEntityList = bookEntity.getLoanEntity();
			List<String> userWaitingLine = bookEntity.getUserWaitingLine();
			List<ReservationRequestEntity> reservationRequestEntities = bookEntity.getReservationRequestEntities();
			List<String> bookTitles = new ArrayList<>();

			if (!userEntity.getLoanEntity().isEmpty())
				for (LoanEntity loan : userEntity.getLoanEntity()) {
					bookTitles.add(loan.getBookEntity().getTitle());
				}

			if (!bookTitles.contains(bookEntity.getTitle())) {

				if (optBookEntity.isPresent() && optUserEntity.isPresent()) {
					List<LoanEntity> loanEntities = (List<LoanEntity>) loanRepository.findAll();
					loanEntity.setId(loanEntities.size() + 1);
					loanEntity.setStartBorrowingDate(loanDateManagementService.getStartBorrowingDate());
					loanEntity.setEndBorrowingDate(loanDateManagementService.getEndBorrowingDate());
					loanEntity.setBookEntity(bookEntity);
					loanEntity.setUserEntity(userEntity);
					setLoanStatusService.initialStatus(loanEntity);


					if ((bookEntity.getQuantity()) - (loanEntityList.size()) < 1) {
						userWaitingLine.add(userEntity.getUserName());
						bookEntity.setUserWaitingLine(userWaitingLine);
						reservationRequestEntity.setId(reservationRequestEntities.size() + 1);
						reservationRequestEntity.setStatus("Demande de réservation en cours");
						reservationRequestEntity.setBookEntity(bookEntity);
						reservationRequestEntity.setUserEntity(userEntity);
						reservationRequestEntity.setStartingDate(loanDateManagementService.getStartBorrowingDate());
						reservationRequestRepository.save(reservationRequestEntity);

					}

					if ((bookEntity.getQuantity()) - (loanEntityList.size()) <= 1) {
						bookEntity.setStatus("indisponible");
					}
				}

				bookRepository.save(bookEntity);

				if (bookEntity.getUserWaitingLine().isEmpty())
					loanRepository.save(loanEntity);
			}
			return loanEntity;
		} catch (Exception e){
			return null;
		}
	}

	@RequestMapping(method = RequestMethod.POST, path = "/loan/return")
	@ResponseBody
	public String loanReturn (long loanId) {

		try {
			Optional<LoanEntity> optLoanEntity = loanRepository.findById(loanId);
			LoanArchiveEntityBuilder archiveBuilder = new LoanArchiveEntityBuilder();
			LoanEntity loanEntity = optLoanEntity.get();
			BookEntity bookEntity = loanEntity.getBookEntity();
			UserEntity userEntity = loanEntity.getUserEntity();
			LoanArchiveEntity loanArchiveEntity = archiveBuilder.getLoanArchiveEntity(loanEntity);

			if (!bookEntity.getUserWaitingLine().isEmpty()) {
				loanArchiveRepository.save(loanArchiveEntity);
				bookEntity.setStatus("en attente NC");

				if (loanEntity.getWaiting48HDate() != null) {
					bookEntity.getUserWaitingLine().remove(0);
				}
			}

			if (bookEntity.getUserWaitingLine().isEmpty()) {
				loanArchiveRepository.save(loanArchiveEntity);
				bookEntity.setStatus("disponible");
			}
			bookRepository.save(bookEntity);
			loanRepository.delete(loanEntity);

			return "Le livre à bien été retourné.";

		} catch (Exception e) {
			return "Le livre ne peut pas être rendu.";
		}

	}
}
