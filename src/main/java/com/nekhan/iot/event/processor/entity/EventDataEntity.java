package com.nekhan.iot.event.processor.entity;

import static com.nekhan.iot.event.processor.constants.ApiConstant.EVENT_ENTITY_SEQUENCE_NAME;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EventDataSequence")
    @SequenceGenerator(name = "EventDataSequence", sequenceName = EVENT_ENTITY_SEQUENCE_NAME, allocationSize = 1)
    private Long id;
    @Column(name = "temperature", nullable = false)
    Long temperature;
    @Column(name = "humidity", nullable = false)
    Long humidity;
    @Column(name = "batteryLevel", nullable = false)
    Long batterLevel;
}
