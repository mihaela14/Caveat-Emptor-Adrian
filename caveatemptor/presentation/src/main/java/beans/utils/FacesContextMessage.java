package beans.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesContextMessage {

	private FacesContextMessage() {
	}

	public static void addMessage(FacesContext facesContext, String clientId,
			String message) {
		facesContext.addMessage(clientId, new FacesMessage(message));
	}

}
