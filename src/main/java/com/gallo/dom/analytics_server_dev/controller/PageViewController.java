package com.gallo.dom.analytics_server_dev.controller;

import com.gallo.dom.analytics_server_dev.exception.NotFoundException;
import com.gallo.dom.analytics_server_dev.model.PageView;
import com.gallo.dom.analytics_server_dev.model.PageViewRequest;
import com.gallo.dom.analytics_server_dev.security.AuthorizationFilter;
import com.gallo.dom.analytics_server_dev.service.DomainService;
import com.gallo.dom.analytics_server_dev.service.PageViewService;
import com.gallo.dom.analytics_server_dev.util.UrlParseService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/pageview")
public class PageViewController {

    Logger logger = LoggerFactory.getLogger(PageViewController.class);

    DomainService domainService;
    PageViewService pageViewService;
    UrlParseService  urlParser;

    public PageViewController(DomainService domainService, PageViewService pageViewService, UrlParseService urlParser) {
        this.domainService = domainService;
        this.pageViewService = pageViewService;
        this.urlParser = urlParser;
    }


    @GetMapping("")
    public ResponseEntity newPageView(@RequestParam("domainId") Long domainId, @RequestParam("url") String url){
        if (domainId.equals(null)){
            logger.info("New Page View for url = " + url);
            return new ResponseEntity(HttpStatus.OK);
        }
        boolean didSave = pageViewService.addPageView(domainId, url);
        if (!didSave)
        {
            throw new NotFoundException("Cannot find pageviews for domain with id: " + domainId);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity getPageViewsForDomainId(@RequestParam("domainId") Long domainId){
        List<PageView> pageViews = pageViewService.getPageViewsForDomainId(domainId);
        return new ResponseEntity(pageViews, HttpStatus.OK);
    }


    @GetMapping("/new")
    public ResponseEntity addPageViewWithURL(@RequestParam("url") String url){
        logger.info("New page view for URL: " + url);

        PageViewRequest pvr = urlParser.parseURL(url);
        logger.info(pvr.toString());
        return new ResponseEntity(HttpStatus.OK);
    }
}
