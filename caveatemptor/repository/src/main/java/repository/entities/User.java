package repository.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "findUserWithAccountName", query = "SELECT u FROM User u where u.accountName = :accountName")
@Table(name = "users")
public class User implements Serializable {

	/**
	 * default serial version ID
	 */
	private static final long serialVersionUID = 1L;

	public static final String QUERY_FIND_USER_WITH_ACCOUNT_NAME = "findUserWithAccountName";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private int id;

	public static final String ID = "id";

	@Column(name = "first_name", nullable = false)
	private String firstName;

	public static final String FIRST_NAME = "firstName";

	@Column(name = "last_name", nullable = false)
	private String lastName;

	public static final String LAST_NAME = "lastName";

	@Column(name = "email_address", unique = true, nullable = false)
	private String emailAddress;

	public static final String EMAIL_ADDRESS = "emailAddress";

	@Column(name = "account_name", unique = true, nullable = false)
	private String accountName;

	public static final String ACCOUNT_NAME = "accountName";

	@Column(name = "password", nullable = false)
	private String password;

	public static final String PASSWORD = "password";

	@Column(name = "role", nullable = false)
	private String role;

	public static final String ROLE = "role";

	@Column(name = "is_activated", nullable = false)
	private boolean isActivated;

	public static final String IS_ACTIVATED = "isActivated";

	public User() {
	}

	public User(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailAddress="
				+ emailAddress + ", accountName=" + accountName + ", password=" + password + ", role=" + role + "]";
	}

}
