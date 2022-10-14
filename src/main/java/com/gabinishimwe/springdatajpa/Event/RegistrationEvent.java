package com.gabinishimwe.springdatajpa.Event;

import com.gabinishimwe.springdatajpa.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationEvent extends ApplicationEvent {
    private final User user;
    private final String applicationUrl;
    public RegistrationEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
