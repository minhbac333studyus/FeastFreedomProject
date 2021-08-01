package com.minhle.config.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;

import io.awspring.cloud.ses.SimpleEmailServiceMailSender;
 
@Configuration

public class AppConfig {
	@Value("${amazon.dynamodb.endpoint}") 
	private String dynamodbEndpoint;
	@Value("${amazon.ses.endpoint}")
	private String sesEndpoint;
	@Value("${amazon.aws.accesskey}")
	private String dynamodbAccessKey;
	@Value("${amazon.aws.secretkey}")
	private String dynamodbSecretKey;
	 @Value("${amazon.aws.region}")
    private String awsRegion; 
	 
 /*
  * DYNAMODB Initualize...
  */
    private AmazonDynamoDB buildAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                   new AwsClientBuilder.EndpointConfiguration(dynamodbEndpoint,awsRegion))
                .withCredentials(new AWSStaticCredentialsProvider(
                   new BasicAWSCredentials(dynamodbAccessKey,dynamodbSecretKey)))
                .build();
    }
    DynamoDBMapperConfig dynamoDBMapperConfig = new DynamoDBMapperConfig.Builder()
  		  .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
  		  .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES)
  		  .build();  
    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(buildAmazonDynamoDB(),dynamoDBMapperConfig);
    }
   
}
