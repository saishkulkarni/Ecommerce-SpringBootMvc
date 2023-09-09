package com.mycompany.ecommerce.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mycompany.ecommerce.dto.Merchant;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailHelper {
	
	@Autowired
	JavaMailSender mailSender;

	public void sendOtp(Merchant merchant) {
		MimeMessage mimeMessage=mailSender.createMimeMessage();
		MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage);
		
		try {
			messageHelper.setTo(merchant.getEmail());
			messageHelper.setFrom("E-Commerce");
			messageHelper.setSubject("OTP Verification");
			String body="<h1 style='color:blue'>Hello "+merchant.getName()+",<br>Your Otp is : "+merchant.getOtp()+"</h1>";
			messageHelper.setText(body,true);
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
}
