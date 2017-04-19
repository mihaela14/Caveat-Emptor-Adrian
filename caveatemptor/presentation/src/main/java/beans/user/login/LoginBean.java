package beans.user.login;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import exceptions.login.InvalidPasswordException;
import exceptions.login.UserNotFoundException;
import user.login.ILoginService;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean {

	@EJB
	private ILoginService iLoginService;

	private String accountName;

	private String password;

	private String status;

	public static final String INVALID_PASSWORD = "Invalid password.";

	public static final String VALID_CREDENTIALS = "Valid credentials.";

	public static final String USER_NOT_FOUND = "User not found.";

	public LoginBean() {
	}

	public String hasValidCredentials() {
		try {
			boolean hasValidCredentials = iLoginService.hasValidCredentials(
					accountName, password);
			if (hasValidCredentials) {
				status = VALID_CREDENTIALS;
			}
		} catch (UserNotFoundException e) {
			status = USER_NOT_FOUND;
		} catch (InvalidPasswordException e) {
			status = INVALID_PASSWORD;
		}

		return status;
	}

	public ILoginService getiLoginService() {
		return iLoginService;
	}

	public void setiLoginService(ILoginService iLoginService) {
		this.iLoginService = iLoginService;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
