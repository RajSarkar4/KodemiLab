package com.example.KodemiLabs.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.KodemiLabs.Model.OTP;
import com.example.KodemiLabs.Model.User;
import com.example.KodemiLabs.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private OTPService otpService;

    @Autowired
    private JwtService jwtService;

    public void register(User request) {

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

        otpService.generateOtp(user.getUserId());
    }

    public String verifyOtpAndLogin(String email, String otpCode) {

        String otp = otpService.verifyOtp(email, otpCode);
        User user = userRepo.getUserByEmail(email);
        dynamoDBMapper.save(user);
        return jwtService.generateToken(
                user.getUserId(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}
