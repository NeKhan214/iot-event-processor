package com.nekhan.iot.event.processor.dto.request;

import static com.nekhan.iot.event.processor.constants.ApiConstant.*;
import static com.nekhan.iot.event.processor.constants.LocalizationKey.MISSING_MANDATORY_PARAMETERS;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The {@code EventDataDto} class represents the data associated with an event.
 * It includes details such as temperature, humidity, and battery level.
 *
 * <p>This class is used to encapsulate the specific data points related to an event.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * EventDataDto eventData = new EventDataDto(70L, 40L, 90L);
 * }</pre>
 *
 * @author Neha Khan
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
public class EventDataDto {
    @Schema(description = EVENT_TEMPERATURE_DESCRIPTION, requiredMode = Schema.RequiredMode.REQUIRED, example = "70")
    @NotNull(message = MISSING_MANDATORY_PARAMETERS)
    Long temperature;
    @Schema(description = EVENT_HUMIDITY_DESCRIPTION, requiredMode = Schema.RequiredMode.REQUIRED, example = "40")
    @NotNull(message = MISSING_MANDATORY_PARAMETERS)
    Long humidity;
    @Schema(description = BATTERY_LEVEL_DESCRIPTION, requiredMode = Schema.RequiredMode.REQUIRED, example = "90")
    @NotNull(message = MISSING_MANDATORY_PARAMETERS)
    @JsonProperty("battery_level")
    Long batterLevel;
}
