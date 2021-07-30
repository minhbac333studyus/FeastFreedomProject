package com.minhle.config.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;

import io.awspring.cloud.ses.SimpleEmailServiceMailSender;
@Configuration

public class EmailConfig {
	
	@Value("${amazon.ses.endpoint}")
	private String sesEndpoint;
	@Value("${amazon.aws.accesskey}")
	private String dynamodbAccessKey;
	@Value("${amazon.aws.secretkey}")
	private String dynamodbSecretKey;
	 @Value("${amazon.aws.region}")
    private String awsRegion; 
 
	 
	 /*
	  * AWS SES Initualize
	  */
	    @Bean
	    public AmazonSimpleEmailService amazonSimpleEmailService() {

	      return AmazonSimpleEmailServiceClientBuilder
	    		  .standard() 
	              .withRegion(awsRegion)
	              .withCredentials(new AWSStaticCredentialsProvider(
	                 new BasicAWSCredentials(dynamodbAccessKey,dynamodbSecretKey)))
	              .build();
	    }
	    
	    @Bean
	    public MailSender mailSender(  AmazonSimpleEmailService amazonSimpleEmailService) {
	      return new SimpleEmailServiceMailSender(amazonSimpleEmailService);
	    }
	    /*
	     * EMAIL CLOUD SPRING Initualize
	     */
	    @Bean
	    public JavaMailSender getJavaMailSender() {
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl(); 
	        return mailSender;
	    }
}
