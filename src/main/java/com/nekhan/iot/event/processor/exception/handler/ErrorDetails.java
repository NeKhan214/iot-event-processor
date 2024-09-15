package com.nekhan.iot.event.processor.exception.handler;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * The {@code ErrorDetails} class represents the details of an error that occurs within the application.
 * It includes information such as the timestamp of the error, a message describing the error,
 * the path where the error occurred, the HTTP status code, and an error code.
 *
 * <p>This class is typically used to provide detailed error information in API responses.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * ErrorDetails errorDetails = new ErrorDetails();
 * errorDetails.setTimestamp("2024-09-15T13:53:48Z");
 * errorDetails.setMessage("Resource not found");
 * errorDetails.setPath("/api/resource");
 * errorDetails.setHttpStatus(HttpStatus.NOT_FOUND);
 * errorDetails.setErrorCode("404_NOT_FOUND");
 * }</pre>
 *
 * @author Neha Kha
 * @version 1.0
 */

@Getter
@Setter
@NoArgsConstructor
public class ErrorDetails {
    private String timestamp;
    private String message;
    private String path;
    private HttpStatus httpStatus;
    private String errorCode;
}
