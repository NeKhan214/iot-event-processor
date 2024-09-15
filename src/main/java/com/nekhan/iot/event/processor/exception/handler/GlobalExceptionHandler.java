package com.nekhan.iot.event.processor.exception.handler;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Global exception handler for handling exceptions in a Spring WebFlux application.
 * This class extends {@link AbstractErrorWebExceptionHandler} to provide custom error handling.
 *
 * @author Neha Khan
 * @version 1.0
 */
@Slf4j
public class GlobalExceptionHandler extends AbstractErrorWebExceptionHandler {

    /**
     * Constructor for GlobalExceptionHandler.
     *
     * @param errorAttributes the error attributes
     * @param resources the web properties resources
     * @param applicationContext the application context
     */
    public GlobalExceptionHandler(ErrorAttributes errorAttributes,
                                  WebProperties.Resources resources,
                                  ApplicationContext applicationContext) {
        super(errorAttributes, resources, applicationContext);
    }

    /**
     * Provides the routing function for handling errors.
     *
     * @param errorAttributes the error attributes
     * @return the router function
     */
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(
                RequestPredicates.all(), this::renderErrorResponse);
    }

    /**
     * Renders the error response.
     *
     * @param request the server request
     * @return the server response
     */
    private Mono<ServerResponse> renderErrorResponse(
            ServerRequest request) {
        Map<String, Object> errorPropertiesMap = getErrorAttributes(request,
                ErrorAttributeOptions.defaults());
        log.error("Exception encountered in application: {}", errorPropertiesMap);
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorPropertiesMap));
    }

    /**
     * Handles the exception.
     *
     * @param exchange the server web exchange
     * @param ex the throwable exception
     * @return the mono void
     */
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        try {
            if (ex instanceof WebExchangeBindException || ex instanceof BindException) {
                return handleValidationException(exchange, (Exception) ex);
            } else if (ex instanceof TooManyRequestsException) {
                return handleTooManyRequestsException(exchange, (Exception) ex);
            } else {
                return handleException(exchange, (Exception) ex);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles TooManyRequestsException.
     *
     * @param exchange the server web exchange
     * @param ex the exception
     * @return the mono void
     * @throws JsonProcessingException if there is an error processing JSON
     */
    private Mono<Void> handleTooManyRequestsException(ServerWebExchange exchange, Exception ex) throws JsonProcessingException {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorCode("TOO_MANY_REQUESTS");
        errorDetails.setPath(String.valueOf(exchange.getRequest().getURI()));
        errorDetails.setHttpStatus(HttpStatus.TOO_MANY_REQUESTS);
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setTimestamp(String.valueOf(LocalDateTime.now()));

        exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                .bufferFactory()
                .wrap(new ObjectMapper().writeValueAsBytes(errorDetails))));
    }

    /**
     * Handles all internal exception.
     *
     * @param exchange the server web exchange
     * @param ex the exception
     * @return the mono void
     * @throws JsonProcessingException if there is an error processing JSON
     */
    private Mono<Void> handleException(ServerWebExchange exchange, Exception ex) throws JsonProcessingException {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorCode("INTERNAL_SERVER_ERROR");
        errorDetails.setPath(String.valueOf(exchange.getRequest().getURI()));
        errorDetails.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setTimestamp(String.valueOf(LocalDateTime.now()));

        exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                .bufferFactory()
                .wrap(new ObjectMapper().writeValueAsBytes(errorDetails))));
    }

    /**
     * Handles all validation exception.
     *
     * @param exchange the server web exchange
     * @param ex the exception
     * @return the mono void
     * @throws JsonProcessingException if there is an error processing JSON
     */
    private Mono<Void> handleValidationException(ServerWebExchange exchange, Exception ex) throws JsonProcessingException {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorCode("VALIDATION_FAILED");
        errorDetails.setPath(String.valueOf(exchange.getRequest().getURI()));
        errorDetails.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setTimestamp(String.valueOf(LocalDateTime.now()));

        exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                .bufferFactory()
                .wrap(new ObjectMapper().writeValueAsBytes(errorDetails))));
    }
}
