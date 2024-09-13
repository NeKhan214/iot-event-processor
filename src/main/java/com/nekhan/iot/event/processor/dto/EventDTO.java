package com.nekhan.iot.event.processor.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.nekhan.iot.event.processor.constants.ApiConstant.*;
import static com.nekhan.iot.event.processor.constants.LocalizationKey.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventDTO {
    @Schema(description = DEVICE_ID_DESCRIPTION, requiredMode = Schema.RequiredMode.REQUIRED, example = "DEV0001")
    @NotBlank(message = MISSING_MANDATORY_PARAMETERS)
    @Pattern(regexp = DEVICE_ID_REGEXP, message = INVALID_DEVICE_ID_PATTERN)
    @JsonProperty("device_id")
    private String deviceId;

    @Schema(description = EVENT_TYPE_DESCRIPTION, requiredMode = Schema.RequiredMode.REQUIRED, example = "alert")
    @NotBlank(message = MISSING_MANDATORY_PARAMETERS)
    @Pattern(regexp = EVENT_TYPE_REGEXP, message = INVALID_EVENT_TYPE_PATTERN)
    @JsonProperty("event_type")
    private String eventType;

    @Schema(description = EVENT_TIMESTAMP_DESCRIPTION, requiredMode = Schema.RequiredMode.REQUIRED,
            example = "2024-09-04T09:21:52+07:00")
    @NotBlank(message = MISSING_MANDATORY_PARAMETERS)
    @JsonProperty("timestamp")
    private Timestamp dateTime;
    @JsonProperty("data")
    private EventDataDTO eventData;
}
