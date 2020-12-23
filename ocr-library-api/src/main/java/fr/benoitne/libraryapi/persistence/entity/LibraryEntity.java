package fr.benoitne.libraryapi.persistence.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Indexed;

@Entity
@Indexed
public class LibraryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	private String adress;

	private String email;

	private String phoneNumber;

	@OneToMany(mappedBy = "libraryEntity", fetch = FetchType.LAZY)
	private List<BookEntity> bookEntity;

	public LibraryEntity() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<BookEntity> getBookEntity() {
		return bookEntity;
	}

	public void setBookEntity(List<BookEntity> bookEntity) {
		this.bookEntity = bookEntity;
	}

}
