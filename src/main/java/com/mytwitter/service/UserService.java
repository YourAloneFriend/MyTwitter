package com.mytwitter.service;

import com.mytwitter.dto.UserDTO;
import com.mytwitter.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User createUser(UserDTO userDTO);
    User findUserByLogin(UserDTO userDTO);
    User findUserByEmail(UserDTO userDTO);
}
