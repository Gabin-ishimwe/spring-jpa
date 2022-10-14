package com.gabinishimwe.springdatajpa.Event.listeners;

import com.gabinishimwe.springdatajpa.Event.RegistrationEvent;
import com.gabinishimwe.springdatajpa.entities.User;
import com.gabinishimwe.springdatajpa.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationListenerEvent implements ApplicationListener<RegistrationEvent> {

    @Autowired
    private UserService userService;

    private Logger LOGGER = LoggerFactory.getLogger(RegistrationListenerEvent.class);

    @Override
    public void onApplicationEvent(RegistrationEvent event) {
        // ... create verification token for user
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token, user);
        // ... send mail to user
        String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;
        // ... faking sending email
        LOGGER.info("click this link to verify email " + url);
        log.info("Another logger to verify email " + url);
    }
}
