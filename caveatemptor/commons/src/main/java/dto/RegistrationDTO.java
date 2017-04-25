package dto;

import java.io.Serializable;

public class RegistrationDTO implements Serializable {

	private static final long serialVersionUID = -365789621565459000L;

	private Long id;

	private UserDTO userDTO;

	private String authorizationKey;

	private Long authorizationKeyExpiration;

	public RegistrationDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public UserDTO getUser() {
		return userDTO;
	}

	public void setUser(UserDTO userDTO) {
		this.userDTO = userDTO;
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
