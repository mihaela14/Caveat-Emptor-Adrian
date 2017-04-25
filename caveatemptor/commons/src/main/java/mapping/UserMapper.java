package mapping;

import dto.UserDTO;
import repository.entities.User;

public class UserMapper {

	private UserMapper() {
	}

	public static User getUser(UserDTO userDTO) {

		User user = new User();

		user.setId(userDTO.getId());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmailAddress(userDTO.getEmailAddress());
		user.setAccountName(userDTO.getAccountName());
		user.setPassword(userDTO.getPassword());
		user.setRole(userDTO.getRole());
		user.setActivated(userDTO.isActivated());

		return user;
	}

	public static UserDTO getUserDTO(User user) {

		UserDTO userDTO = new UserDTO();

		userDTO.setId(user.getId());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setEmailAddress(user.getEmailAddress());
		userDTO.setAccountName(user.getAccountName());
		userDTO.setPassword(user.getPassword());
		userDTO.setRole(user.getRole());
		userDTO.setActivated(user.isActivated());

		return userDTO;
	}

}
