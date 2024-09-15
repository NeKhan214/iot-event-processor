package com.nekhan.iot.event.processor.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a request is made too frequently.
 * This exception results in a HTTP status code 429 (Too Many Requests).
 *
 * <p>This exception should be used to indicate that the user has sent too many
 * requests in a given amount of time ("rate limiting").</p>
 *
 * @see HttpStatus#TOO_MANY_REQUESTS
 * @author Neha Khan
 * @version 1.0
 */
@ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
public class TooManyRequestsException extends Exception {

    /**
     * Constructs a new TooManyRequestsException with the specified detail message.
     *
     * @param message the detail message
     */
    public TooManyRequestsException(String message) {
        super(message);
    }
}
