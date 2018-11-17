package com.megshan.dynamodb.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Exception class to be used when a resource was not found.
 *
 * Created by shantanu on 11/16/18.
 */
@ResponseStatus(NOT_FOUND)
public class NotFoundException extends HttpStatusCodeException {

    public NotFoundException(String msg) {
        super(NOT_FOUND, msg);
    }
}