package com.gallo.dom.analytics_server_dev.controller;

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

     */
    @GetMapping("/me")
    public ResponseEntity getMe(HttpServletRequest request, Authentication auth){
        // Pull the user emailAddress out of the security context authentication
//        String lookingForEmailAddressHere = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Claims lookingForEmailAddressHere = (Claims) auth.getName().toString();
        logger.info(String.format("LOOKING FOR CURRENTLY LOGGED IN USER INFORMATION: %s", lookingForEmailAddressHere));
        return new ResponseEntity(lookingForEmailAddressHere, HttpStatus.OK);
    }
}
