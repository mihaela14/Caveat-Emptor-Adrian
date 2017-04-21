package mapping;

import org.modelmapper.ModelMapper;

import repository.entities.Registration;
import dto.RegistrationDTO;

public class RegistrationMapper {

	private RegistrationMapper() {
	}

	public static Registration getRegistration(RegistrationDTO registrationDTO) {
		return new ModelMapper().map(registrationDTO, Registration.class);
	}

	public static RegistrationDTO getRegistrationDTO(Registration registration) {
		return new ModelMapper().map(registration, RegistrationDTO.class);
	}

}
