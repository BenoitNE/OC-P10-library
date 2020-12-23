package fr.benoitne.libraryapi.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.benoitne.libraryapi.persistence.entity.LoanEntity;




@Repository
public interface LoanRepository  extends CrudRepository <LoanEntity, Long>, CustomizedLoanRepository  {

}
