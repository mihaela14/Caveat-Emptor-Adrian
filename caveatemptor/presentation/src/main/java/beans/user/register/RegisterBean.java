package beans.user.register;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import user.registration.IRegistrationService;
import utils.FacesContextMessage;
import constants.Forms;
import dto.UserDTO;
import exceptions.RegistrationException;

@ManagedBean(name = "register")
@RequestScoped
public class RegisterBean {

	@EJB
	private IRegistrationService iRegistrationService;

	private String firstName;
	private String lastName;
	private String emailAddress;
	private String accountName;
	private String password;

	public void register() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		UserDTO userDTO = getUserDTO();

		try {
			iRegistrationService.registerUser(userDTO);
		} catch (RegistrationException e) {
			FacesContextMessage.addMessage(facesContext,
					Forms.REGISTER.getName(), e.getMessage());
		}
	}

	private UserDTO getUserDTO() {

		UserDTO userDTO = new UserDTO();

		userDTO.setFirstName(firstName);
		userDTO.setLastName(lastName);
		userDTO.setEmailAddress(emailAddress);
		userDTO.setAccountName(accountName);
		userDTO.setPassword(password);

		return userDTO;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
