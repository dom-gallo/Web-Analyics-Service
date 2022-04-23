package com.gallo.dom.analytics_server_dev.model;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = 2550185165626007488L;
    private static final long JWT_TOKEN_VALIDITY = 5*60*60;

    @Value("${jwt.secret}")
    private String secret;

    public String getUsernameFromToken(String token){
        return Jwts.claims().getSubject();
    }
}
