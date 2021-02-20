package com.fmatheus.app.controller.rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fmatheus.app.controller.enumerable.MessagesEnum;
import com.fmatheus.app.controller.handler.response.MessageResponse;

/* Essas mensagens sao utilizadas para retornar no corpo da requisicao */
@Component
public class MessageResponseRule {

    @Autowired
    private MessageSource messageSource;

    public MessageResponse messageResponseBadRequest() {
        int statusCode = HttpStatus.BAD_REQUEST.value();
        HttpStatus statusDescription = HttpStatus.BAD_REQUEST;
        String messageProperties = MessagesEnum.ERROR_BAD_REQUEST.getDescription();
        String cause = HttpStatus.BAD_REQUEST.name();
        String message = messageSource.getMessage(messageProperties, null, LocaleContextHolder.getLocale());
        return new MessageResponse(statusCode, statusDescription.name(), message, cause);
    }

    public MessageResponse messageResponseSuccessCreate() {
        int statusCode = HttpStatus.CREATED.value();
        HttpStatus statusDescription = HttpStatus.CREATED;
        String messageProperties = MessagesEnum.SUCCESS_CREATE.getDescription();
        String cause = HttpStatus.CREATED.name();
        String message = messageSource.getMessage(messageProperties, null, LocaleContextHolder.getLocale());
        return new MessageResponse(statusCode, statusDescription.name(), message, cause);
    }

    public MessageResponse messageResponseSuccessUpdate() {
        int statusCode = HttpStatus.CREATED.value();
        HttpStatus statusDescription = HttpStatus.CREATED;
        String messageProperties = MessagesEnum.SUCCESS_UPDATE.getDescription();
        String cause = HttpStatus.CREATED.name();
        String message = messageSource.getMessage(messageProperties, null, LocaleContextHolder.getLocale());
        return new MessageResponse(statusCode, statusDescription.name(), message, cause);
    }

    public MessageResponse messageResponseSuccessDelete() {
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusDescription = HttpStatus.OK;
        String messageProperties = MessagesEnum.SUCCESS_DELETE.getDescription();
        String cause = HttpStatus.OK.name();
        String message = messageSource.getMessage(messageProperties, null, LocaleContextHolder.getLocale());
        return new MessageResponse(statusCode, statusDescription.name(), message, cause);
    }

}
