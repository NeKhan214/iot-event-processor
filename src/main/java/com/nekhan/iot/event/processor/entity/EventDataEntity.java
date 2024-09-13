package com.nekhan.iot.event.processor.entity;

import java.sql.Timestamp;

import static com.nekhan.iot.event.processor.constants.ApiConstant.*;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = EVENT_DATA_TABLE_NAME)
public class EventDataEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EventDataSequence")
    @SequenceGenerator(name = "EventDataSequence", sequenceName = EVENT_ENTITY_SEQUENCE_NAME, allocationSize = 1)
    private Long id;
    @Column(name = "TEMPERATURE", nullable = false)
    Long temperature;
    @Column(name = "HUMIDITY", nullable = false)
    Long humidity;
    @Column(name = "BATTER_LEVEL", nullable = false)
    Long batterLevel;
}
