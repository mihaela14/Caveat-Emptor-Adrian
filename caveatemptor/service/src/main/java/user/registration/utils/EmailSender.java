package user.registration.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import constants.Config;
import constants.Email;
import constants.Routes;
import dto.UserDTO;

public class EmailSender {

	private EmailSender() {
	}

	public static class CaveatEmail {

		private Properties properties;

		private final Session session;

		public CaveatEmail(Properties properties, Session session) {
			this.properties = properties;
			this.session = session;
		}

		public Properties getProperties() {
			return properties;
		}

		public Session getSession() {
			return session;
		}

	}

	public static void send(UserDTO user, String authenticationKey) {

		Properties properties = PropertiesLoader.getProperties(
				EmailSender.class.getClassLoader(), Config.EMAIL.getFileName());

		Session session = getSession(properties);

		CaveatEmail wrapper = new CaveatEmail(properties, session);

		try {
			String activationUrl = Routes.ACTIVATE_ABSOLUTE.getUrl()
					+ Email.SCOPE.getValue() + Email.KEY.getValue()
					+ authenticationKey;

			Message message = getMessage(wrapper, user.getEmailAddress(),
					activationUrl);
			Transport.send(message);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static Session getSession(Properties properties) {

		return Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(properties
						.getProperty(Email.USERNAME.getValue()), properties
						.getProperty(Email.PASSWORD.getValue()));
			}
		});
	}

	private static Message getMessage(CaveatEmail caveatEmail,
			String emailAddress, String activationUrl) throws AddressException,
			MessagingException {

		Message message = new MimeMessage(caveatEmail.getSession());

		message.setFrom(new InternetAddress(caveatEmail.getProperties()
				.getProperty(Email.USERNAME.getValue())));

		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(emailAddress));

		message.setSubject(Email.SUBJECT.getValue());

		message.setText(String.format(Email.TEXT.getValue(), activationUrl));

		return message;
	}

}
