package com.example.KodemiLabs.Service;

import com.example.KodemiLabs.DTO.UserDTO;
import com.example.KodemiLabs.Model.OTP;
import com.example.KodemiLabs.Repository.OTPRepo;
import com.example.KodemiLabs.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OTPService {

    @Autowired
    private OTPRepo otpRepo;

    private static final int OTP_EXPIRY_MINUTES = 5;
//    private final Map<String, OTP> otpStore = new ConcurrentHashMap<>();
    private final Random random = new Random();

    public void generateOtp(String userId) {
        String gotp = String.format("%06d", random.nextInt(999999));
        OTP otp = new OTP(gotp, userId, LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES), true);
        otpRepo.newOtp(otp);
    }

        public boolean verifyOtp(String email, String otp) {
            UserDTO.setUser
            OTP otpData = otpRepo.getOtpData(userId);
            if (otpData == null) return false;
            if (LocalDateTime.now().isAfter(otpData.getExpireAt())) {
                otpRepo.removeOtp(otpData);
                return false;
            }

            boolean isValid = otpData.getOtpCode().equals(otp);
            if (isValid) {
                otpRepo.removeOtp(otpData); // one-time use
            }
            return isValid;
        }
}
