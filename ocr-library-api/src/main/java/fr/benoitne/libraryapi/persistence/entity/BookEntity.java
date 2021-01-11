package fr.benoitne.libraryapi.persistence.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
public class BookEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String title;

	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String author;

	private int pageNumber;

	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String type;

	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String publishing;

	private String status;

	private String imageLink;

	private int quantity;

	@Column(length = 2000)
	private String summary;

	@OneToMany(mappedBy = "bookEntity", fetch = FetchType.LAZY)
	private List<LoanEntity> loanEntity;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "libraryEntity_id")
	private LibraryEntity libraryEntity;

	

	public BookEntity() {
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

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<LoanEntity> getLoanEntity() {
		return loanEntity;
	}

	public void setLoanEntity(List<LoanEntity> loanEntity) {
		this.loanEntity = loanEntity;
	}

	public LibraryEntity getLibraryEntity() {
		return libraryEntity;
	}

	public void setLibraryEntity(LibraryEntity libraryEntity) {
		this.libraryEntity = libraryEntity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

//	@Override
//	public String toString() {
//		return "BookEntity [id=" + id + ", title=" + title + ", author=" + author + ", pageNumber=" + pageNumber
//				+ ", type=" + type + ", publishing=" + publishing + ", status=" + status + ", imageLink=" + imageLink
//				+ ", summary=" + summary + ", loanEntity=" + loanEntity + ", libraryEntity=" + libraryEntity + "]";
//	}

}
