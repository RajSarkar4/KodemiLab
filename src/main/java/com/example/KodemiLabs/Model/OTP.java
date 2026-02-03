package com.example.KodemiLabs.Model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@DynamoDBTable(tableName = "OTP")
@Data
@NoArgsConstructor
public class OTP {

    private String otpCode;
    private String userId;
    private Long expireAt;
    private boolean enable;

    @DynamoDBIndexHashKey(
            attributeName = "otpCode",
            globalSecondaryIndexName = "otpcode-index"
    )
    public String getOtpCode() {
        return otpCode;
    }

    @DynamoDBHashKey(attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    @DynamoDBAttribute
    public Long getExpireAt() {
        return expireAt;
    }

    @DynamoDBAttribute(attributeName = "isUsed")
    public boolean isEnable() {
        return enable;
    }
}
