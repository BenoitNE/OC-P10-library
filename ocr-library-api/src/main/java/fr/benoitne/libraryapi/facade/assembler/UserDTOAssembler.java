package fr.benoitne.libraryapi.facade.assembler;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import fr.benoitne.library.dto.UserDTO;
import fr.benoitne.libraryapi.persistence.entity.UserEntity;


@Component
public class UserDTOAssembler {
	
	@Autowired
	LoanDTOAssembler loanDTOAssembler;

	 
	public UserDTO convertToDTO(UserEntity userEntity) {
	        UserDTO userDTO = new UserDTO();
	        userDTO.setId(userEntity.getId());
	        userDTO.setFirstName(userEntity.getFirstName());
	        userDTO.setLastName(userEntity.getLastName());
	        userDTO.setUserName(userEntity.getUserName());
	        userDTO.setAdress(userEntity.getAdress());
	        userDTO.setEmail(userEntity.getEmail());
	        userDTO.setPhoneNumber(userEntity.getPhoneNumber());
	        userDTO.setRole(userEntity.getRole());
	        userDTO.setPassword(userEntity.getPassword());
	        return userDTO;
	    }

	    
		public UserEntity convertToEntity(UserDTO userDTO) {
	        UserEntity userEntity = new UserEntity();
	        userEntity.setId(userDTO.getId());
	        userEntity.setFirstName(userDTO.getFirstName());
	        userEntity.setLastName(userDTO.getLastName());
	        userEntity.setUserName(userDTO.getUserName());
	        userEntity.setAdress(userDTO.getAdress());
	        userEntity.setEmail(userDTO.getEmail());
	        userEntity.setPhoneNumber(userDTO.getPhoneNumber());
	        userEntity.setRole(userDTO.getRole());
	        userEntity.setPassword(userDTO.getPassword());;
	        return userEntity;
	    }
	
}
