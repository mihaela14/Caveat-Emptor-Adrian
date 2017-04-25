package user.registration;

import dto.UserDTO;
import exceptions.RegistrationException;

public interface IRegistrationService {

	void registerUser(UserDTO userDTO) throws RegistrationException;
}
