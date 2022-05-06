package com.gallo.dom.analytics_server_dev.service;

import com.gallo.dom.analytics_server_dev.exception.NotFoundException;
import com.gallo.dom.analytics_server_dev.model.Domain;
import com.gallo.dom.analytics_server_dev.model.User;
import com.gallo.dom.analytics_server_dev.model.requests.AppUserRegisterRequest;
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
    private DomainService domainService;

    @Autowired
    public UserService(UserRepository userRepository,DomainService domainService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.domainService = domainService;
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
    //TODO: check for already existing users
    public User addNewUser(AppUserRegisterRequest appUser){

        User user = new User(appUser.getEmailAddress(), passwordEncoder.encode(appUser.getPassword()), LocalDateTime.now());
        logger.info("Saving user to database with emailAddress="+user.getEmailAddress());
        User savedUser = userRepository.save(user);
        logger.info(String.format("Adding domain base = %s for new user with email =%s", appUser.getDomainBase(), appUser.getEmailAddress()));
        Domain d = new Domain(appUser.getDomainBase(), savedUser);
        Domain dD = domainService.save(d);
        savedUser.setDomain(dD);
        userRepository.save(savedUser);
        return savedUser;
    }
}
