package com.example.KodemiLabs.Service;
import com.example.KodemiLabs.Model.User;
import com.example.KodemiLabs.Repository.UserRepo;
import com.example.KodemiLabs.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RegisterService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OTPService otpService;

    @Autowired
    private JwtService jwtService;

    public void register(User request) {

        if (userRepo.getUserByEmail(request.getEmail()) != null) {
            throw new RuntimeException("Email already registered");
        }

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
        user.setRole(Role.LEARNER);
        user.setLastLogin(null);

        userRepo.save(user);
        otpService.generateOtp(user.getUserId());
    }

    public String verifyOtpAndLogin(String email, String otpCode) {

        otpService.verifyOtp(email, otpCode);

        User user = userRepo.getUserByEmail(email);
        user.setVerified(true);
        user.setLastLogin(Instant.now().toString());

        userRepo.save(user);

        return jwtService.generateToken(
                user.getUserId(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}
