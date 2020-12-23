package fr.benoitne.library.dto;

public class LoanDTO {

	private long id;

	private String status;

	private String startBorrowingDate;

	private String endBorrowingDate;

	private String prolongationDate;

	private UserDTO userDTO;

	private BookDTO bookDTO;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
