package com.example.KodemiLabs.Config;
import com.amazonaws.auth.*;
import com.amazonaws.client.builder.*;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;


@Configuration
public class DynamoDBConfig {

    @Value("${dynamodb.access-key}")
    private String dynamodbAccessKey;

    @Value("${dynamodb.secret-access-key}")
    private String dynamodbSecretAccessKey;

    @Value("${dynamodb.endpoint}")
    private String dynamodbEndpoint;

    @Value("${dynamodb.region}")
    private String dynamodbRegion;

    @Bean
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {
        return new DynamoDBMapper(amazonDynamoDB);
    }
    protected AmazonDynamoDB buildAmazonDynamoDB() {

        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                dynamodbEndpoint,   // http://localhost:8000
                                dynamodbRegion      // ap-south-1 (any value)
                        ))
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(
                                        dynamodbAccessKey,
                                        dynamodbSecretAccessKey
                                )
                        )
                )
                .build();
    }
}
