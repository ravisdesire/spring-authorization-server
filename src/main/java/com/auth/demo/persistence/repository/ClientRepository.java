package com.auth.demo.persistence.repository;

import com.auth.demo.persistence.model.Client;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author globaliware
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, String>{
    
    Optional<Client> findByClientId(String clientId);
    
}
