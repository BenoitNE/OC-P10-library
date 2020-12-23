package fr.benoitne.libraryapi.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.benoitne.libraryapi.persistence.entity.BookEntity;


@Repository
public interface BookRepository extends CrudRepository <BookEntity, Long>, CustomizedBookRepository {

}
