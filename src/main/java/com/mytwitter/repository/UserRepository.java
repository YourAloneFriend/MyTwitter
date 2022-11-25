package com.mytwitter.repository;

import com.mytwitter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select distinct u from User u left join fetch u.roles where u.email = :email")
    User findUserByEmail(String email);
    @Query("select distinct u from User u left join fetch u.roles where u.login = :login")
    User findUserByLogin(String login);
}
