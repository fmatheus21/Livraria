package com.fmatheus.app.controller.handler;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author fmatheus
 */
public class RestResponseHandler {

    @Getter
    @Setter
    private LocalDateTime timestamp = LocalDateTime.now();

    @Getter
    @Setter
    private int status;

    @Getter
    @Setter
    private String error;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private String path;

    public RestResponseHandler() {
    }

    public RestResponseHandler(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static RestResponseBuilderHandler builder() {
        return new RestResponseBuilderHandler();
    }

}
