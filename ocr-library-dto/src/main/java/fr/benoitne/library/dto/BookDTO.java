package fr.benoitne.library.dto;


import java.util.List;

public class BookDTO {

	private long id;

	private String title;

	private String author;

	private int pageNumber;

	private String type;

	private String publishing;

	private String status;

	private String imageLink;

	private int quantity;

	private String summary;
	
	private LibraryDTO libraryDTO;

	private List<String> userWaitingLine;

	private List<String> userLoanList;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPublishing() {
		return publishing;
	}

	public void setPublishing(String publishing) {
		this.publishing = publishing;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public LibraryDTO getLibraryDTO() {
		return libraryDTO;
	}

	public void setLibraryDTO(LibraryDTO libraryDTO) {
		this.libraryDTO = libraryDTO;
	}

	public List<String> getUserWaitingLine() {
		return userWaitingLine;
	}

	public void setUserWaitingLine(List<String> userWaitingLine) {
		this.userWaitingLine = userWaitingLine;
	}

	public List<String> getUserLoanList() {
		return userLoanList;
	}

	public void setUserLoanList(List<String> userLoanList) {
		this.userLoanList = userLoanList;
	}


}
