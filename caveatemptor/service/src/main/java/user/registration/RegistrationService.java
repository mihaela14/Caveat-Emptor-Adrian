package user.registration;

import dto.UserDTO;
import exceptions.RegistrationException;

public interface RegistrationService {

	void registerUser(UserDTO userDTO) throws RegistrationException;
}
