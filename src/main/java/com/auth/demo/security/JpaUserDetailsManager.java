/*
 * Copyright 2022 Global Internetware IT Solutions
 * https://www.globaliware.com
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
    public void createUser(UserDetails user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateUser(UserDetails user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteUser(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean userExists(String username) {
        return this.userRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            return CustomUserPrincipal.create(user.get());
        }
        throw new UsernameNotFoundException(MessageFormat.format("username {0} not found", username));
    }
    
    
    
}
