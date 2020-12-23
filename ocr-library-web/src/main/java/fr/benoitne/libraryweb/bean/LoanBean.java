package fr.benoitne.libraryweb.bean;

import fr.benoitne.library.dto.BookDTO;
import fr.benoitne.library.dto.UserDTO;
import fr.benoitne.libraryweb.service.DateTool;

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

	public String getStartBorrowingDate() {
		DateTool dateTool = new DateTool();
		return dateTool.convert(startBorrowingDate);
	}

	public void setStartBorrowingDate(String startBorrowingDate) {
		this.startBorrowingDate = startBorrowingDate;
	}

	public String getEndBorrowingDate() {
		DateTool dateTool = new DateTool();
		return dateTool.convert(endBorrowingDate);
	}

	public void setEndBorrowingDate(String endBorrowingDate) {
		this.endBorrowingDate = endBorrowingDate;
	}

	public String getProlongationDate() {
		DateTool dateTool = new DateTool();
		return dateTool.convert(prolongationDate);
	}

	public void setProlongationDate(String prolongationDate) {
		this.prolongationDate = prolongationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

//	public UserBean getUserBean() {
//		return userBean;
//	}
//
//	public void setUserBean(UserBean userBean) {
//		this.userBean = userBean;
//	}
//
//	public BookBean getBookBean() {
//		return bookBean;
//	}
//
//	public void setBookBean(BookBean bookBean) {
//		this.bookBean = bookBean;
//	}
	
	

	@Override
	public String toString() {
		return "LoanBean [id=" + id + ", startBorrowingDate=" + startBorrowingDate + ", endBorrowingDate="
				+ endBorrowingDate + ", prolongationDate=" + prolongationDate + "]";
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

}
