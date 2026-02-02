package com.example.KodemiLabs.Model;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;
import java.util.Date;

@DynamoDBTable(tableName = "OTP")
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean


public class OTP {

    private Long otpCode;
    private String userId;
    private String purpose;
    private Date expireAt;
    private boolean isUsed;
    private Date verifiedAt;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("otpCode")
    public Long getOtpCode() {
        return otpCode;
    }
    @DynamoDbSecondaryPartitionKey(indexNames = "userId-index")
    @DynamoDbAttribute("userId")
    public String getUserId() {
        return userId;
    }


    @DynamoDbAttribute("purpose")
    public String getPurpose() {
        return purpose;
    }


    @DynamoDbAttribute("expireAt")
    public Date getExpireAt() {
        return expireAt;
    }


    @DynamoDbAttribute("isUsed")
    public boolean isUsed() {
        return isUsed;
    }

    @DynamoDbAttribute("verifiedAt")
    public Date getVerifiedAt() {
        return verifiedAt;
    }

}