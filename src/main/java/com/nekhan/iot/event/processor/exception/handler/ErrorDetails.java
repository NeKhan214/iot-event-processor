package com.nekhan.iot.event.processor.exception.handler;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@Builder
public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String path;
    private String errorCode;
}
