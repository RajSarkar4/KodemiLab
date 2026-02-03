package com.example.KodemiLabs.Service;

import com.example.KodemiLabs.Model.User;
import com.example.KodemiLabs.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;


public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JavaMailSender javaMailSender;


    public void register(User user, String siteURL) {

    }
}
