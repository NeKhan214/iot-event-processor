package com.nekhan.iot.event.processor.entity;

import static com.nekhan.iot.event.processor.constants.ApiConstant.EVENT_DATA_TABLE_NAME;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity class representing events.
 * This class is annotated with Lombok and reactive annotations for automatic generation of
 * boilerplate code and ORM mapping.
 *
 * @author Neha Khan
 * @version 1.0
 */
@Data
@AllArgsConstructor
@Table(name = EVENT_DATA_TABLE_NAME)
public class EventDataEntity {
    @Column("TEMPERATURE")
    Long temperature;
    @Column("HUMIDITY")
    Long humidity;
    @Column("BATTER_LEVEL")
    Long batterLevel;
    @Id
    private Long id;
    @Column("CREATED_BY")
    @CreatedBy
    private String createdBy;
    @CreatedDate
    @Column("CREATED_DATE")
    private Timestamp createdDate;
    @Column("UPDATED_BY")
    @LastModifiedBy
    private String updatedBy;
    @LastModifiedDate
    @Column("UPDATED_DATE")
    private Timestamp updatedDate;
}
