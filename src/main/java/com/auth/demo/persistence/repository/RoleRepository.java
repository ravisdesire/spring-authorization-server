package com.auth.demo.persistence.repository;

import com.auth.demo.persistence.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author globaliware
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
    
    Optional<Role> findByName(String name);
    
}
