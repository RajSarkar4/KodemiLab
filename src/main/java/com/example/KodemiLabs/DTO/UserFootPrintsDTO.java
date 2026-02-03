package com.example.KodemiLabs.DTO;
import lombok.*;
import java.time.Instant;

@Getter
@Setter

public class UserFootPrintsDTO {

    private String sessionId;
    private String sessionToken;
    private String userId;
    private Boolean isActive;
    private Instant loginTime;
    private Instant lastActivity;

}