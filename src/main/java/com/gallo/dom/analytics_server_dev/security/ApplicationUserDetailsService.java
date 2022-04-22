package com.gallo.dom.analytics_server_dev.security;

import com.gallo.dom.analytics_server_dev.model.User;
import com.gallo.dom.analytics_server_dev.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(ApplicationUserDetailsService.class);

    private UserRepository userRepository;

    @Autowired
    public ApplicationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("ApplicationUserDetailsService being called, loadUserByUsername");

        Optional<User> userOptional = userRepository.getUserByEmailAddress(username);
        if (userOptional.isEmpty()){
            logger.warn("Cannot find user with emailAddress = " + username);
            throw new UsernameNotFoundException(username);
        }

        User user = userOptional.get();
        logger.info("Found a user ----------------");
        logger.info(user.toString());
        return user;
//        return new org.springframework.security.core.userdetails.User(user.getEmailAddress(), user.getPassword(), Collections.emptyList());

    }
}
