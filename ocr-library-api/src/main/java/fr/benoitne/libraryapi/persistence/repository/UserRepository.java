package fr.benoitne.libraryapi.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.benoitne.libraryapi.persistence.entity.UserEntity;


@Repository
public interface UserRepository extends CrudRepository <UserEntity, Long>, CustomizedUserRepository {

}
