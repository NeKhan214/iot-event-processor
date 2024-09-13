package com.nekhan.iot.event.processor.mapper;

import com.nekhan.iot.event.processor.dto.EventDTO;
import com.nekhan.iot.event.processor.entity.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {
    EventMapper EVENT_MAPPER = Mappers.getMapper(EventMapper.class);

    EventDTO mapEventEntityToDTO(EventEntity eventEntity);

    EventEntity mapEventDTOToEventEntity(EventDTO eventDTO);

}
