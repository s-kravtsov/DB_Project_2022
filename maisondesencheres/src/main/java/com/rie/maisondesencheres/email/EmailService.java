package com.rie.maisondesencheres.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
	
	private final JavaMailSender mail_sender;

	@Override
	@Async
	public void send(String to, String email) {
		try {
			MimeMessage mime_message = mail_sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mime_message, "utf-8");
			helper.setText(email, true);
			helper.setTo(to);
			helper.setSubject("Confirmez votre compte - maisondesencheres");
			helper.setFrom("donotreply@verschneit.fr");
			mail_sender.send(mime_message);
		} catch (MessagingException e) {
			LOGGER.error("Envoi echoué : ", e);
			throw new IllegalStateException("Envoi echoué");
		}
		
	}

}
