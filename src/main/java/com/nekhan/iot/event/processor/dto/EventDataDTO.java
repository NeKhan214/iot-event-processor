package com.nekhan.iot.event.processor.dto;

import static com.nekhan.iot.event.processor.constants.ApiConstant.*;
import static com.nekhan.iot.event.processor.constants.LocalizationKey.MISSING_MANDATORY_PARAMETERS;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventDataDTO {
    @Schema(description = EVENT_TEMPERATURE_DESCRIPTION, requiredMode = Schema.RequiredMode.REQUIRED, example = "70")
    @NotNull(message = MISSING_MANDATORY_PARAMETERS)
    Long temperature;
    @Schema(description = EVENT_HUMIDITY_DESCRIPTION, requiredMode = Schema.RequiredMode.REQUIRED, example = "40")
    @NotNull(message = MISSING_MANDATORY_PARAMETERS)
    Long humidity;
    @Schema(description = BATTERY_LEVEL_DESCRIPTION, requiredMode = Schema.RequiredMode.REQUIRED, example = "90")
    @NotNull(message = MISSING_MANDATORY_PARAMETERS)
    Long batterLevel;
}
