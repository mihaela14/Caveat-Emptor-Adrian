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

import constants.Email;
import dto.UserDTO;

public class EmailSender {

	public static void send(UserDTO user, String authenticationKey,
			String path, String url) {

		Properties properties = PropertiesLoader.getProperties(
				EmailSender.class.getClassLoader(), path);

		String to = user.getEmailAddress();

		Session session = getSession(properties);

		try {
			url += Email.SCOPE.getValue() + Email.KEY.getValue()
					+ authenticationKey;

			Message message = getMessage(session, properties, to, url);

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

	private static Message getMessage(Session session, Properties properties,
			String to, String url) throws AddressException, MessagingException {

		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress(properties
				.getProperty(Email.USERNAME.getValue())));

		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));

		message.setSubject(Email.SUBJECT.getValue());

		message.setText(String.format(Email.TEXT.getValue(), url));

		return message;
	}

}
