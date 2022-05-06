package com.gallo.dom.analytics_server_dev.service;

import com.gallo.dom.analytics_server_dev.exception.BadRequestException;
import com.gallo.dom.analytics_server_dev.exception.NotFoundException;
import com.gallo.dom.analytics_server_dev.model.Domain;
import com.gallo.dom.analytics_server_dev.model.PageView;
import com.gallo.dom.analytics_server_dev.model.User;
import com.gallo.dom.analytics_server_dev.model.requests.PageViewRequest;
import com.gallo.dom.analytics_server_dev.repository.DomainRepository;
import com.gallo.dom.analytics_server_dev.repository.PageViewRepository;
import com.gallo.dom.analytics_server_dev.util.IURLParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PageViewService {
    private Logger logger = LoggerFactory.getLogger(PageViewService.class);

    private PageViewRepository pageViewRepository;
    private DomainRepository domainRepository;
    private IURLParser urlParser;
    private UserService userService;
    @Autowired
    public PageViewService(PageViewRepository pageViewRepository, DomainRepository domainRepository, IURLParser urlParser,
                           UserService userService) {
        this.pageViewRepository = pageViewRepository;
        this.domainRepository = domainRepository;
        this.urlParser = urlParser;
        this.userService = userService;
    }
    public List<PageView> getPageViewsForEmail(String emailAddress){
        User userFromEmail = userService.getUserByEmail(emailAddress);
        Domain domain = userFromEmail.getDomain();
        Long domainId = domain.getId();
        logger.info(String.format("Getting pageviews for user with email = %s and domain = %s", emailAddress, domain.getDomainBase()));
        List<PageView> pageViews = pageViewRepository.getPageViewForDomainId(domainId);
        return pageViews;
    }

    public List<PageView> getPageViewsForDomainId(Long domainId){
        logger.info("Looking for domain with id: " + domainId);
        Optional<Domain> domain = domainRepository.findById(domainId);
        if (domain.isEmpty()) {
            logger.warn("Cannot find domain for id: " + domainId);
            throw new BadRequestException("Bad Request, Domain does not exist for that Id");
        }
        logger.info("Found domain with id: " + domainId + " looking for pageviews for that id.");
        List<PageView> views = pageViewRepository.getPageViewForDomainId(domain.get().getId());
        return views;
    }
    /*
    Purpose: Takes a plain URL that is a new PageView, parser and separates into
        a, 1. Domain
           2. ResourceConsumed
       Checks if this is a domain that we are tracking pageviews and if so, we will add a new PageView object
       and pass it off to the persistence layer.

     */
    public PageView addPageView(String url){
        PageViewRequest pvr = urlParser.parseURL(url);
        // check if we can find the domain that was viewed in our database
        logger.info("Looking for Domain="+pvr.getDomainBase()+" in database to add a new pageview");
        logger.info(pvr.getDomainBase());
        Optional<Domain> domainOptional = domainRepository.findDomainByBase(pvr.getDomainBase());
        if(domainOptional.isEmpty()){
            logger.info("Could not find a domain with base="+pvr.getDomainBase());
            // we cannot find a domain with that base;
            throw new NotFoundException("Could not find a domain with base="+pvr.getDomainBase());
        }
        // We know that we did find a domain here.
        Domain domain = domainOptional.get();
        // Parse the PageVIewRequest into a PageView object.
        PageView pageView = new PageView(domain, pvr.getPath());
        // Save to persistence layer.
        PageView nPageView = pageViewRepository.save(pageView);
        if(nPageView.getId().equals(null) || nPageView.getId() < 1){
            logger.warn("Error saving new pageview");
            // Idk what could have happened here, but lets blame the user instead of taking responsibility.
            throw new BadRequestException("Could not save pageview, malformed request.");
        }
        return nPageView;
    }
}
