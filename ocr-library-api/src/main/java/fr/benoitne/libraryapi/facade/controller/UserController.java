package fr.benoitne.libraryapi.facade.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.benoitne.library.dto.UserDTO;
import fr.benoitne.libraryapi.facade.assembler.UserDTOAssembler;
import fr.benoitne.libraryapi.persistence.entity.UserEntity;
import fr.benoitne.libraryapi.persistence.repository.UserRepository;

@Controller
@RequestMapping(value = "/")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDTOAssembler userDTOAssembler;


	@RequestMapping(method = RequestMethod.GET, path = "/user/userName")
	@ResponseBody
	public Optional<UserDTO> loadUserByUsername(String userName) {
		UserEntity userEntity = userRepository.getByUserName(userName);
		if (userEntity != null) {
		Optional<UserEntity> optUser = Optional.of(userEntity);
		return optUser.map(x -> userDTOAssembler.convertToDTO(x));
		}else {
			return null;
		}
	}
	

}
