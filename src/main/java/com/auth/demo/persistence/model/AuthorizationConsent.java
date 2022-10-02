package com.auth.demo.persistence.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import lombok.Data;

/**
 *
 * @author globaliware
 */
@Entity
@Data
@IdClass(AuthorizationConsent.AuthorizationConsentId.class)
public class AuthorizationConsent implements Serializable{
    
    @Id
    private String registeredClientId;
    
    @Id
    private String principalName;
    
    @Column(length = 1000)
    private String authorities;
    
    public static class AuthorizationConsentId implements Serializable {
        private String registeredClientId;
        private String principalName;
        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                AuthorizationConsentId that = (AuthorizationConsentId) o;
                return registeredClientId.equals(that.registeredClientId) && principalName.equals(that.principalName);
        }

        @Override
        public int hashCode() {
                return Objects.hash(registeredClientId, principalName);
        }
    }
    
}
