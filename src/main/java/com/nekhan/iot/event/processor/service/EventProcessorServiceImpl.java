package com.nekhan.iot.event.processor.service;

import java.util.Optional;

import static com.nekhan.iot.event.processor.constants.ApiConstant.SYSTEM_USER;

import com.nekhan.iot.event.processor.dto.EventDTO;
import com.nekhan.iot.event.processor.entity.EventEntity;
import com.nekhan.iot.event.processor.mapper.EventMapper;
import com.nekhan.iot.event.processor.repository.EventProcessorRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventProcessorServiceImpl implements EventProcessorService {

    EventProcessorRepository eventProcessorRepository;

    @Override
    public EventDTO persistIOTEvents(EventDTO eventDto) {
        EventEntity eventEntity = EventMapper.EVENT_MAPPER.mapEventDTOToEventEntity(eventDto);
        return EventMapper.EVENT_MAPPER.mapEventEntityToDTO(eventProcessorRepository.save(eventEntity));
    }
}
