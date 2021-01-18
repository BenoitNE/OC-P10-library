package fr.benoitne.libraryapi.facade.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.benoitne.library.dto.LoanDTO;
import fr.benoitne.libraryapi.persistence.entity.LoanEntity;

@Component
public class LoanDTOAssembler {

	@Autowired
	UserDTOAssembler userDTOAssembler;

	@Autowired
	BookDTOAssembler bookDTOAssembler;

	public LoanDTO convertToDTO(LoanEntity loanEntity) {
		LoanDTO loanDTO = new LoanDTO();
		loanDTO.setId(loanEntity.getId());
		loanDTO.setStatus(loanEntity.getStatus());
		loanDTO.setStartBorrowingDate(loanEntity.getStartBorrowingDate());
		loanDTO.setEndBorrowingDate(loanEntity.getEndBorrowingDate());
		loanDTO.setProlongationDate(loanEntity.getProlongationDate());
		loanDTO.setUserDTO(userDTOAssembler.convertToDTO(loanEntity.getUserEntity()));
		loanDTO.setBookDTO(bookDTOAssembler.convertToDTO(loanEntity.getBookEntity()));
		loanDTO.setWaiting48HDate(loanEntity.getWaiting48HDate());
		return loanDTO;
	}

	public LoanEntity convertToEntity(LoanDTO loanDTO) {
		LoanEntity loanEntity = new LoanEntity();
		loanEntity.setId(loanDTO.getId());
		loanEntity.setStatus(loanDTO.getStatus());
		loanEntity.setStartBorrowingDate(loanDTO.getStartBorrowingDate());
		loanEntity.setEndBorrowingDate(loanDTO.getEndBorrowingDate());
		loanEntity.setProlongationDate(loanDTO.getProlongationDate());
		loanEntity.setUserEntity(userDTOAssembler.convertToEntity(loanDTO.getUserDTO()));
		loanEntity.setBookEntity(bookDTOAssembler.convertToEntity(loanDTO.getBookDTO()));
		loanEntity.setWaiting48HDate(loanDTO.getWaiting48HDate());
		return loanEntity;
	}

}
