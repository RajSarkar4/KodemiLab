package com.example.KodemiLabs.Service;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.KodemiLabs.Model.OTP;
import com.example.KodemiLabs.Model.User;
import com.example.KodemiLabs.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class RegisterService {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private OtpService otpService;

    @Autowired
    private JwtService Jwtservice;

    public void register(User request) {

        // Save USER first
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPasswordHash(
                BCrypt.hashpw(request.getPasswordHash(), BCrypt.gensalt())
        );
        user.setActive(true);
        user.setVerified(false);
        user.setRole(user.getRole());

        dynamoDBMapper.save(user);

        otpService.generateRegisterOtp(user.getUserId(), user.getEmail());
    }

    public String verifyOtpAndLogin(String otpCode) {
        OTP otp = otpService.verifyRegisterOtp(otpCode);

        User user = dynamoDBMapper.load(User.class, otp.getUserId());
        user.setVerified(true);
        user.setLastLogin(new Date());
        dynamoDBMapper.save(user);
        otpService.deleteOtp(otp);

        return Jwtservice.generateToken(
                user.getUserId(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}
