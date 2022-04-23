package com.gallo.dom.analytics_server_dev.controller;

import com.gallo.dom.analytics_server_dev.model.requests.AddDomainRequest;
import com.gallo.dom.analytics_server_dev.service.DomainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/domain")
public class DomainController {

    DomainService domainService;

    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }
    /*
       Purpose: Add a new tracked domain to a users account
       Usage: /api/v1/domain/add - {domainBase: "domainBase"}
       Return Value: ResponseEntity w/ Status Code
     */
    @PostMapping("/add")
    public ResponseEntity addDomainToAccount(@RequestBody AddDomainRequest domainRequest, Authentication auth){

        System.out.println(String.format("%s", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()));

        return new ResponseEntity(domainRequest, HttpStatus.OK);
    }
}
