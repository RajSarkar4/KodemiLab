package com.example.KodemiLabs.Model;
import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;
import java.time.Instant;

@Data
@DynamoDbBean
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserFootPrints {

    private String sessionId;
    private String sessionToken;
    private String userId;
    private Boolean isActive;
    private Instant loginTime;
    private Instant lastActivity;

    @DynamoDbPartitionKey
    public String getSessionId() {
        return sessionId;
    }
    @DynamoDbSecondaryPartitionKey(indexNames = "userId-index")
    public String getUserId() {
        return userId;
    }



}
