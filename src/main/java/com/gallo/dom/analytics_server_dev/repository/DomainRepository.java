package com.gallo.dom.analytics_server_dev.repository;

import com.gallo.dom.analytics_server_dev.model.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DomainRepository extends JpaRepository<Domain, Long> {

    @Query("SELECT d FROM Domain d WHERE d.id = ?1")
    public Optional<Domain> getDomainById(Long id);

    @Query("SELECT d FROM Domain d where d.domainBase = ?1")
    public Optional<Domain> findDomainByBase(String base);

}
