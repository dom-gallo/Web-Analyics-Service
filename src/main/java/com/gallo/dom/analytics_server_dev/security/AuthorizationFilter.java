package com.gallo.dom.analytics_server_dev.security;

import com.gallo.dom.analytics_server_dev.service.PageViewService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.gallo.dom.analytics_server_dev.security.SecurityConstants.HEADER_NAME;
import static com.gallo.dom.analytics_server_dev.security.SecurityConstants.KEY;

public class AuthorizationFilter extends BasicAuthenticationFilter {
    Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);
    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_NAME);

        logger.info("do filter internal called");

        if (header == null){
            logger.info("do filter internal called, header is null");
            chain.doFilter(request, response);
            return;
        }
        logger.info("doFilterInternal called, trying to get authenticationtoken");
        UsernamePasswordAuthenticationToken authentication = authenticate(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken authenticate(HttpServletRequest request) {

        logger.info("authenticate called inside AuthorizationFilter");
        String token = request.getHeader(HEADER_NAME);

        if (token != null){

            Claims user = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes())).build().parseClaimsJws(token).getBody();

            if(user != null){
                return new UsernamePasswordAuthenticationToken(user,null, new ArrayList<>());
            }
        }
        return null;
    }
}
