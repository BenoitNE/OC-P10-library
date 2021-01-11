package fr.benoitne.libraryweb.bean;

import java.util.List;

public class UserBean {

	private long id;

	private String firstName;

	private String lastName;

	private String userName;

	private String adress;

	private String email;

	private String phoneNumber;
	
	private String role;
	
	private String password;

	private List<LoanBean> loanDTOList;
		

	public UserBean() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<LoanBean> getLoanDTOList() {
		return loanDTOList;
	}

	public void setLoanDTOList(List<LoanBean> loanDTOList) {
		this.loanDTOList = loanDTOList;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", adress=" + adress + ", email=" + email + ", phoneNumber=" + phoneNumber + ", role=" + role
				+ ", password=" + password + "]";
	}

	

	

	

}
