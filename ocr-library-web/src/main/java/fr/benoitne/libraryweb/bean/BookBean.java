package fr.benoitne.libraryweb.bean;


public class BookBean {

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

	private LibraryBean libraryBean;
	
	public BookBean() {
		super();
	}

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
	
	

	public LibraryBean getLibraryBean() {
		return libraryBean;
	}

	public void setLibraryBean(LibraryBean libraryBean) {
		this.libraryBean = libraryBean;
	}

	@Override
	public String toString() {
		return "BookBean [id=" + id + ", title=" + title + ", author=" + author + ", pageNumber=" + pageNumber
				+ ", type=" + type + ", publishing=" + publishing + ", status=" + status + ", imageLink=" + imageLink
				+ ", quantity=" + quantity + ", summary=" + summary + "]";
	}
	
	
	
}
