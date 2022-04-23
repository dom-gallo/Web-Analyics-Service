package com.gallo.dom.analytics_server_dev.service;

import com.gallo.dom.analytics_server_dev.model.Domain;
import com.gallo.dom.analytics_server_dev.repository.DomainRepository;
import com.gallo.dom.analytics_server_dev.repository.PageViewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DomainService {
    private final Logger logger = LoggerFactory.getLogger(DomainService.class);
    private final DomainRepository domainRepository;

    public DomainService(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    Domain save(Domain d){
        logger.info(String.format("Saving domain with base=%s",d.getDomainBase()));
        return domainRepository.save(d);
    }
}
