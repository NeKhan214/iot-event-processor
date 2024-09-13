package com.nekhan.iot.event.processor.entity;

import java.sql.Time;
import java.sql.Timestamp;

import static com.nekhan.iot.event.processor.constants.ApiConstant.*;

import com.nekhan.iot.event.processor.dto.EventDataDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = EVENT_ENTITY_TABLE_NAME)
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EventAttributeSequence")
    @SequenceGenerator(name = "EventAttributeSequence", sequenceName = EVENT_ENTITY_SEQUENCE_NAME, allocationSize = 1)
    private Long id;
    @Column(name = "DEVICE_ID", unique = true, nullable = false)
    private String deviceId;
    @Column(name = "EVENT_TYPE", nullable = false)
    private String eventType;
    @Column(name = "TIMESTAMP", nullable = false)
    private Timestamp timestamp;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_data_id", referencedColumnName = "id")
    private EventDataEntity eventData;
    @Column(name = "CREATED_BY", nullable = false)
    @CreatedBy
    private String createdBy;
    @CreatedDate
    @Column(name = "CREATED_DATE", nullable = false)
    private Timestamp createdDate;
    @Column(name = "UPDATED_BY", nullable = false)
    @LastModifiedBy
    private String updatedBy;
    @LastModifiedDate
    @Column(name = "UPDATED_DATE", nullable = false)
    private Timestamp updatedDate;
}
