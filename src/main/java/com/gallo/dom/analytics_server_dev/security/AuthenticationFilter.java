package com.gallo.dom.analytics_server_dev.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gallo.dom.analytics_server_dev.model.User;
import com.gallo.dom.analytics_server_dev.model.response.AuthenticationResponseBody;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;

import static com.gallo.dom.analytics_server_dev.security.SecurityConstants.EXPIRATION_TIME;
import static com.gallo.dom.analytics_server_dev.security.SecurityConstants.KEY;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);


    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        logger.info("Authentication Filter being called, attemptAuthentication");
        try {
            User appUser = new ObjectMapper().readValue(request.getInputStream(),User.class);
            logger.info("Trying to authorize user with username = " + appUser.getUsername() + " and password = " + appUser.getPassword());
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(appUser.getEmailAddress(), appUser.getPassword(), new ArrayList<>())
            );

        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {


        logger.info("Inside successful authentication");
        Date exp = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        Key key = Keys.hmacShaKeyFor(KEY.getBytes());
        Claims claims = Jwts.claims().setSubject(((User) authResult.getPrincipal()).getUsername());
        String token = Jwts.builder().setClaims(claims).claim("TestClaim","HelloWorld").signWith(key, SignatureAlgorithm.HS512).setExpiration(exp).compact();

        response.setHeader("token", token);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Because I couldn't figure cors out. Instead, I'm just writing the authentication token to the body of the request.
        ObjectMapper mapper = new ObjectMapper();
        AuthenticationResponseBody body = new AuthenticationResponseBody(token, "OK");
        String responseBody = mapper.writer().writeValueAsString(body);


        response.getWriter().write(responseBody);
        response.getWriter().flush();

        logger.info("Token generated: " + token);
    }
}
