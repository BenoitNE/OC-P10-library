package fr.benoitne.libraryapi.persistence.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import fr.benoitne.libraryapi.persistence.entity.BookEntity;



public class CustomizedBookRepositoryImpl implements CustomizedBookRepository {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<BookEntity> search(String search) {

		List<BookEntity> listOfBooks = null;

		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
		try {
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		org.hibernate.search.query.dsl.QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder().forEntity(BookEntity.class).get();
		org.apache.lucene.search.Query luceneQuery = ((org.hibernate.search.query.dsl.QueryBuilder) queryBuilder)
				.keyword().onFields("title", "author", "type", "publishing").matching(search).createQuery();

		javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, BookEntity.class);

		listOfBooks = jpaQuery.getResultList();

//		jpaQuery.setMaxResults(limit);
//		jpaQuery.setFirstResult(offset);

		return listOfBooks;
	}

}
