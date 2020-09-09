package controllers;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * The Class SendMail.
 */
public class SendMail {

	/** The user name. */
	private String userName;

	/** The good Orbad. */
	private int goodORbad;

	/** The request id. */
	private int reqId;

	private String stage = "";

	/**
	 * Instantiates a new send mail.
	 *
	 * @param userName  the user name
	 * @param goodORbad the good O rbad
	 * @param reqId     the req id
	 */
	public SendMail(String userName, int goodORbad, int reqId) {
		this.userName = userName;
		this.goodORbad = goodORbad;
		this.reqId = reqId;
	}

	public SendMail(String userName, int goodORbad, int reqId, String stage) {
		this.userName = userName;
		this.goodORbad = goodORbad;
		this.reqId = reqId;
		this.stage = stage;
	}

	/**
	 * This method is sending a mail.
	 */
	public void sendEMail() {

		final String username = "icm6348@gmail.com";
		final String password = "**************";

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("icm6348@gmail.com"));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.userName + "@s.braude.ac.il")); // gmail.com

			if (this.goodORbad == 1) // 1->good
			{
				message.setSubject("Handling request number " + this.reqId + " has finished successfully");
				message.setText("Dear " + this.userName + ",  Handling request number " + this.reqId
						+ " has finished successfully.");
			} else if (this.goodORbad == 0) // 0->bad
			{
				message.setSubject("Handling request number " + this.reqId + " has denied");
				message.setText("Dear " + this.userName + ",  Your request was denied by the committie members.");
			} else if (this.goodORbad == 2) // 2-> send mail one day before finishd stage
			{
				System.out.println("good");
				message.setSubject("Handling request number " + this.reqId + " - reminder message");
				message.setText("Dear " + this.userName
						+ ", Reminder: tomorrow is the scheduled date for completion the stage " + this.stage + " .");
				System.out.println("done");
			}

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
