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

	private static final long serialVersionUID = 1L;

	public static final String QUERY_FIND_REGISTRATION_WITH_ACTIVATION_KEY = "findRegistrationWithActivationKey";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private int id;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@Column(name = "authorization_key", nullable = false)
	private String authorizationKey;

	@Column(name = "authorization_key_expiration", nullable = false)
	private long authorizationKeyExpiration;

	public static final String ID_FIELD = "id";

	public static final String USER_ID_FIELD = "userId";

	public static final String AUTHORIZATION_KEY_FIELD = "authorizationKey";

	public static final String AUTHORIZATION_KEY_EXPIRATION_FIELD = "authorizationKeyExpiration";

	public Registration() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public long getAuthorizationKeyExpiration() {
		return authorizationKeyExpiration;
	}

	public void setAuthorizationKeyExpiration(long authorizationKeyExpiration) {
		this.authorizationKeyExpiration = authorizationKeyExpiration;
	}

}
