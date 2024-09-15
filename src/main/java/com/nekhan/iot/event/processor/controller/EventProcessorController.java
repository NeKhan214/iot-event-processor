package com.nekhan.iot.event.processor.controller;

import static com.nekhan.iot.event.processor.constants.ApiConstant.PATH_EVENTS;
import static com.nekhan.iot.event.processor.constants.ApiConstant.SUMMARY_STORE_EVENT_API;

import com.nekhan.iot.event.processor.dto.request.EventDto;
import com.nekhan.iot.event.processor.dto.response.EventResponse;
import com.nekhan.iot.event.processor.exception.handler.ErrorDetails;
import com.nekhan.iot.event.processor.exception.handler.TooManyRequestsException;
import com.nekhan.iot.event.processor.service.EventProcessorService;
import io.github.bucket4j.Bucket;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * Controller class responsible for processing events.
 * This class handles the reception, and storage of various events.
 * It acts as a mediator between the event sources and the event processing logic.
 *
 * @author Neha Khan
 * @version 1.0
 */
@RestController
@RequestMapping(value = PATH_EVENTS)
@Validated
@AllArgsConstructor
public class EventProcessorController {

    EventProcessorService eventProcessorService;
    Bucket bucket;

    /**
     * API to store incoming events concurrently. Also, enable rate limiting on API.
     *
     * @param eventDto Incoming event information between different layers of the application.
     * @return EventResponse stored in database.
     * @throws TooManyRequestsException Exception thrown if API requests exceeds the set bucket limit.
     */
    @Operation(
            summary = SUMMARY_STORE_EVENT_API,
            description = SUMMARY_STORE_EVENT_API,
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created", content =
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = EventDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content =
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDetails.class))),
                    @ApiResponse(responseCode = "429", description = "Too Many requests Error", content =
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDetails.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content =
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDetails.class)))
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<EventResponse> storeEvent(@Valid @RequestBody @Parameter(name = "Request Payload", description =
            "Parameters that define an incoming event") EventDto eventDto) throws TooManyRequestsException {
        if (bucket.tryConsume(1)) {
            return eventProcessorService.persistIotEvents(eventDto);
        } else {
            throw new TooManyRequestsException("Too many requests");
        }
    }


}
