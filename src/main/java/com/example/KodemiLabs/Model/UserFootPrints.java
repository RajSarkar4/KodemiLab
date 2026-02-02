package com.example.KodemiLabs.Model;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;

@DynamoDBTable(tableName = "FootPrints")
@NoArgsConstructor
@Setter
public class UserFootPrints {

    private String sessionId;
    private String sessionToken;
    private String userId;
    private Boolean isActive;
    private Instant loginTime;
    private Instant lastActivity;

    @DynamoDBHashKey(attributeName = "sessionId")
    public String getSessionId() {
        return sessionId;
    }

    @DynamoDBIndexHashKey(
            globalSecondaryIndexName = "userId-index",
            attributeName = "userId"
    )
    public String getUserId() {
        return userId;
    }

    @DynamoDBAttribute(attributeName = "sessionToken")
    public String getSessionToken() {
        return sessionToken;
    }

    @DynamoDBAttribute(attributeName = "isActive")
    public Boolean getIsActive() {
        return isActive;
    }

    @DynamoDBAttribute(attributeName = "loginTime")
    public Instant getLoginTime() {
        return loginTime;
    }

    @DynamoDBAttribute(attributeName = "lastActivity")
    public Instant getLastActivity() {
        return lastActivity;
    }
}
