package fr.benoitne.libraryapi.facade.controller;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import fr.benoitne.libraryapi.persistence.entity.BookEntity;
import fr.benoitne.libraryapi.persistence.entity.ReservationRequestEntity;
import fr.benoitne.libraryapi.persistence.repository.ReservationRequestRepository;
import fr.benoitne.libraryapi.service.LoanDateManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.benoitne.library.dto.BookDTO;
import fr.benoitne.libraryapi.facade.assembler.BookDTOAssembler;
import fr.benoitne.libraryapi.persistence.repository.BookRepository;

@Controller
@RequestMapping(value = "/")
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookDTOAssembler bookDTOAssembler;

	@Autowired
	LoanDateManagementService loanDateManagementService;

	@Autowired
	ReservationRequestRepository reservationRequestRepository;

	@RequestMapping(method = RequestMethod.GET, path = "/book")
	@ResponseBody
	public Stream<BookDTO> getBooks() {
		return StreamSupport.stream(bookRepository.findAll().spliterator(), false)
				.map(bookEntity -> bookDTOAssembler.convertToDTO(bookEntity));
	}

	@RequestMapping(method = RequestMethod.GET, path = "/book/search")
	@ResponseBody
	public Stream<BookDTO> booksSearch(String search) {
		return StreamSupport.stream(bookRepository.search(search).spliterator(), false)
				.map(bookEntity -> bookDTOAssembler.convertToDTO(bookEntity));
	}

	@RequestMapping(method = RequestMethod.POST, path = "/book/48hwaiting")
	@ResponseBody
	public String waitingLine48HInit (long bookId){
		Optional<BookEntity> bookEntityOptional = bookRepository.findById(bookId);
		BookEntity bookEntity = bookEntityOptional.get();
		bookEntity.setStatus("en attente 48h");
		bookEntity.setWaiting48HDate(loanDateManagementService.get48HWaitingDate());
		bookRepository.save(bookEntity);

		return "waitingLine générée.";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/book/48hwaiting/remove")
	@ResponseBody
	public String waitingLine48HRemove (long bookId){
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

		return "waitingLine supprimée.";
	}

}
