package com.example.KodemiLabs.Model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.example.KodemiLabs.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@DynamoDBTable(tableName = "user")
@Data
@NoArgsConstructor
public class User {

    private String userId;
    private String name;
    private String email;
    private String username;
    private String passwordHash;
    private boolean active;
    private boolean verified;
    private String lastLogin;
    private Role role;

    @DynamoDBHashKey(attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    @DynamoDBAttribute
    public String getName() {
        return name;
    }

    @DynamoDBIndexHashKey(
            globalSecondaryIndexName = "email-index",
            attributeName = "email"
    )
    public String getEmail() {
        return email;
    }

    @DynamoDBAttribute
    public String getUsername() {
        return username;
    }

    @DynamoDBAttribute
    public String getPasswordHash() {
        return passwordHash;
    }

    @DynamoDBAttribute
    public boolean isActive() {
        return active;
    }

    @DynamoDBAttribute
    public boolean isVerified() {
        return verified;
    }

    @DynamoDBAttribute
    public String getLastLogin() {
        return lastLogin;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute
    public Role getRole() {
        return role;
    }
}
