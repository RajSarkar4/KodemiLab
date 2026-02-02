package com.example.KodemiLabs.Model;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.util.Date;
import java.util.Timer;

@DynamoDbBean
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OTP {
    private Long otpCode;
    private String purpose;
    private Timer expire;
    private boolean isUsed;
    private Date verifiedAt;
}
