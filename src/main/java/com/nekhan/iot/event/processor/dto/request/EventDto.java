package com.nekhan.iot.event.processor.dto.request;


import static com.nekhan.iot.event.processor.constants.ApiConstant.*;
import static com.nekhan.iot.event.processor.constants.LocalizationKey.*;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The {@code EventDto} class represents an event data transfer object.
 * It includes details such as the device ID, event type, timestamp, and additional event data.
 *
 * <p>This class is used to transfer event information between different layers of the application.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * EventDto event = new EventDto("DEV0001", "alert", Timestamp.valueOf("2024-09-04T09:21:52+07:00"), eventData);
 * }</pre>
 *
 * @author Neha Kha
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
public class EventDto {
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
    @NotNull(message = MISSING_MANDATORY_PARAMETERS)
    @JsonProperty("timestamp")
    private Timestamp dateTime;
    @JsonProperty("data")
    private EventDataDto eventData;
}
