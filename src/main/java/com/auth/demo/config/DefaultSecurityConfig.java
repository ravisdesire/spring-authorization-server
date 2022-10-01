/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auth.demo.config;

import com.auth.demo.security.JpaUserDetailsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author globaliware
 */
@EnableWebSecurity
public class DefaultSecurityConfig {
    
    private final PasswordEncoder passwordEncoder;
    private final JpaUserDetailsManager jpaUserDetailsManager;
    
    @Autowired
    public DefaultSecurityConfig(PasswordEncoder passwordEncoder, JpaUserDetailsManager jpaUserDetailsManager) {
        this.passwordEncoder = passwordEncoder;
        this.jpaUserDetailsManager = jpaUserDetailsManager;
    }
    
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated()).formLogin(withDefaults());
        return http.build();
    }
    
    @Bean
    public DaoAuthenticationProvider jpaDaoAuthenticationProvider() {
      DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
      daoAuthenticationProvider.setUserDetailsService(jpaUserDetailsManager);
      daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
      return daoAuthenticationProvider;
    }
    
    
    
    
    
    
}
