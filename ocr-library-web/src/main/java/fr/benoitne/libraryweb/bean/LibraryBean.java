package fr.benoitne.libraryweb.bean;

public class LibraryBean {

	private long id;

	private String name;

	private String adress;

	private String email;

	private String phoneNumber;

	
	
	public LibraryBean() {
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

	@Override
	public String toString() {
		return "LibraryBean [id=" + id + ", name=" + name + ", adress=" + adress + ", email=" + email + ", phoneNumber="
				+ phoneNumber + "]";
	}
	
	
	
}
