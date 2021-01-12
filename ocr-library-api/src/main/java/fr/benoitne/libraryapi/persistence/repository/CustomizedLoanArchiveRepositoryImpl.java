package fr.benoitne.libraryapi.persistence.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CustomizedLoanArchiveRepositoryImpl implements CustomizedLoanArchiveRepository {

    @PersistenceContext
    private EntityManager em;
}
