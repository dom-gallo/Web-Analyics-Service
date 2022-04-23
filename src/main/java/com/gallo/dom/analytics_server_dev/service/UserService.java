package com.gallo.dom.analytics_server_dev.service;

import com.gallo.dom.analytics_server_dev.exception.NotFoundException;
import com.gallo.dom.analytics_server_dev.model.User;
import com.gallo.dom.analytics_server_dev.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByEmail(String emailAddress){
        Optional<User> userOptional = userRepository.getUserByEmailAddress(emailAddress);
        if (userOptional.isEmpty()) {
            logger.warn("Request for user with email: " + emailAddress + " not found");
            throw new NotFoundException("User not found for emailAddress = " + emailAddress);
        }
        logger.info("Found user with emailAddress = " + emailAddress);
        return userOptional.get();
    }

    public User addNewUser(User user){
        user.setCreated_at(LocalDateTime.now());
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        logger.info("Saving user to database with emailAddress="+user.getEmailAddress());
        return userRepository.save(user);
    }
}
