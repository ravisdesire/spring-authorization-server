/*
 * Copyright 2022 Global Internetware IT Solutions
 * https://www.globaliware.com
 */
package com.auth.demo.security;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import java.io.IOException;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author globaliware
 */
public class CustomUserPrincipalDeserializer extends JsonDeserializer<CustomUserPrincipal> {

    @Override
    public CustomUserPrincipal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode jsonNode = mapper.readTree(jsonParser);
        Long id = readJsonNode(jsonNode, "id").asLong();
        Boolean enable = readJsonNode(jsonNode, "enable").asBoolean();
        String username = readJsonNode(jsonNode, "username").asText();
        String password = readJsonNode(jsonNode, "password").asText();
         List<GrantedAuthority> authorities = mapper.readerForListOf(GrantedAuthority.class).readValue(jsonNode.get("authorities"));
        return new CustomUserPrincipal(id, username, password, enable, authorities);
    }
    
    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }
    
}
