package com.gallo.dom.analytics_server_dev.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gallo.dom.analytics_server_dev.model.User;
import com.gallo.dom.analytics_server_dev.model.requests.AppUserRegisterRequest;
import com.gallo.dom.analytics_server_dev.service.UserService;
import com.gallo.dom.analytics_server_dev.util.ApiResponseHandler;
import com.gallo.dom.analytics_server_dev.util.AuthenticatedUserUtil;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final AuthenticatedUserUtil authenticatedUserUtil;


    public UserController(UserService userService, AuthenticatedUserUtil authenticatedUserUtil)
    {
        this.userService = userService;
        this.authenticatedUserUtil = authenticatedUserUtil;
    }

    // Admin function

    @GetMapping("")
    public ResponseEntity getUserByEmail(@RequestParam("email") String emailAddress)
    {
        logger.info(String.format("REQUEST FOR USER WITH EMAIL: %S", emailAddress));
        try
        {
            User user = userService.getUserByEmail(emailAddress);
            logger.info(String.format("USER FOUND FOR EMAIL %s, ", emailAddress));
            logger.info(user.toString());
            return ApiResponseHandler.generateResponse("User found.", HttpStatus.OK, user);
        } catch (Exception e)
        {
            return ApiResponseHandler.generateResponse("Unknown exception occured.", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

    }

    @PostMapping("/register")
    public ResponseEntity signUpUser(@RequestBody @NotNull AppUserRegisterRequest appUserRegisterRequest)
    {
        logger.info("UserController received request to sign up a new user with emailAddress="+appUserRegisterRequest.getEmailAddress());
        if(appUserRegisterRequest.getPassword().equals(appUserRegisterRequest.getPasswordConfirm()))
        {
            return new ResponseEntity("Passwords do not match", HttpStatus.BAD_REQUEST);
        }
        User savedUser = userService.addNewUser(appUserRegisterRequest);


        return new ResponseEntity("message: User successfully created", HttpStatus.OK);
    }
    /*
        Purpose: To provide all initial information to the web app for the currently logged-in user
        Role: AppUser
     */

    @GetMapping("/me")
    public ResponseEntity getMe() throws JsonProcessingException {
//        Claims c = (Claims) auth.getPrincipal();
//        logger.info(String.format("Claims c = ", c.getSubject()));
//        Claims s = (Claims) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.print(String.format("%s", s.getSubject()));

        String authorizedUserEmailAddress = authenticatedUserUtil.getEmailFromContext(SecurityContextHolder.getContext().getAuthentication());
        User user = userService.getUserByEmail(authorizedUserEmailAddress);

        return new ResponseEntity(user,HttpStatus.OK);
    }
}
