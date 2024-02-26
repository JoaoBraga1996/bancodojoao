package com.joaofelipebraga.bancodojoao.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.joaofelipebraga.bancodojoao.entities.records.Email;

@Service
public class EmailService {
	
	 private final JavaMailSender mailSender;
    
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    public void sendEmail(Email email) {
        var message = new SimpleMailMessage();
        message.setFrom("joaofelipebraga7@gmail.com");
        message.setTo(email.to());
        message.setSubject(email.subject());
        message.setText(email.body());
        
        mailSender.send(message); // This line sends the email
    }

	public JavaMailSender getMailSender() {
		return mailSender;
	}
    
    
    
}


