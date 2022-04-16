package com.gallo.dom.analytics_server_dev.service;

import com.gallo.dom.analytics_server_dev.model.Domain;
import com.gallo.dom.analytics_server_dev.model.PageView;
import com.gallo.dom.analytics_server_dev.repository.DomainRepository;
import com.gallo.dom.analytics_server_dev.repository.PageViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PageViewService {

    PageViewRepository pageViewRepository;
    DomainRepository domainRepository;

    @Autowired
    public PageViewService(PageViewRepository pageViewRepository, DomainRepository domainRepository) {
        this.pageViewRepository = pageViewRepository;
        this.domainRepository = domainRepository;
    }

    public int addPageView(Long domainId, String url){
        Domain domain = domainRepository.getById((Long) domainId);
        if (domain == null)
        {
            return 0;
        }
        PageView newView = new PageView(
                LocalDateTime.now(),
                url,
                domain
        );
        pageViewRepository.save(newView);
        return 1;
    }

    public List<PageView> getPageViewsForDomainId(Long domainId){
        return pageViewRepository.getPageViewForDomainId(domainId);
    }
}
