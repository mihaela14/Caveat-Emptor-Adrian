package beans.user.login;

import item.ItemService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import user.login.LoginService;
import utils.FacesContextMessage;
import beans.user.UserBean;
import constants.Forms;
import constants.Routes;
import exceptions.UserException;

@ManagedBean(name = "login")
@RequestScoped
public class LoginBean {

	@EJB
	private LoginService loginService;

	@EJB
	private ItemService itemService;

	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;

	private String accountName;

	private String password;

	public String login() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		try {

			Long userId = loginService.validateUserLoginData(accountName,
					password);

			userBean.setId(userId);

			return Routes.INDEX_REDIRECT.getUrl();
		} catch (UserException e) {
			FacesContextMessage.addMessage(facesContext, Forms.LOGIN.getName(),
					e.getMessage());
			return null;
		}

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

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
