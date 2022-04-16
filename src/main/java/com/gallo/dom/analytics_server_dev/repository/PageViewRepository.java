package com.gallo.dom.analytics_server_dev.repository;

import com.gallo.dom.analytics_server_dev.exception.ApiRequestException;
import com.gallo.dom.analytics_server_dev.exception.NotFoundException;
import com.gallo.dom.analytics_server_dev.model.PageView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface PageViewRepository extends JpaRepository<PageView, Long> {

    @Query("SELECT p FROM PageView p WHERE p.domain.id = ?1")
    List<PageView> getPageViewForDomainId(Long id) throws ApiRequestException;

}
