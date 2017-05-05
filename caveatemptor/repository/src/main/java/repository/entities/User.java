package repository.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "findUserWithAccountNameOrEmailAddress", query = "SELECT usr FROM User usr WHERE usr.accountName = :accountName or usr.emailAddress = :emailAddress"),
		@NamedQuery(name = "findUserWithAccountName", query = "SELECT usr FROM User usr WHERE usr.accountName = :accountName") })
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = -6828711065892247573L;

	public static final String QUERY_FIND_USER_WITH_ACCOUNT_NAME = "findUserWithAccountName";
	public static final String QUERY_FIND_USER_WITH_ACCOUNT_NAME_OR_EMAIL_ADDRESS = "findUserWithAccountNameOrEmailAddress";

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

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String role;

	@Column(name = "is_activated", nullable = false)
	private Boolean isActivated;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Item> items;

	public User() {
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
