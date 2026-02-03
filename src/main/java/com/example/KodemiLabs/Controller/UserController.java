package com.example.KodemiLabs.Controller;
import com.example.KodemiLabs.Model.User;
import com.example.KodemiLabs.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody User user, HttpServletRequest request){

        userService.register(user, getSiteURL(request));

        return ResponseEntity.ok("User Added Successfully");
    }


    

}
