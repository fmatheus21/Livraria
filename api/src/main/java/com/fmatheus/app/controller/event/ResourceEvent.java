package com.fmatheus.app.controller.event;

import javax.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class ResourceEvent extends ApplicationEvent {

    private static final long serialVersionUID = -5158219624948005570L;

    @Getter
    private final HttpServletResponse response;
    
    @Getter
    private final Integer id;

    public ResourceEvent(Object source, HttpServletResponse response, Integer id) {
        super(source);
        this.response = response;
        this.id = id;
    }

}
