package com.auth.demo.persistence.repository;

import com.auth.demo.persistence.model.Authorization;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author globaliware
 */
@Repository
public interface AuthorizationRepository extends JpaRepository<Authorization, String>{
    
    Optional<Authorization> findByState(String state);
    Optional<Authorization> findByAuthorizationCodeValue(String authorizationCode);
    Optional<Authorization> findByAccessTokenValue(String accessToken);
    Optional<Authorization> findByRefreshTokenValue(String refreshToken);
    @Query("select a from Authorization a where a.state = :token" +
                    " or a.authorizationCodeValue = :token" +
                    " or a.accessTokenValue = :token" +
                    " or a.refreshTokenValue = :token"
    )
    Optional<Authorization> findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValue(@Param("token") String token);
    
}
