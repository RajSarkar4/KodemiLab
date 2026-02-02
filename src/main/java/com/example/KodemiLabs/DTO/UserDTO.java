package com.example.KodemiLabs.DTO;
import com.example.KodemiLabs.enums.Role;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter

public class UserDTO {

    private String userId;
    private String name;
    private String email;
    private String username;
    private boolean isActive;
    private boolean isVerified;
    private Date lastLogin;
    private Role role;


}
