/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auth.demo.persistence.model;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author globaliware
 */
@Entity
@Data
public class Authorization {
    
    @Id
    private String id;
    
    private String registeredClientId;
    
    private String principalName;
    
    private String authorizationGrantType;
    
    @Column(columnDefinition="TEXT")
    private String attributes;
    
    @Column(length = 500)
    private String state;
    
    @Column(columnDefinition="TEXT")
    private String authorizationCodeValue;
        
    private Instant authorizationCodeIssuedAt;
    private Instant authorizationCodeExpiresAt;
        
    @Column(columnDefinition="TEXT")
    private String authorizationCodeMetadata;

    @Column(columnDefinition="TEXT")
    private String accessTokenValue;
        
    private Instant accessTokenIssuedAt;
    private Instant accessTokenExpiresAt;
	
    @Column(columnDefinition="TEXT")
    private String accessTokenMetadata;
	
    private String accessTokenType;

    @Column(columnDefinition="TEXT")
    private String accessTokenScopes;

    @Column(columnDefinition="TEXT")
    private String refreshTokenValue;

    private Instant refreshTokenIssuedAt;
    private Instant refreshTokenExpiresAt;

    @Column(columnDefinition="TEXT")
    private String refreshTokenMetadata;

    @Column(columnDefinition="TEXT")
    private String oidcIdTokenValue;
    private Instant oidcIdTokenIssuedAt;
    private Instant oidcIdTokenExpiresAt;

    @Column(columnDefinition="TEXT")
    private String oidcIdTokenMetadata;
    @Column(columnDefinition="TEXT")
    private String oidcIdTokenClaims;
    
}
