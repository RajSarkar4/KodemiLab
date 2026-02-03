package com.example.KodemiLabs.Service;

import com.example.KodemiLabs.Model.OTP;
import com.example.KodemiLabs.Model.User;
import com.example.KodemiLabs.Repository.OTPRepo;
import com.example.KodemiLabs.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OTPService {

    @Autowired
    private OTPRepo otpRepo;
    @Autowired
    private  UserRepo userRepo;

    private static final int OTP_EXPIRY_MINUTES = 5;
//    private final Map<String, OTP> otpStore = new ConcurrentHashMap<>();
    private final Random random = new Random();

    public void generateOtp(String userId) {
        String gotp = String.format("%06d", random.nextInt(999999));
        OTP otp = new OTP(gotp, userId, LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES), true);
        otpRepo.newOtp(otp);
    }

        public String verifyOtp(String email, String otp) {

            User user = userRepo.getUserByEmail(email);
            if(user == null) throw new RuntimeException("User not Exist");
            OTP otpData = otpRepo.getOtpData(user.getUserId());
            if (otpData == null) throw new RuntimeException("Internal Error");
            if (LocalDateTime.now().isAfter(otpData.getExpireAt())) {
                otpRepo.removeOtp(otpData);
                return "OTP Expired";
            }

            boolean isValid = otpData.getOtpCode().equals(otp);
            if (isValid) {
                otpRepo.removeOtp(otpData);
                return "OTP Verified Successfully";// one-time use
            }else{
                return "OTP Incorrect";
            }

        }
}
