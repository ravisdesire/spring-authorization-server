package com.auth.demo.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author globaliware
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity{
    
    @Column(name = "username", nullable = false)
    private String username;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name="role_id")    
    private Role role;
    
    @Column(name = "active")
    private Boolean active;
    
}
