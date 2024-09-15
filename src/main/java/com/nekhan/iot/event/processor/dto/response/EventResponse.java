package com.nekhan.iot.event.processor.dto.response;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;

/**
 * The {@code EventResponse} class represents an event data transfer object.
 * It includes details such as the device ID, event type, timestamp, and additional event data.
 *
 * <p>This class is used to transfer event information between different layers of the application.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * EventResponse event = new EventResponse("DEV0001", "alert", Timestamp.valueOf("2024-09-04T09:21:52+07:00"),
 * eventDataResponse);
 * }</pre>
 *
 * @author Neha Kha
 * @version 1.0
 */
@Builder
@Data
public class EventResponse implements Serializable {
    private static final long serialVersionUID = 129348938L;
    private Long id;
    private String deviceId;
    private String eventType;
    private Timestamp dateTime;
    private EventDataResponse eventDataResponse;
}

