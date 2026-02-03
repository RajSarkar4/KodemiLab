package com.example.KodemiLabs.Service;
import com.example.KodemiLabs.Model.OTP;
import com.example.KodemiLabs.Model.User;
import com.example.KodemiLabs.Repository.OTPRepo;
import com.example.KodemiLabs.Repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class OTPService {

    private static final int OTP_EXPIRY_MINUTES = 5;
    private final Random random = new Random();

    @Autowired
    private EmailService emailService;

    @Autowired
    private OTPRepo otpRepo;

    @Autowired
    private UserRepo userRepo;

    public void generateOtp(String userId) {

        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }

        User user = userRepo.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User not found for OTP");
        }

        String rawOtp = String.format("%06d", random.nextInt(1_000_000));
        String hashedOtp = BCrypt.hashpw(rawOtp, BCrypt.gensalt());

        OTP otp = new OTP();
        otp.setUserId(userId);
        otp.setOtpCode(hashedOtp);
        otp.setExpireAt(
                System.currentTimeMillis() + (OTP_EXPIRY_MINUTES * 60 * 1000)
        );
        otp.setEnable(true);

        otpRepo.save(otp);
        emailService.sendOtpEmail(user.getEmail(), rawOtp);
    }

    public void verifyOtp(String email, String otpCode) {

        User user = userRepo.getUserByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        OTP otp = otpRepo.getOtpData(user.getUserId());
        if (otp == null) {
            throw new RuntimeException("OTP not found");
        }

        if (!otp.isEnable()) {
            throw new RuntimeException("OTP already used");
        }

        if (System.currentTimeMillis() > otp.getExpireAt()) {
            otpRepo.delete(otp);
            throw new RuntimeException("OTP expired");
        }

        if (!BCrypt.checkpw(otpCode, otp.getOtpCode())) {
            throw new RuntimeException("Invalid OTP");
        }

        otp.setEnable(false);
        otpRepo.save(otp);
    }
}
