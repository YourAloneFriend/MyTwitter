package com.mytwitter.dto;

import com.mytwitter.annotations.ValidEmail;
import lombok.Data;

@Data
public class UserDTO {

    private String login;
    @ValidEmail
    private String email;
    private String password;
    private String matchingPassword;
}
