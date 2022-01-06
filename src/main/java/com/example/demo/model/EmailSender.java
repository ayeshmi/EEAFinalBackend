package com.example.demo.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.File;


@Component
public class EmailSender {
	
	@Autowired
	private JavaMailSender javaMailSender;

	
	 public void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("domsellanka@gmail.com", "domsellanka@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello Customer \n You are successfully registered into the system");

        javaMailSender.send(msg);

    }
	 
	 public void sendEmailOrderCompleted() {

	        SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo("domsellanka@gmail.com", "domsellanka@gmail.com");

	        msg.setSubject("Testing from Spring Boot");
	        msg.setText("Order is completed.");

	        javaMailSender.send(msg);

	    }
	 
	 public void sendEmailOrderCancelation() {

	        SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo("domsellanka@gmail.com", "domsellanka@gmail.com");

	        msg.setSubject("Testing from Spring Boot");
	        msg.setText("Order is cancel.");

	        javaMailSender.send(msg);

	    }
	 
	 public void sendEmailContactUs(String email,String answer) {
		 
	        SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo("domsellanka@gmail.com",email);

	        msg.setSubject("Answer for Problems");
	        msg.setText(answer);

	       
	        
	        javaMailSender.send(msg);

	    }

	 

	 public void sendMailWithAttachment(String to, String subject, String body, String fileToAttach)
	 {
	 	MimeMessagePreparator preparator = new MimeMessagePreparator()
	 	{
	         public void prepare(MimeMessage mimeMessage) throws Exception
	         {
	             mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
	             mimeMessage.setFrom(new InternetAddress("domsellanka@gmail.com"));
	             mimeMessage.setSubject(subject);
	             mimeMessage.setText(body);

	             FileSystemResource file = new FileSystemResource(new File("C:\\Users\\ayesh\\a.txt"));
	             MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	             helper.addAttachment("logo.jpg", file);
	         }
	     };

	     try {
	    	 javaMailSender.send(preparator);
	     }
	     catch (MailException ex) {
	         // simply log it and go on...
	         System.err.println(ex.getMessage());
	     }
	 }

}
