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
