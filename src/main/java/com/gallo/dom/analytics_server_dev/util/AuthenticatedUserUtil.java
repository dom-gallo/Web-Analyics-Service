package com.gallo.dom.analytics_server_dev.util;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUserUtil {

    public AuthenticatedUserUtil(){}

    public String getEmailFromContext(Authentication authentication){
        Claims claims = (Claims) authentication.getPrincipal();
        String emailAddress = claims.getSubject();
        return emailAddress;
    }
}
