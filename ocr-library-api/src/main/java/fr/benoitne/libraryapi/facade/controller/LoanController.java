package fr.benoitne.libraryapi.facade.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.benoitne.library.dto.LoanDTO;
import fr.benoitne.libraryapi.facade.assembler.LoanDTOAssembler;
import fr.benoitne.libraryapi.persistence.entity.BookEntity;
import fr.benoitne.libraryapi.persistence.entity.LoanEntity;
import fr.benoitne.libraryapi.persistence.entity.UserEntity;
import fr.benoitne.libraryapi.persistence.repository.BookRepository;
import fr.benoitne.libraryapi.persistence.repository.LoanRepository;
import fr.benoitne.libraryapi.persistence.repository.UserRepository;
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
	private LoanDTOAssembler loanDTOAssembler;

	@Autowired
	private SetLoanStatus setLoanStatus;

	@Autowired
	LoanDateManagement loanDateManagement;
	
	
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
		Optional<BookEntity> optBookEntity = bookRepository.findById(bookId);
		Optional<UserEntity> optUserEntity = userRepository.findById(userId);
		BookEntity bookEntity = optBookEntity.get();
		UserEntity userEntity = optUserEntity.get();
		List<String> userWaitingLine = bookEntity.getUserWaitingLine();

		if (optBookEntity.isPresent() && optUserEntity.isPresent()) {
			List<LoanEntity> loanEntities = (List<LoanEntity>) loanRepository.findAll();
			loanEntity.setId(loanEntities.size() + 1);
			loanEntity.setStartBorrowingDate(loanDateManagement.getStartBorrowingDate());
			loanEntity.setEndBorrowingDate(loanDateManagement.getEndBorrowingDate());
			loanEntity.setBookEntity(bookEntity);
			loanEntity.setUserEntity(userEntity);
			setLoanStatus.initialStatus(loanEntity);

			if (bookEntity.getQuantity()<=0){
				userWaitingLine.add(userEntity.getUserName());
				bookEntity.setUserWaitingLine(userWaitingLine);
				bookEntity.setStatus("indisponible");
			}

			bookEntity.setQuantity(bookEntity.getQuantity() - 1);

			bookRepository.save(bookEntity);
			loanRepository.save(loanEntity);

			return loanEntity;
		} else {
			return null;
		}
	}

	@RequestMapping(method = RequestMethod.POST, path = "/loan/return")
	@ResponseBody
	public LoanDTO loanReturn(long loanId) {
		Optional<LoanEntity> optLoanEntity = loanRepository.findById(loanId);
		if (optLoanEntity.isPresent()) {
			LoanEntity loanEntity = optLoanEntity.get();
			BookEntity bookEntity = loanEntity.getBookEntity();
			bookEntity.setQuantity(bookEntity.getQuantity() + 1);
			setLoanStatus.finalStatus(loanEntity);
			bookRepository.save(bookEntity);
			loanRepository.save(loanEntity);
			return loanDTOAssembler.convertToDTO(loanEntity);
		} else {
			return null;
		}
	}


}
