package com.gallo.dom.analytics_server_dev.service;

import com.gallo.dom.analytics_server_dev.exception.ApiRequestException;
import com.gallo.dom.analytics_server_dev.exception.NotFoundException;
import com.gallo.dom.analytics_server_dev.model.Domain;
import com.gallo.dom.analytics_server_dev.model.PageView;
import com.gallo.dom.analytics_server_dev.repository.DomainRepository;
import com.gallo.dom.analytics_server_dev.repository.PageViewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public int addPageView(Long domainId, String url){
        logger.info("Finding domain for id: " + domainId);
        Domain domain;

        try {
            domain = domainRepository.getDomainById(domainId);
            PageView pageview = new PageView(
                    LocalDateTime.now(),
                    url,
                    domain
            );
            pageViewRepository.save(pageview);
            return 1;
        } catch (RuntimeException e) {
            throw new ApiRequestException("Cannot find domain with that id");
        }
    }

    public List<PageView> getPageViewsForDomainId(Long domainId){

        Optional<Domain> domain = domainRepository.findById(domainId);
        if (domain.isEmpty()) {
            throw new ApiRequestException("Bad Request, Domain does not exist for that Id");
        }

        List<PageView> views = pageViewRepository.getPageViewForDomainId(domain.get().getId());
        return pageViewRepository.getPageViewForDomainId(domainId);
    }
}
