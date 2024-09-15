package com.nekhan.iot.event.processor.service;

import com.nekhan.iot.event.processor.dto.request.EventDto;
import com.nekhan.iot.event.processor.dto.response.EventResponse;
import reactor.core.publisher.Mono;

/**
 * Interface to perform business logic on incoming events.
 *
 * @author Neha Khan
 * @version 1.o
 */
public interface EventProcessorService {

    /**
     * Method to process and save event data to database.
     *
     * @param eventDto Event object includes details such as the device ID, event type, timestamp,
     *                 and additional event data.
     * @return Mono<EventResponse> return saved event response.
     */
    Mono<EventResponse> persistIotEvents(EventDto eventDto);
}
