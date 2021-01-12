package fr.benoitne.libraryapi.persistence.repository;


import fr.benoitne.libraryapi.persistence.entity.LoanArchiveEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoanArchiveRepository extends CrudRepository<LoanArchiveEntity, Long>, CustomizedLoanArchiveRepository {
}
