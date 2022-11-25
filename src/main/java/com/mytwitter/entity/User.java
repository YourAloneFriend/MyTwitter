package com.mytwitter.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "login_unique", columnNames = {"login"}),
        @UniqueConstraint(name = "email_unique", columnNames = {"email"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Setter(AccessLevel.PRIVATE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "user_id")),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "role_id")))
    private Collection<Role> roles;

    public User(){
        createdAt = LocalDateTime.now();
    }

    public User(String login, String email, String password, List<Role> roles){
        this();
        this.login = login;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
