package com.example.KodemiLabs.Model;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

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

    @DynamoDbPartitionKey
    public String getUserId() {
        return learnerId;
    }
}
