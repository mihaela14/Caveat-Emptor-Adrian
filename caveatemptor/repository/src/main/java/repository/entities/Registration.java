package repository.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "findRegistrationWithActivationKey", query = "SELECT reg FROM Registration reg WHERE reg.authorizationKey = :authorizationKey")
@Table(name = "registrations")
public class Registration implements Serializable {

	private static final long serialVersionUID = 6802068321550951165L;

	public static final String QUERY_FIND_REGISTRATION_WITH_ACTIVATION_KEY = "findRegistrationWithActivationKey";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private Long id;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@Column(name = "authorization_key", nullable = false)
	private String authorizationKey;

	@Column(name = "authorization_key_expiration", nullable = false)
	private Long authorizationKeyExpiration;

	public Registration() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAuthorizationKey() {
		return authorizationKey;
	}

	public void setAuthorizationKey(String authorizationKey) {
		this.authorizationKey = authorizationKey;
	}

	public Long getAuthorizationKeyExpiration() {
		return authorizationKeyExpiration;
	}

	public void setAuthorizationKeyExpiration(Long authorizationKeyExpiration) {
		this.authorizationKeyExpiration = authorizationKeyExpiration;
	}

}
