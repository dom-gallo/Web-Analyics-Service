package com.gallo.dom.analytics_server_dev.service;

import com.gallo.dom.analytics_server_dev.model.requests.PageViewRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UrlParseService implements IURLParser {
    Logger logger = LoggerFactory.getLogger(UrlParseService.class);

    public UrlParseService() {
    }

    @Override
    public PageViewRequest parseURL(String urlRequestParameter) {
        //http://localhost:8080/index.html
        String protocolRemoved;
        int endOfDomain;
        String resourceConsumed;
        String domain;
        //parsing protocol www-subdomain url
        if(urlRequestParameter.startsWith("http://www.")){
            return null;
        }
        // Parsing a protocol only url
        if(urlRequestParameter.startsWith("http://")){
            protocolRemoved = urlRequestParameter.substring(7);
            endOfDomain = protocolRemoved.indexOf("/");
            domain = protocolRemoved.substring(0, endOfDomain);
            resourceConsumed = protocolRemoved.substring(endOfDomain);

            logger.info(String.format("URL Parsed for Domain: %s, Resource Consumed: %s", domain, resourceConsumed));
            return new PageViewRequest(domain, resourceConsumed);
        }
        // parsing sub-domain only url
        if(urlRequestParameter.startsWith("www.")){
            protocolRemoved = urlRequestParameter.substring(1);
            endOfDomain = protocolRemoved.indexOf("/");
            domain = protocolRemoved.substring(0,endOfDomain);
            resourceConsumed = protocolRemoved.substring(endOfDomain);
            return new PageViewRequest(domain, resourceConsumed);
        }
        return null;
    }
}
