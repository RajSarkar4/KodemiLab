package com.example.KodemiLabs.Controller;
import com.example.KodemiLabs.Model.User;
import com.example.KodemiLabs.Service.RegisterService;
import com.example.KodemiLabs.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User request) {
        RegisterService.register(request);
        return ResponseEntity.ok("OTP sent to email");
    }

    

}
