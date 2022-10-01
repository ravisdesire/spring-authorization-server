/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auth.demo.service;

import com.auth.demo.persistence.model.AuthorizationConsent;
import com.auth.demo.persistence.repository.AuthorizationConsentRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *
 * @author globaliware
 */
@Component
public class JpaOAuth2AuthorizationConsentService implements OAuth2AuthorizationConsentService{
    
    private final AuthorizationConsentRepository authorizationConsentRepository;
    private final RegisteredClientRepository registeredClientRepository;
    
    @Autowired
    public JpaOAuth2AuthorizationConsentService(AuthorizationConsentRepository authorizationConsentRepository, RegisteredClientRepository registeredClientRepository) {
        this.authorizationConsentRepository = authorizationConsentRepository;
        this.registeredClientRepository = registeredClientRepository;
    }
    

    @Override
    public void save(OAuth2AuthorizationConsent authorizationConsent) {
        this.authorizationConsentRepository.save(toEntity(authorizationConsent));
    }

    @Override
    public void remove(OAuth2AuthorizationConsent authorizationConsent) {
        this.authorizationConsentRepository.deleteByRegisteredClientIdAndPrincipalName(authorizationConsent.getRegisteredClientId(), authorizationConsent.getPrincipalName());
    }

    @Override
    public OAuth2AuthorizationConsent findById(String registeredClientId, String principalName) {
        return this.authorizationConsentRepository.findByRegisteredClientIdAndPrincipalName(registeredClientId, principalName).map(this::toObject).orElse(null);
    }
    
    private OAuth2AuthorizationConsent toObject(AuthorizationConsent authorizationConsent) {
        String registeredClientId = authorizationConsent.getRegisteredClientId();
        RegisteredClient registeredClient = this.registeredClientRepository.findById(registeredClientId);
        if (registeredClient == null) {
                throw new DataRetrievalFailureException(
                                "The RegisteredClient with id '" + registeredClientId + "' was not found in the RegisteredClientRepository.");
        }

        OAuth2AuthorizationConsent.Builder builder = OAuth2AuthorizationConsent.withId(registeredClientId, authorizationConsent.getPrincipalName());
        
        if (authorizationConsent.getAuthorities() != null) {
            for (String authority : StringUtils.commaDelimitedListToSet(authorizationConsent.getAuthorities())) {
               builder.authority(new SimpleGrantedAuthority(authority));
            }
        }

        return builder.build();
    }
    
    private AuthorizationConsent toEntity(OAuth2AuthorizationConsent authorizationConsent) {
        AuthorizationConsent entity = new AuthorizationConsent();
        entity.setRegisteredClientId(authorizationConsent.getRegisteredClientId());
        entity.setPrincipalName(authorizationConsent.getPrincipalName());

        Set<String> authorities = new HashSet<>();
        for (GrantedAuthority authority : authorizationConsent.getAuthorities()) {
            authorities.add(authority.getAuthority());
        }
        entity.setAuthorities(StringUtils.collectionToCommaDelimitedString(authorities));
        return entity;
    }
    
}
