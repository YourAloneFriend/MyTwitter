package com.mytwitter.repository;

import com.mytwitter.dto.UserDTO;
import com.mytwitter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);
    User findUserByLogin(String login);
}
