package dto;

import java.io.Serializable;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = -2886676111463445945L;

	private Long id;

	private String firstName;

	private String lastName;

	private String emailAddress;

	private String accountName;

	private String password;

	private String role;

	private Boolean isActivated;

	public UserDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean isActivated() {
		return isActivated;
	}

	public void setActivated(Boolean isActivated) {
		this.isActivated = isActivated;
	}

}
