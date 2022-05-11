package com.gallo.dom.analytics_server_dev.controller;

import com.gallo.dom.analytics_server_dev.model.PageView;
import com.gallo.dom.analytics_server_dev.service.DomainService;
import com.gallo.dom.analytics_server_dev.service.PageViewService;
import com.gallo.dom.analytics_server_dev.util.AuthenticatedUserUtil;
import com.gallo.dom.analytics_server_dev.service.UrlParseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/pageview")
public class PageViewController {

    Logger logger = LoggerFactory.getLogger(PageViewController.class);

    private DomainService domainService;
    private PageViewService pageViewService;
    private UrlParseService  urlParser;
    private final AuthenticatedUserUtil authenticatedUserUtil;

    public PageViewController(DomainService domainService, PageViewService pageViewService, UrlParseService urlParser, AuthenticatedUserUtil authenticatedUserUtil) {
        this.domainService = domainService;
        this.pageViewService = pageViewService;
        this.urlParser = urlParser;
        this.authenticatedUserUtil = authenticatedUserUtil;
    }


    /*
          Usage: /api/v1/pageview?domainId={domainId}
          Purpose: To retrieve all pageviews for the given domainId
     */
//    @GetMapping("/all")
//    public ResponseEntity getPageViewsForDomainId(@RequestParam("domainId") Long domainId){
//        List<PageView> pageViews = pageViewService.getPageViewsForDomainId(domainId);
//        return new ResponseEntity(pageViews, HttpStatus.OK);
//    }
    @GetMapping("/all")
    public ResponseEntity<List<PageView>> getPageViews(){
        String authorizedUserEmail = authenticatedUserUtil.getEmailFromContext(SecurityContextHolder.getContext().getAuthentication());
        logger.info(String.format("Getting pageviews for user with email = %s", authorizedUserEmail));
        List<PageView> pageViews = pageViewService.getPageViewsForEmail(authorizedUserEmail);
        return new ResponseEntity<>(pageViews, HttpStatus.OK);
    }
    /*
          Usage: /api/v1/pageview/new?url={url}
          Purpose: To add a new PageView for a given url

     */
    @GetMapping("/new")
    public ResponseEntity addPageViewWithURL(@RequestParam("url") String url){
        logger.info("New page view for URL: " + url);

        PageView o = pageViewService.addPageView(url);
        return new ResponseEntity(o,HttpStatus.OK);
    }
}
