package fr.benoitne.libraryapi.persistence.repository;

import java.util.List;

import fr.benoitne.libraryapi.persistence.entity.BookEntity;



public interface CustomizedBookRepository {

	List<BookEntity> search(String terms);

}
