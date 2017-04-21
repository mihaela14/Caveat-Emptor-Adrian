package dto;

import java.io.Serializable;

public class RegistrationDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private UserDTO user;

	private String authorizationKey;

	private long authorizationKeyExpiration;

	public RegistrationDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
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
