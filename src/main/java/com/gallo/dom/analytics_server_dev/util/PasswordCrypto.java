package com.gallo.dom.analytics_server_dev.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@Primary
public class PasswordCrypto implements PasswordEncoder {

    Logger logger = LoggerFactory.getLogger(PasswordCrypto.class);


    private PasswordEncoder encoder;

    public PasswordCrypto() {
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encode(CharSequence rawPassword) {
        logger.info("Encoding a new password");
        String hashedPassword = encoder.encode(rawPassword);
        return hashedPassword;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {

        boolean doesMatch = encoder.matches(rawPassword, encodedPassword);
        if(doesMatch){
            logger.info("Passwords do match");
        } else {
            logger.info("Passwords do not match");
        }

        return doesMatch;
    }
}
