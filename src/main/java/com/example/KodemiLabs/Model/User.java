package com.example.KodemiLabs.Model;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;
import java.util.Date;

@DynamoDbBean
@DynamoDBTable(tableName = "user")
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String userId;
    private String name;
    private String email;
    private String username;
    private String passwordHash;
    private boolean isActive;
    private boolean isVerified;
    private Date lastLogin;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("userId")
    public String getUserId() {
        return userId;
    }

    @DynamoDbAttribute("Username")
    public String getName() {
        return name;
    }

    @DynamoDbAttribute("email")
    public String getEmail() {
        return email;
    }

    @DynamoDbAttribute("username")
    public String getUsername() {
        return username;
    }

    @DynamoDbAttribute("passwordHash")
    public String getPasswordHash() {
        return passwordHash;
    }

    @DynamoDbAttribute("isActive")
    public boolean isActive() {
        return isActive;
    }

    @DynamoDbAttribute("isVerified")
    public boolean isVerified() {
        return isVerified;
    }

    @DynamoDbAttribute("lastLogin")
    public Date getLastLogin() {
        return lastLogin;
    }
}
