package com.nekhan.iot.event.processor.service;

import com.nekhan.iot.event.processor.dto.request.EventDto;
import com.nekhan.iot.event.processor.dto.response.EventDataResponse;
import com.nekhan.iot.event.processor.dto.response.EventResponse;
import com.nekhan.iot.event.processor.entity.EventDataEntity;
import com.nekhan.iot.event.processor.entity.EventEntity;
import com.nekhan.iot.event.processor.mapper.EventMapper;
import com.nekhan.iot.event.processor.repository.EventDataProcessorRepository;
import com.nekhan.iot.event.processor.repository.EventProcessorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Implementation class to perform business logic on incoming events.
 *
 * @author Neha Khan
 * @version 1.o
 */
@Service
@AllArgsConstructor
@Slf4j
public class EventProcessorServiceImpl implements EventProcessorService {

    EventProcessorRepository eventProcessorRepository;
    EventDataProcessorRepository eventDataProcessorRepository;

    /**
     * Method implementation to process and save event data to database.
     *
     * @param eventDto Event object includes details such as the device ID, event type, timestamp,
     *                 and additional event data.
     * @return Mono<EventResponse> return saved event response.
     */
    @Override
    public Mono<EventResponse> persistIotEvents(EventDto eventDto) {
        EventDataEntity eventDataEntity = EventMapper.EVENT_MAPPER.mapEventDataDtoToEventEntity(eventDto.getEventData());
        EventEntity eventEntity = EventMapper.EVENT_MAPPER.mapEventDtoToEventEntity(eventDto);
        return eventDataProcessorRepository.save(eventDataEntity)
                .flatMap(savedEventDataEntity -> {
                    eventEntity.setEventDataId(savedEventDataEntity.getId());
                    return eventProcessorRepository.save(eventEntity)
                            .flatMap(savedEventEntity -> Mono.just(
                                    convertEntityToResponse(savedEventEntity, savedEventDataEntity)));
                });
    }

    /**
     * Method to convert entities to response object.
     *
     * @param eventEntity saved Event object includes details such as the device ID, event type, timestamp
     *                    and additional event data.
     * @param eventDataEntity saved Event data object includes details such as temperature, humidity,
     *                        and battery level.
     * @return EventResponse.
     */
    EventResponse convertEntityToResponse(EventEntity eventEntity, EventDataEntity eventDataEntity) {
        EventDataResponse eventDataResponse = EventDataResponse.builder()
                .humidity(eventDataEntity.getHumidity())
                .temperature(eventDataEntity.getTemperature())
                .batterLevel(eventDataEntity.getBatterLevel())
                .build();
        EventResponse eventResponse = EventResponse.builder()
                .id(eventEntity.getId())
                .eventType(eventEntity.getEventType())
                .deviceId(eventEntity.getDeviceId())
                .dateTime(eventEntity.getDateTime())
                .eventDataResponse(eventDataResponse)
                .build();
        log.info("EventResponse: {}", eventResponse);
        return eventResponse;
    }
}
