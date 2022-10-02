package com.auth.demo.config;

import com.auth.demo.persistence.model.Client;
import com.auth.demo.persistence.model.Role;
import com.auth.demo.persistence.model.User;
import com.auth.demo.persistence.repository.ClientRepository;
import com.auth.demo.persistence.repository.RoleRepository;
import com.auth.demo.persistence.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *
 * @author globaliware
 */
@Component
public class DatabaseLoader {
    
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ClientRepository clientRepository;
    
    @Autowired
    public DatabaseLoader(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository, ClientRepository clientRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.clientRepository = clientRepository;
    }
    
    @PostConstruct
    void init(){
        clientCreation();
        roleCreation();
    }

    private void clientCreation() {
        Optional<Client> clientOptional = clientRepository.findByClientId("demo-client");
        if(clientOptional.isPresent()) return;
        
        List<String> clientAuthenticationMethods = new ArrayList<>();
        clientAuthenticationMethods.add(ClientAuthenticationMethod.CLIENT_SECRET_BASIC.getValue());
        
        List<String> authorizationGrantTypes = new ArrayList<>();
        authorizationGrantTypes.add(AuthorizationGrantType.AUTHORIZATION_CODE.getValue());
        authorizationGrantTypes.add(AuthorizationGrantType.REFRESH_TOKEN.getValue());
        authorizationGrantTypes.add(AuthorizationGrantType.CLIENT_CREDENTIALS.getValue());
        
        List<String> redirectUri = new ArrayList<>();
            redirectUri.add("http://localhost:9191/login/oauth2/code/demo-client-oidc");
            redirectUri.add("http://localhost:9191/authorized");
            
        List<String> scope = new ArrayList<>();
            scope.add(OidcScopes.OPENID);
            scope.add("demo.read");
            scope.add("demo.write"); 
        
        Client client = new Client();
        client.setId(UUID.randomUUID().toString());
        client.setClientId("demo-client");
        client.setClientSecret(passwordEncoder.encode("demo-client-secret"));
        client.setClientAuthenticationMethods(StringUtils.collectionToCommaDelimitedString(clientAuthenticationMethods));
        client.setAuthorizationGrantTypes(StringUtils.collectionToCommaDelimitedString(authorizationGrantTypes));
        client.setRedirectUris(StringUtils.collectionToCommaDelimitedString(redirectUri));
        client.setScopes(StringUtils.collectionToCommaDelimitedString(scope));
        client.setClientSettings(null);
        client.setTokenSettings(null);
        clientRepository.save(client);    
        
    }

    private void roleCreation() {
        Optional<Role> roleOptional = roleRepository.findByName("ROLE_USER");
        if(roleOptional.isPresent()) return;
        Role role = roleRepository.save(new Role("ROLE_USER"));
        createUser("user", role);
    }

    private void createUser(String username, Role role) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isPresent()) return;
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode("password"));
        user.setRole(role);
        user.setActive(Boolean.TRUE);
        userRepository.save(user);
        
    }

    
    
    
}
