package beans.user.register;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import user.registration.IRegistrationService;
import beans.utils.FacesContextMessage;
import constants.ErrorMessages;
import constants.Forms;
import dto.UserDTO;

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

	public RegisterBean() {
	}

	public void register() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		UserDTO userDTO = getUserDTO();
		iRegistrationService.registerUser(userDTO);

		FacesContextMessage.addMessage(facesContext, Forms.LOGIN.getName(),
				ErrorMessages.USER_NOT_FOUND.getDetails());
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
