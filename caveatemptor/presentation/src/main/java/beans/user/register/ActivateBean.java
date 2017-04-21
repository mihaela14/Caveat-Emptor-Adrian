package beans.user.register;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import user.activation.IActivationService;
import exceptions.registration.RegistrationException;

@ManagedBean(name = "activation")
@RequestScoped
public class ActivateBean {

	@EJB
	private IActivationService iActivationService;

	private String activationKey;

	// TODO: handle exception
	public void activate() {

		try {
			iActivationService.activate(activationKey);
		} catch (RegistrationException e) {

		}
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

}
