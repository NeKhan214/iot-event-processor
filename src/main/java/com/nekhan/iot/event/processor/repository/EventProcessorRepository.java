package com.nekhan.iot.event.processor.repository;

import com.nekhan.iot.event.processor.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventProcessorRepository extends JpaRepository<EventEntity, Long> {


}
