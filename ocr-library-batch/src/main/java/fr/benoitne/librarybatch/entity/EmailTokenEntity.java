package fr.benoitne.librarybatch.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmailTokenEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	String token;

	String date;

	long loanId;

	public EmailTokenEntity(String token, String date, long loanId) {
		super();
		this.token = token;
		this.date = date;
		this.loanId = loanId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getLoanId() {
		return loanId;
	}

	public void setLoanId(long loanId) {
		this.loanId = loanId;
	}

}
