package fr.benoitne.libraryapi.facade.controller;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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

}
