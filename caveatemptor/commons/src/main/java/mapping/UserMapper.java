package mapping;

import org.modelmapper.ModelMapper;

import repository.entities.User;
import dto.UserDTO;

public class UserMapper {

	private UserMapper() {
	}

	public static User getUser(UserDTO userDTO) {
		return new ModelMapper().map(userDTO, User.class);
	}

	public static UserDTO getUserDTO(User user) {
		return new ModelMapper().map(user, UserDTO.class);
	}

}
