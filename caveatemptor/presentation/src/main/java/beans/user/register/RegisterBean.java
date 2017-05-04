package beans.user.register;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import user.registration.RegistrationService;
import utils.FacesContextMessage;
import constants.Email;
import constants.Forms;
import dto.UserDTO;
import exceptions.RegistrationException;

@ManagedBean(name = "register")
@RequestScoped
public class RegisterBean {

	@EJB
	private RegistrationService registrationService;

	private UserDTO userDTO;

	@PostConstruct
	public void init() {
		userDTO = new UserDTO();
	}

	public String register() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		try {
			registrationService.registerUser(getUserDTO());
			FacesContextMessage.addMessage(facesContext,
					Forms.REGISTER.getName(),
					Email.ACTIVATE_ACCOUNT_MESSAGE.getValue());
		} catch (RegistrationException e) {
			FacesContextMessage.addMessage(facesContext,
					Forms.REGISTER.getName(), e.getMessage());
		}

		return null;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

}
