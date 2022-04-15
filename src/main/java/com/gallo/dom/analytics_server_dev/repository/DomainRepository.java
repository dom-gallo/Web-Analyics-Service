package com.gallo.dom.analytics_server_dev.repository;

import com.gallo.dom.analytics_server_dev.model.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainRepository extends JpaRepository<Domain, Long> {


}
