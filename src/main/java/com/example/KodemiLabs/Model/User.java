package com.example.KodemiLabs.Model;
import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;
import java.util.Date;


@Data
@DynamoDbBean
@Getter
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
    public String getUserId() {
        return userId;
    }
}
