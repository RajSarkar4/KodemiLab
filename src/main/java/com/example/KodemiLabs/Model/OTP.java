package com.example.KodemiLabs.Model;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbIgnoreNulls;

import java.time.LocalDateTime;
import java.util.Date;

@DynamoDBTable(tableName = "OTP")
@NoArgsConstructor
@Setter
public class OTP {

    private String otpCode;
    private String userId;
    private LocalDateTime expireAt;
    private boolean enable;

    public OTP(String gotp, String userId, LocalDateTime localDateTime, boolean enable) {
    }

    @DynamoDBHashKey(attributeName = "otpCode")
    public String getOtpCode() {
        return otpCode;
    }

    @DynamoDBIndexHashKey(
            globalSecondaryIndexName = "userId-index",
            attributeName = "userId"
    )
    public String getUserId() {
        return userId;
    }

    @DynamoDBAttribute(attributeName = "expireAt")
    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    @DynamoDBAttribute(attributeName = "isUsed")
    public boolean isEnable() {
        return enable;
    }
}
