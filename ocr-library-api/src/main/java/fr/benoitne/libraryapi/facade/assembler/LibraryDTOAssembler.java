package fr.benoitne.libraryapi.facade.assembler;

import org.springframework.stereotype.Component;

import fr.benoitne.library.dto.LibraryDTO;
import fr.benoitne.libraryapi.persistence.entity.LibraryEntity;

@Component
public class LibraryDTOAssembler {

	
	  public LibraryDTO convertToDTO(LibraryEntity libraryEntity) {
	        LibraryDTO libraryDTO = new LibraryDTO();
	        libraryDTO.setId(libraryEntity.getId());
	        libraryDTO.setName(libraryEntity.getName());
	        libraryDTO.setAdress(libraryEntity.getAdress());
	        libraryDTO.setEmail(libraryEntity.getEmail());
	        libraryDTO.setPhoneNumber(libraryEntity.getPhoneNumber());
	        return libraryDTO;
	    }

	    public LibraryEntity convertToEntity(LibraryDTO libraryDTO) {
	        LibraryEntity libraryEntity = new LibraryEntity();
	        libraryEntity.setId(libraryDTO.getId());
	        libraryEntity.setName(libraryDTO.getName());
	        libraryEntity.setAdress(libraryDTO.getAdress());
	        libraryEntity.setEmail(libraryDTO.getEmail());
	        libraryEntity.setPhoneNumber(libraryDTO.getPhoneNumber());
	        return libraryEntity;
	    }
	
	
}
