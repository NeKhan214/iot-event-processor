package com.nekhan.iot.event.processor.entity;

import java.sql.Timestamp;

import static com.nekhan.iot.event.processor.constants.ApiConstant.*;

import jakarta.persistence.*;
import lombok.*;
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
@Table(name = EVENT_ENTITY_TABLE_NAME)
public class EventEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EventAttributeSequence")
    @SequenceGenerator(name = "EventAttributeSequence", sequenceName = EVENT_ENTITY_SEQUENCE_NAME, allocationSize = 1)
    private Long id;
    @Column(name = "DEVICE_ID", unique = true, nullable = false)
    private String deviceId;
    @Column(name = "EVENT_TYPE", nullable = false)
    private String eventType;
    @Column(name = "DATE_TIME", nullable = false)
    private Timestamp dateTime;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EVENT_DATA_ID", referencedColumnName = "ID")
    private EventDataEntity eventData;
}
