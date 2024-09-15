package com.nekhan.iot.event.processor.dto.response;

import java.io.Serial;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

/**
 * The {@code EventDataResponse} class represents the data associated with an event.
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
@Builder
@Data
public class EventDataResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 129348938L;
    private Long temperature;
    private Long humidity;
    private Long batterLevel;
}
