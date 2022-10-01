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
public class Client {
    
    @Id
    private String id;
    
    @Column(unique = true)
    private String clientId;
    
    private Instant clientIdIssuedAt;
    
    private String clientSecret;
    
    private Instant clientSecretExpiresAt;
    
    private String clientName;
    
    @Column(length = 1000)
    private String clientAuthenticationMethods;
    
    @Column(length = 1000)
    private String authorizationGrantTypes;
    
    @Column(length = 1000)
    private String redirectUris;
    
    @Column(length = 1000)
    private String scopes;
    
    @Column(length = 2000)
    private String clientSettings;
    
    @Column(length = 2000)
    private String tokenSettings;
    
}
