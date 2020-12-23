package fr.benoitne.libraryapi.persistence.repository;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;





public class CustomizedLoanrepositoryImpl implements CustomizedLoanRepository {

	@PersistenceContext
	private EntityManager em;
	
	

}
