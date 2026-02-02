package com.example.KodemiLabs.Model;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.util.Date;

@DynamoDbBean
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Learner {

    private String learnerId;
    private String name;
    private String email;
    private String username;
    private String passwordHash;
    private boolean isActive;
    private boolean isVerified;
    private Date lastLogin;

    @DynamoDbPartitionKey
    public String getUserId() {
        return learnerId;
    }
}
