package com.gallo.dom.analytics_server_dev.repository;

import com.gallo.dom.analytics_server_dev.model.PageView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface PageViewRepository extends JpaRepository<PageView, Long> {
}
