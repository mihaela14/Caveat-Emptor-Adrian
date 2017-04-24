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
@NamedQuery(name = "findUserWithAccountName", query = "SELECT usr FROM User usr WHERE usr.accountName = :accountName")
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String QUERY_FIND_USER_WITH_ACCOUNT_NAME = "findUserWithAccountName";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private Long id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "email_address", unique = true, nullable = false)
	private String emailAddress;

	@Column(name = "account_name", unique = true, nullable = false)
	private String accountName;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "role", nullable = false)
	private String role;

	@Column(name = "is_activated", nullable = false)
	private boolean isActivated;

	public static final String ID_FIELD = "id";

	public static final String FIRST_NAME_FIELD = "firstName";

	public static final String LAST_NAME_FIELD = "lastName";

	public static final String EMAIL_ADDRESS_FIELD = "emailAddress";

	public static final String ACCOUNT_NAME_FIELD = "accountName";

	public static final String PASSWORD_FIELD = "password";

	public static final String ROLE_FIELD = "role";

	public static final String IS_ACTIVATED_FIELD = "isActivated";

	public User() {
	}

	public Long getId() {
		return id;
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

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

}
