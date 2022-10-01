/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auth.demo.persistence.repository;

import com.auth.demo.persistence.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author globaliware
 */
public interface UserRepository extends JpaRepository<User, Long>{
    
    Optional<User> findByUsername(String username);
    
}
