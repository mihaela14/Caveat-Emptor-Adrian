package mapping;

import dto.RegistrationDTO;
import repository.entities.Registration;

public class RegistrationMapper {

	private RegistrationMapper() {
	}

	public static Registration getRegistration(RegistrationDTO registrationDTO) {

		Registration registration = new Registration();

		registration.setId(registrationDTO.getId());
		registration.setUser(UserMapper.getUser(registrationDTO.getUser()));
		registration.setAuthorizationKey(registration.getAuthorizationKey());
		registration.setAuthorizationKeyExpiration(registrationDTO.getAuthorizationKeyExpiration());

		return registration;
	}

	public static RegistrationDTO getRegistrationDTO(Registration registration) {

		RegistrationDTO registrationDTO = new RegistrationDTO();

		registrationDTO.setId(registration.getId());
		registrationDTO.setUser(UserMapper.getUserDTO(registration.getUser()));
		registrationDTO.setAuthorizationKey(registration.getAuthorizationKey());
		registrationDTO.setAuthorizationKeyExpiration(registration.getAuthorizationKeyExpiration());

		return registrationDTO;
	}

}
