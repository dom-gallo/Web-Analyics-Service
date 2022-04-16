package com.gallo.dom.analytics_server_dev.controller;

import com.gallo.dom.analytics_server_dev.model.PageView;
import com.gallo.dom.analytics_server_dev.service.DomainService;
import com.gallo.dom.analytics_server_dev.service.PageViewService;
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

    DomainService domainService;
    PageViewService pageViewService;

    public PageViewController(DomainService domainService, PageViewService pageViewService) {
        this.domainService = domainService;
        this.pageViewService = pageViewService;
    }

    @GetMapping("")
    public ResponseEntity newPageView(@RequestParam("domainId") Long domainId, @RequestParam("url") String url){
        if (pageViewService.addPageView(domainId,url) == 0)
        {
            return new ResponseEntity("NOT OK", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity getPageViewsForDomainId(@RequestParam("domainId") Long domainId){
        List<PageView> pageViews = pageViewService.getPageViewsForDomainId(domainId);
        return new ResponseEntity(pageViews, HttpStatus.OK);
    }
}
