package fr.benoitne.libraryapi.persistence.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import fr.benoitne.libraryapi.persistence.entity.UserEntity;


//import fr.benoitne.libraryapi.service.EncodePassword;

public class CustomizedUserRepositoryImpl implements CustomizedUserRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean compare(String userName, String password) {

		UserEntity userEntity = null;
//		EncodePassword encodePassword = new EncodePassword();

		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);

		try {
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		userEntity = (UserEntity) fullTextEntityManager.createQuery("FROM UserEntity U WHERE U.userName = :userName")
				.setParameter("userName", userName).getSingleResult();

		if (userEntity != null && userEntity.getPassword().equals(password)) {//(encodePassword.encode(password, 12))) {
			return true;
		} else
			return false;
	}

	@Override
	public UserEntity getByUserName(String userName) {
		
		UserEntity userEntity = null;
		
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);

		try {
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
		userEntity = (UserEntity) fullTextEntityManager.createQuery("FROM UserEntity U WHERE U.userName = :userName")
				.setParameter("userName", userName).getSingleResult();
		
		return userEntity;
	}

}
