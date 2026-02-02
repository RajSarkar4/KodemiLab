package com.example.KodemiLabs.Model;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@DynamoDBTable(tableName = "LoginHistory")
@NoArgsConstructor
@Setter
public class LoginHistory {

    private String userId;
    private Date loginAttemptTime;
    private String loginStatus;
    private String ipAddress;
    private String userAgent;

    @DynamoDBHashKey(attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    @DynamoDBRangeKey(attributeName = "loginAttemptTime")
    public Date getLoginAttemptTime() {
        return loginAttemptTime;
    }

    @DynamoDBAttribute(attributeName = "loginStatus")
    public String getLoginStatus() {
        return loginStatus;
    }

    @DynamoDBAttribute(attributeName = "ipAddress")
    public String getIpAddress() {
        return ipAddress;
    }

    @DynamoDBAttribute(attributeName = "userAgent")
    public String getUserAgent() {
        return userAgent;
    }
}
