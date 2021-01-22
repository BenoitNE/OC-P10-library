package fr.benoitne.libraryapi.persistence.repository;

import fr.benoitne.libraryapi.persistence.entity.BookEntity;
import fr.benoitne.libraryapi.persistence.entity.ReservationRequestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRequestRepository extends CrudRepository<ReservationRequestEntity, Long> {
}
