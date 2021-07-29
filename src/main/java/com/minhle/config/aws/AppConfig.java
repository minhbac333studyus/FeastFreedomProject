package com.minhle.config.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration

public class AppConfig {
	@Value("${amazon.dynamodb.endpoint}") 
	private String dynamodbEndpoint;

	@Value("${amazon.aws.accesskey}")
	private String dynamodbAccessKey;
	@Value("${amazon.aws.secretkey}")
	private String dynamodbSecretKey;
	 @Value("${amazon.aws.region}")
    private String awsRegion;
	 

 	

    private AmazonDynamoDB buildAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                   new AwsClientBuilder.EndpointConfiguration(dynamodbEndpoint,awsRegion))
                .withCredentials(new AWSStaticCredentialsProvider(
                   new BasicAWSCredentials(dynamodbAccessKey,dynamodbSecretKey)))
                .build();
    }
    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(buildAmazonDynamoDB());
    }

 
}
