package com.fmatheus.app.controller.enumerable;

import lombok.Getter;

public enum MessagesEnum {

    SUCCESS_CREATE("message.success.create"),
    SUCCESS_UPDATE("message.success.update"),
    SUCCESS_DELETE("message.success.delete"),
    ERROR_NOT_READABLE("message.error.not.readable"),
    ERROR_NOT_FOUND("message.error.not.found"),
    ERROR_REGISTER_EXIST("message.error.register.exist"),
    ERROR_NOT_PERMISSION("message.error.not.permission"),
    ERROR_BAD_REQUEST("message.error.bad.request");

    @Getter
    private final String description;

    MessagesEnum(String description) {
        this.description = description;
    }

}
