package com.mytwitter.service.impl;

import com.mytwitter.dto.UserDTO;
import com.mytwitter.entity.Role;
import com.mytwitter.entity.Roles;
import com.mytwitter.entity.User;
import com.mytwitter.repository.RoleRepository;
import com.mytwitter.repository.UserRepository;
import com.mytwitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserDTO userDTO) {
        Role role = roleRepository.findRoleByName(Roles.USER.getRoleName());
        if(role == null)
            role = new Role(Roles.USER.getRoleName());
        System.out.println(userDTO);
        User user = new User(userDTO.getLogin(), userDTO.getEmail(), passwordEncoder.encode(userDTO.getPassword()), Arrays.asList(role));
        return userRepository.save(user);
    }

    @Override
    public User findUserByLogin(UserDTO userDTO){
        return userRepository.findUserByLogin(userDTO.getLogin());
    }

    @Override
    public User findUserByEmail(UserDTO userDTO){
        return userRepository.findUserByEmail(userDTO.getEmail());
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findUserByEmail(username);
        if(user == null)
            throw new UsernameNotFoundException("Invalid email or password!");
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthority(user.getRoles()));
    }

    public Collection<? extends GrantedAuthority> mapRolesToAuthority(Collection<Role> roles){
        return roles.stream().map(x -> new SimpleGrantedAuthority(x.getName())).collect(Collectors.toList());
    }
}
