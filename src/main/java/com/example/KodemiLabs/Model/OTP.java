package com.example.KodemiLabs.Model;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@DynamoDBTable(tableName = "OTP")
@NoArgsConstructor
@Setter
public class OTP {

    private String otpCode;
    private String userId;
    private String purpose;
    private Date expireAt;
    private boolean isUsed;
    private Date verifiedAt;

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

    @DynamoDBAttribute(attributeName = "purpose")
    public String getPurpose() {
        return purpose;
    }

    @DynamoDBAttribute(attributeName = "expireAt")
    public Date getExpireAt() {
        return expireAt;
    }

    @DynamoDBAttribute(attributeName = "isUsed")
    public boolean isUsed() {
        return isUsed;
    }

    @DynamoDBAttribute(attributeName = "verifiedAt")
    public Date getVerifiedAt() {
        return verifiedAt;
    }
}
