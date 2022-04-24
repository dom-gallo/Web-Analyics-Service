package com.gallo.dom.analytics_server_dev.controller;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gallo.dom.analytics_server_dev.model.TestObject;
import com.gallo.dom.analytics_server_dev.model.requests.AppUserRegisterRequest;
import com.gallo.dom.analytics_server_dev.model.Domain;
import com.gallo.dom.analytics_server_dev.model.User;
import com.gallo.dom.analytics_server_dev.repository.DomainRepository;
import com.gallo.dom.analytics_server_dev.service.UserService;
import io.jsonwebtoken.Claims;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);


    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Admin function

    @GetMapping("")
    public ResponseEntity getUserByEmail(@RequestParam("email") String emailAddress){
        logger.info(String.format("REQUEST FOR USER WITH EMAIL: %S", emailAddress));

        User user = userService.getUserByEmail(emailAddress);
        System.out.println(user);

        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public void signUpUser(@RequestBody @NotNull AppUserRegisterRequest appUserRegisterRequest){
        logger.info("UserController received request to sign up a new user with emailAddress="+appUserRegisterRequest.getEmailAddress());

        User savedUser = userService.addNewUser(appUserRegisterRequest);

    }
    /*
        Purpose: To provide all initial information to the web app for the currently logged in user
        Role: AppUser
     */

    @GetMapping("/me")
    public ResponseEntity getMe(Authentication auth) throws JsonProcessingException {

         Claims s = (Claims) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         System.out.print(String.format("%s", s.getSubject()));
         User user = userService.getUserByEmail(s.getSubject());

        return new ResponseEntity(user,HttpStatus.OK);
    }
}
