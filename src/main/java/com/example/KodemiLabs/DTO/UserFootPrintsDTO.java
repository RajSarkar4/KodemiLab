package com.example.KodemiLabs.DTO;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;
import java.time.Instant;

@Data
@DynamoDBTable(tableName = "FootPrints")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class UserFootPrintsDTO {

    private String sessionId;
    private String sessionToken;
    private String userId;
    private Boolean isActive;
    private Instant loginTime;
    private Instant lastActivity;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("sessionId")
    public String getSessionId() {
        return sessionId;
    }
    @DynamoDbSecondaryPartitionKey(indexNames = "userId-index")
    @DynamoDbAttribute("userId")
    public String getUserId() {
        return userId;
    }
    @DynamoDbAttribute("sessionToken")
    public String getSessionToken() {
        return sessionToken;
    }
    @DynamoDbAttribute("isActive")
    public Boolean getIsActive() {
        return isActive;
    }

    @DynamoDbAttribute("loginTime")
    public Instant getLoginTime() {
        return loginTime;
    }

    @DynamoDbAttribute("lastActivity")
    public Instant getLastActivity() {
        return lastActivity;
    }

}