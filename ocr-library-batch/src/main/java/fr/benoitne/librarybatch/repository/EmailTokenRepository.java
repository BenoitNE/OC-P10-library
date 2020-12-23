package fr.benoitne.librarybatch.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.benoitne.librarybatch.entity.EmailTokenEntity;

@Repository
public interface EmailTokenRepository extends CrudRepository <EmailTokenEntity, Long> {

	
	
}
