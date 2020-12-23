package fr.benoitne.librarybatch.bean;

import org.springframework.stereotype.Component;

import fr.benoitne.library.dto.BookDTO;
import fr.benoitne.library.dto.UserDTO;


public class LoanBean {

	private long id;

	private String status;

	private String startBorrowingDate;

	private String endBorrowingDate;

	private String prolongationDate;

	private UserDTO userDTO;

	private BookDTO bookDTO;

	public LoanBean() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStartBorrowingDate() {
		return startBorrowingDate;
	}

	public void setStartBorrowingDate(String startBorrowingDate) {
		this.startBorrowingDate = startBorrowingDate;
	}

	public String getEndBorrowingDate() {
		return endBorrowingDate;
	}

	public void setEndBorrowingDate(String endBorrowingDate) {
		this.endBorrowingDate = endBorrowingDate;
	}

	public String getProlongationDate() {
		return prolongationDate;
	}

	public void setProlongationDate(String prolongationDate) {
		this.prolongationDate = prolongationDate;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public BookDTO getBookDTO() {
		return bookDTO;
	}

	public void setBookDTO(BookDTO bookDTO) {
		this.bookDTO = bookDTO;
	}

	@Override
	public String toString() {
		return "LoanBean [id=" + id + ", status=" + status + ", startBorrowingDate=" + startBorrowingDate
				+ ", endBorrowingDate=" + endBorrowingDate + ", prolongationDate=" + prolongationDate + ", userDTO="
				+ userDTO + ", bookDTO=" + bookDTO + "]";
	}

	
}
