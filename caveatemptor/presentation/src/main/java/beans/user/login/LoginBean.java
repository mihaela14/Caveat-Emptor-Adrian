package beans.user.login;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import user.login.ILoginService;
import beans.utils.FacesContextMessage;
import constants.ErrorMessages;
import constants.Forms;
import constants.Routes;
import exceptions.user.UserException;

@ManagedBean(name = "login")
@RequestScoped
public class LoginBean {

	@EJB
	private ILoginService iLoginService;

	private String accountName;
	private String password;

	public LoginBean() {
	}

	public String login() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		try {
			boolean isValidUserLoginData = iLoginService.isValidUserLoginData(
					accountName, password);

			if (isValidUserLoginData) {
				return Routes.INDEX_REDIRECT.getUrl();
			} else {
				FacesContextMessage.addMessage(facesContext,
						Forms.LOGIN.getName(),
						ErrorMessages.INVALID_PASSWORD.getDetails());
			}
		} catch (UserException e) {
			FacesContextMessage.addMessage(facesContext, Forms.LOGIN.getName(),
					ErrorMessages.USER_NOT_FOUND.getDetails());
		}

		return null;
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
