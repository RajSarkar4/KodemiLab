package com.example.KodemiLabs.Model;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import com.example.KodemiLabs.enums.Role;
import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.time.LocalDateTime;
import java.util.Date;


@DynamoDBTable(tableName = "user")
@DynamoDbBean
@NoArgsConstructor
@Setter
public class User {

    private String userId;
    private String name;
    private String email;
    private String username;
    private String passwordHash;
    private boolean isActive;
    private boolean isVerified;
    private LocalDateTime lastLogin;
    private Role role;

    @DynamoDbPartitionKey
    @DynamoDBHashKey(attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    @DynamoDBAttribute(attributeName = "email")
    public String getEmail() {
        return email;
    }

    @DynamoDBAttribute(attributeName = "username")
    public String getUsername() {
        return username;
    }

    @DynamoDBAttribute(attributeName = "passwordHash")
    public String getPasswordHash() {
        return passwordHash;
    }

    @DynamoDBAttribute(attributeName = "isActive")
    public boolean isActive() {
        return isActive;
    }

    @DynamoDBAttribute(attributeName = "isVerified")
    public boolean isVerified() {
        return isVerified;
    }

    @DynamoDBAttribute(attributeName = "lastLogin")
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }
    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "role")
    public Role getRole() {
        return role;
    }

}
