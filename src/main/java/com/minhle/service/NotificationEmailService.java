//package com.minhle.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//@Service
//public class NotificationEmailService {
//	@Autowired
//    private MailSender mailSender;
//	@Autowired
//	private JavaMailSender javaMailSender;
//	
//    public void sendMailMessage(   final SimpleMailMessage simpleMailMessage) { 
//        this.mailSender.send(simpleMailMessage);
//    }
//    @Async
//    public void sendEmail(SimpleMailMessage email) {
//        javaMailSender.send(email);
//    }
//}
