package com.epam.easyshopway.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {

	public static void sendEmailRegistrationLink(String email, String name, String hash)
			throws AddressException, MessagingException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", Setup.MAIL_SMTP_HOST);
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Setup.MAIL_USERNAME, Setup.MAIL_PASSWORD);
			}
		});

		String link = Setup.MAIL_REGISTRATION_SITE_LINK + "?scope=activation&hash=" + hash;

		StringBuilder bodyText = new StringBuilder();
		// bodyText.append("<div>").append(" Dear User<br/><br/>")
		// .append(" Thank you for registration. Your mail (" + email + ") is
		// under verification<br/>")
		// .append(" Please click <a href=\"" + link + "\">here</a> or open
		// below link in browser<br/>")
		// .append(" <a href=\"" + link + "\">" + link + "</a>").append("
		// <br/><br/>").append(" Thanks,<br/>")
		// .append(" SodhanaLibrary Team").append("</div>");
		bodyText.append("<body style='margin: 0; padding: 0;'>" + "<table align='center' border='0' cellpadding='0'"
				+ " cellspacing='0'	width='600' style='color: #153643; font-family: Arial, "
				+ "sans-serif; font-size: 16px; line-height: 20px;'><tr><td align='center' bgcolor='#70bbd9' "
				+ "style='padding: 40px 0 30px 0; font-size: 30px; color: white; font-weight: 600;'>EasyShopWay</td>"
				+ "</tr><tr><td bgcolor='#ffffff' style='padding: 40px 30px 40px 30px;'><table border='0'"
				+ "cellpadding='0' cellspacing='0' width='100%'><tr><td	style='color: #153643; font-family: Arial, "
				+ "sans-serif; font-size: 24px;'>Dear " + name + "!</td></tr><tr><td>Thank you for registration. Your email "
				+ "(" + email + ") is under verification.</td></tr><tr><td>Please click "
				+ "<a href='" + link + "'>here</a></td></tr></table></td></tr><tr>"
				+ "<td bgcolor='#ee4c50' style='padding: 30px 30px 30px 30px;'><table border='0' cellpadding='0'"
				+ " cellspacing='0' width='100%'><tr><td style='color: #ffffff; font-family: Arial, sans-serif; "
				+ "font-size: 14px;'>&reg; Thanks, EasyShopWay Team <br /></td><td align='right'></td></tr></table>"
				+ "</td></tr></table></body>");
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(Setup.MAIL_USERNAME));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		message.setSubject("Email Registration");
		message.setContent(bodyText.toString(), "text/html; charset=utf-8");
		Transport.send(message);
	}

	public static void sendEmail(String email, String header, String body) throws AddressException, MessagingException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", Setup.MAIL_SMTP_HOST);
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Setup.MAIL_USERNAME, Setup.MAIL_PASSWORD);
			}
		});

		StringBuilder bodyText = new StringBuilder();
		bodyText.append("<div>");
		bodyText.append(body);
		bodyText.append("</div>");
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(Setup.MAIL_USERNAME));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		message.setSubject(header);
		message.setContent(bodyText.toString(), "text/html; charset=utf-8");
		Transport.send(message);
	}

	public static void sendResetPasswordLink(String email, String hash) throws AddressException, MessagingException {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", Setup.MAIL_SMTP_HOST);
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Setup.MAIL_USERNAME, Setup.MAIL_PASSWORD);
			}
		});

		String link = Setup.MAIL_REGISTRATION_SITE_LINK + "?scope=resetPassword&hash=" + hash;

		StringBuilder bodyText = new StringBuilder();
		bodyText.append("<div>").append("  Dear User<br/><br/>")
				.append("  We got your reset password request, Find below link to reset password <br/>")
				.append("  Please click <a href=\"" + link + "\">here</a> or open below link in browser<br/>")
				.append("  <a href=\"" + link + "\">" + link + "</a>").append("  <br/><br/>").append("  Thanks,<br/>")
				.append("  SodhanaLibrary Team").append("</div>");
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(Setup.MAIL_USERNAME));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		message.setSubject("Reset Password");
		message.setContent(bodyText.toString(), "text/html; charset=utf-8");
		Transport.send(message);
	}

}
