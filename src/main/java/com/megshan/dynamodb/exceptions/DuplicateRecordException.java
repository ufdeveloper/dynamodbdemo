package com.megshan.dynamodb.exceptions;

/**
 * Created by shantanu on 11/17/18.
 */

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

import static org.springframework.http.HttpStatus.CONFLICT;

/**
 * Exception to be used when duplicate record found
 *
 */
@ResponseStatus(CONFLICT)
public class DuplicateRecordException extends HttpStatusCodeException {
    /**
     * Generated serialVersionUID
     */
    private static final long serialVersionUID = -7635756838587441196L;

    public DuplicateRecordException(String message) {
        super(CONFLICT, message);
    }
}