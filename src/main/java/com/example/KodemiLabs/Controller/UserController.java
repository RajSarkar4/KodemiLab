package com.example.KodemiLabs.Controller;
import com.example.KodemiLabs.Model.User;
import com.example.KodemiLabs.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User request) {
        System.out.println("hitting register controller");
        registerService.register(request);
        return ResponseEntity.ok("OTP sent to email");
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<?> verifyOtp(
            @RequestParam String email,
            @RequestParam String otp
    ) {
        System.out.println("hitting login controller");
        String token = registerService.verifyOtpAndLogin(email, otp);
        return ResponseEntity.ok(token);
    }

    

}
