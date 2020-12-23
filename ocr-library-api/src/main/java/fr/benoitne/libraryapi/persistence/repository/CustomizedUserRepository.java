package fr.benoitne.libraryapi.persistence.repository;

import fr.benoitne.libraryapi.persistence.entity.UserEntity;

public interface CustomizedUserRepository {
	boolean compare(String userName, String password);
	UserEntity getByUserName (String userName);
}
