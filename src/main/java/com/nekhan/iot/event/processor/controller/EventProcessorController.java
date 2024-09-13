package com.nekhan.iot.event.processor.controller;

import java.util.Map;

import static com.nekhan.iot.event.processor.constants.ApiConstant.PATH_EVENTS;
import static com.nekhan.iot.event.processor.constants.ApiConstant.SUMMARY_STORE_EVENT_API;

import com.nekhan.iot.event.processor.dto.EventDTO;
import com.nekhan.iot.event.processor.exception.handler.ErrorDetails;
import com.nekhan.iot.event.processor.service.EventProcessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Operation(
            summary = SUMMARY_STORE_EVENT_API,
            description = SUMMARY_STORE_EVENT_API,
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created", content =
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = EventDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content =
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Map.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content =
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDetails.class)))
            }
    )
    @PostMapping
    public ResponseEntity<EventDTO> storeEvent(@Valid @RequestBody @Parameter(name = "Request Payload", description =
            "Parameters that define an incoming event") EventDTO eventDTO) {
        return new ResponseEntity<>(eventProcessorService.persistIOTEvents(eventDTO), HttpStatus.CREATED);
    }


}
