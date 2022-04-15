package com.gallo.dom.analytics_server_dev.repository;

import com.gallo.dom.analytics_server_dev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.emailAddress = ?1")
    Optional<User> getUserByEmailAddress(String emailAddress);
}
