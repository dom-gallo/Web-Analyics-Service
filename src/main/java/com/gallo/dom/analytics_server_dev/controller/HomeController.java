package com.gallo.dom.analytics_server_dev.controller;


import com.gallo.dom.analytics_server_dev.model.AppUserSecurityContext;
import com.gallo.dom.analytics_server_dev.model.User;
import com.gallo.dom.analytics_server_dev.security.ApplicationUserDetailsService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.HTMLDocument;

@RestController
@RequestMapping(path = "/")
public class HomeController {

    private final ApplicationUserDetailsService appUserDetailsService;

    @Autowired
    public HomeController(ApplicationUserDetailsService appUserDetailsService) {
        this.appUserDetailsService = appUserDetailsService;
    }

    @GetMapping(path = {"/", "/home"})
    public String getHome(){
        return "index";
    }
}
