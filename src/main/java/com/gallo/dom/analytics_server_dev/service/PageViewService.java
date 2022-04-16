package com.gallo.dom.analytics_server_dev.service;

import com.gallo.dom.analytics_server_dev.exception.BadRequestException;
import com.gallo.dom.analytics_server_dev.model.Domain;
import com.gallo.dom.analytics_server_dev.model.PageView;
import com.gallo.dom.analytics_server_dev.repository.DomainRepository;
import com.gallo.dom.analytics_server_dev.repository.PageViewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PageViewService {
    Logger logger = LoggerFactory.getLogger(PageViewService.class);

    PageViewRepository pageViewRepository;
    DomainRepository domainRepository;

    @Autowired
    public PageViewService(PageViewRepository pageViewRepository, DomainRepository domainRepository) {
        this.pageViewRepository = pageViewRepository;
        this.domainRepository = domainRepository;
    }

    public boolean addPageView(Long domainId, String url){
        logger.info("Looking for domain with id: " + domainId);
        Optional<Domain> domain = domainRepository.findById(domainId);
        if (domain.isEmpty()){
            logger.warn("User requested bad domain id: " + domainId);
            throw new BadRequestException("Cannot find domain with that ID");
        }
        logger.info("Adding pageview to domain with id: " + domainId);
        PageView pageView = new PageView(LocalDateTime.now(), url, domain.get());
        PageView savedPageView = pageViewRepository.save(pageView);
        if (savedPageView.getId().equals(null)) {
            throw new BadRequestException("Could not save pageview");
        }
        return true;
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
}
