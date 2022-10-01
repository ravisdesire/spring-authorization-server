/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auth.demo.security;

import com.auth.demo.persistence.model.User;
import com.auth.demo.persistence.repository.UserRepository;
import java.text.MessageFormat;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

/**
 *
 * @author globaliware
 */
@Service
public class JpaUserDetailsManager implements UserDetailsManager{
    
    private final UserRepository userRepository;

    @Autowired
    public JpaUserDetailsManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            return UserPrincipal.create(user.get());
        }
        throw new UsernameNotFoundException(MessageFormat.format("username {0} not found", username));
    }

    @Override
    public void createUser(UserDetails userDetails) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateUser(UserDetails userDetails) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteUser(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
    
}
