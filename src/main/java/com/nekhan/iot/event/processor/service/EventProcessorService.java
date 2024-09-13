package com.nekhan.iot.event.processor.service;

import com.nekhan.iot.event.processor.dto.EventDTO;

public interface EventProcessorService {

    EventDTO persistIOTEvents(EventDTO eventDTO);
}
