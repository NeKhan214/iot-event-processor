package com.nekhan.iot.event.processor.mapper;

import com.nekhan.iot.event.processor.dto.request.EventDataDto;
import com.nekhan.iot.event.processor.dto.request.EventDto;
import com.nekhan.iot.event.processor.entity.EventDataEntity;
import com.nekhan.iot.event.processor.entity.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between Event DTOs and Event Entities.
 * Utilizes MapStruct for automatic mapping.
 */
@Mapper
public interface EventMapper {

    /**
     * Instance of the EventMapper.
     */
    EventMapper EVENT_MAPPER = Mappers.getMapper(EventMapper.class);

    /**
     * Converts an EventDto to an EventEntity.
     *
     * @param eventDto the EventDto to be converted
     * @return the corresponding EventEntity
     */
    EventEntity mapEventDtoToEventEntity(EventDto eventDto);

    /**
     * Converts an EventDataDto to an EventDataEntity.
     *
     * @param eventDto the EventDataDto to be converted
     * @return the corresponding EventDataEntity
     */
    EventDataEntity mapEventDataDtoToEventEntity(EventDataDto eventDto);

}
