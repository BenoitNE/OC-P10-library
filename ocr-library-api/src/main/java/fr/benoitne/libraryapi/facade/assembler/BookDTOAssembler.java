package fr.benoitne.libraryapi.facade.assembler;


import fr.benoitne.libraryapi.persistence.entity.LoanEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import fr.benoitne.library.dto.BookDTO;
import fr.benoitne.libraryapi.persistence.entity.BookEntity;

import java.util.ArrayList;
import java.util.List;


@Component
public class BookDTOAssembler {

	@Autowired
	private LibraryDTOAssembler libraryDTOAssembler;

	public BookDTO convertToDTO(BookEntity bookEntity) {
		BookDTO bookDTO = new BookDTO();
		bookDTO.setId(bookEntity.getId());
		bookDTO.setAuthor(bookEntity.getAuthor());
		bookDTO.setTitle(bookEntity.getTitle());
		bookDTO.setPageNumber(bookEntity.getPageNumber());
		bookDTO.setType(bookEntity.getType());
		bookDTO.setPublishing(bookEntity.getPublishing());
		bookDTO.setStatus(bookEntity.getStatus());
		bookDTO.setImageLink(bookEntity.getImageLink());
		bookDTO.setQuantity(bookEntity.getQuantity());
		bookDTO.setSummary(bookEntity.getSummary());
		bookDTO.setLibraryDTO(libraryDTOAssembler.convertToDTO(bookEntity.getLibraryEntity()));
		bookDTO.setUserWaitingLine(bookEntity.getUserWaitingLine());
		bookDTO.setUserLoanList(getUserLoanList(bookEntity.getLoanEntity()));
		return bookDTO;
	}

	private List<String> getUserLoanList (List<LoanEntity>loanEntities){
		List<String> userList =new ArrayList<>();
		for (LoanEntity loanEntity:loanEntities){
			userList.add(loanEntity.getUserEntity().getUserName());
		}
		return userList;
	}

	public BookEntity convertToEntity(BookDTO bookDTO) {
		BookEntity bookEntity = new BookEntity();
		bookEntity.setId(bookDTO.getId());
		bookEntity.setAuthor(bookDTO.getAuthor());
		bookEntity.setTitle(bookDTO.getTitle());
		bookEntity.setPageNumber(bookDTO.getPageNumber());
		bookEntity.setType(bookDTO.getType());
		bookEntity.setPublishing(bookDTO.getPublishing());
		bookEntity.setStatus(bookDTO.getStatus());
		bookEntity.setImageLink(bookDTO.getImageLink());
		bookEntity.setQuantity(bookDTO.getQuantity());
		bookEntity.setSummary(bookDTO.getSummary());
		bookEntity.setLibraryEntity(libraryDTOAssembler.convertToEntity(bookDTO.getLibraryDTO()));
		bookEntity.setUserWaitingLine(bookDTO.getUserWaitingLine());
		return bookEntity;
	}

}
