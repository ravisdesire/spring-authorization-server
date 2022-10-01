/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auth.demo.security;

import com.auth.demo.persistence.model.Role;
import com.auth.demo.persistence.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author globaliware
 */
public class UserPrincipal implements UserDetails{
    
    private String id;
    
    @JsonIgnore
    private String username;
    
    @JsonIgnore
    private String password;
    
    @JsonIgnore
    private Role role;
    
    private boolean enable;

    public UserPrincipal(String id, String username, String password, Role role, boolean enable) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enable = enable;
    }
    
    public static UserPrincipal create(User user){
        return new UserPrincipal(
                user.getId().toString(),
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                user.getActive()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.role.getName()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.enable;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.enable;
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }
    
    
    
    
    
    
    
    
}
