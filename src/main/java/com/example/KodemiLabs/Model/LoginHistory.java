package com.example.KodemiLabs.Model;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;
import java.time.Instant;

public class LoginHistory {

    private String userId;
    private Instant loginAttemptTime;
    private String loginStatus;
    private String ipAddress;
    private String userAgent;

    @DynamoDbPartitionKey
    public String getUserId() {
        return userId;
    }

    @DynamoDbSortKey
    public Instant getLoginAttemptTime() {
        return loginAttemptTime;
    }

}
