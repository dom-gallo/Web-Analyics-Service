package com.gallo.dom.analytics_server_dev.service;

import com.gallo.dom.analytics_server_dev.model.User;
import com.gallo.dom.analytics_server_dev.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserByEmailAddress(String emailAddress) {
        Optional<User> userOptional = userRepository.getUserByEmailAddress(emailAddress);
        if (userOptional.isEmpty()){
            // SEND BACK ERROR RESPONSE
            logger.info("USER NOT FOUND IN DATABASE");
            return null;
        }
        return userOptional;
    }
}
