package com.gallo.dom.analytics_server_dev.controller;

import com.gallo.dom.analytics_server_dev.service.DomainService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/domain")
public class DomainController {

    DomainService domainService;

    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }

}
