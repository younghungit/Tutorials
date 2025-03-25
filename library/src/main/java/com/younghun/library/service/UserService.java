package com.younghun.library.service;


import com.younghun.library.config.UserForm;
import com.younghun.library.model.User;
import com.younghun.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    
    public Object createUser(UserForm userForm) {
        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setPassword(passwordEncoder.encode(userForm.getPassword1()));
        userRepository.save(user);
        return null;
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
