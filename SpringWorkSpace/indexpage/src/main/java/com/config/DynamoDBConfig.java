package com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
 
@Configuration
//@EnableDynamoDBRepositories(basePackages = "com.CRUD")
//@EnableDynamoDBRepositories(basePackageClasses = ClassesRepository.class)
public class DynamoDBConfig {
 
  @Value("${amazon.aws.accesskey}")
  private String accessKey;
 
  @Value("${amazon.aws.secretkey}")
  private String secretKey;
 
  @Value("${amazon.aws.region}")
  private String awsRegion;
  
  @Value("${amazon.dynamodb.endpoint}")
  private String dBEndpoint;
  
  @Bean
  public AWSCredentials awsCredentials() {
    return new BasicAWSCredentials(accessKey, secretKey);
  }
  
  public AWSCredentialsProvider amazonAWSCredentialsProvider() {
		return new AWSStaticCredentialsProvider(awsCredentials());
	}
  
  @Bean 
  public AmazonDynamoDB amazonDynamoDB(AWSCredentials awsCredentials) {
	  return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
				.withRegion(Regions.AP_SOUTH_1).build();
  }
  
  @Bean
  public DynamoDB dynamoDB(AmazonDynamoDB amazonDynamoDB) {
    return new DynamoDB(amazonDynamoDB);
  }
  
  @Bean
	public DynamoDBMapperConfig dynamoDBMapperConfig() {
		return DynamoDBMapperConfig.DEFAULT;
	}
  
  @Bean
  public DynamoDBMapper dynamoDBMapper() {
	  return new DynamoDBMapper(amazonDynamoDBConfig());
  }
//	public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig config) {
//		return new DynamoDBMapper(amazonDynamoDB, config);
//	}
  
  public AmazonDynamoDB amazonDynamoDBConfig() {
		return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("dynamodb.ap-south-1.amazonaws.com", "ap-south-1"))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("AKIAXDRLXRRHY6UF36XD", "22MuZphksqrGGVJ4TTFHeiANYmG4dM4+UpnulNrS")))
				.build();
	}
//  @Bean
//  public ClassesRepository classesRepository() {
//	  return new ClassesRepository();
//  }
}
