package com.example.KodemiLabs.Service;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.KodemiLabs.Model.OTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;


@Service
public class OtpService {

    private static final int EXPIRY_MINUTES = 5;

    @Autowired
    private DynamoDBMapper dynamoDBMapper;
//
//    @Autowired
//    private EmailService emailService;

    public void generateRegisterOtp(String userId, String email) {

        String otpCode = generateOtp();

        OTP otp = new OTP();
        otp.setOtpCode(otpCode);
        otp.setUserId(userId);
        otp.setPurpose("REGISTER");
        otp.setUsed(false);
        otp.setExpireAt(
                Date.from(
                        Instant.now().plus(EXPIRY_MINUTES, ChronoUnit.MINUTES)
                )
        );

        dynamoDBMapper.save(otp);
//        emailService.sendRegisterOtp(email, otpCode);
    }

    public OTP verifyRegisterOtp(String otpCode) {

        OTP otp = dynamoDBMapper.load(OTP.class, otpCode);

        if (otp == null)
            throw new RuntimeException("Invalid OTP");

        if (otp.isUsed())
            throw new RuntimeException("OTP already used");

        if (otp.getExpireAt().before(new Date()))
            throw new RuntimeException("OTP expired");

        otp.setUsed(true);
        otp.setVerifiedAt(new Date());

        return otp;
    }

    public void deleteOtp(OTP otp) {
        dynamoDBMapper.delete(otp);
    }

    private String generateOtp() {
        return String.format("%06d", new SecureRandom().nextInt(1_000_000));
    }
}
