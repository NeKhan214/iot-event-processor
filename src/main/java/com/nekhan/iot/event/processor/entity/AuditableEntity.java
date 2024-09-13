package com.nekhan.iot.event.processor.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableEntity {
    @Column(name = "CREATED_BY", nullable = false, updatable = false)
    @CreatedBy
    private String createdBy;
    @CreatedDate
    @Column(name = "CREATED_DATE", nullable = false, updatable = false)
    private Timestamp createdDate;
    @Column(name = "UPDATED_BY", nullable = false)
    @LastModifiedBy
    private String updatedBy;
    @LastModifiedDate
    @Column(name = "UPDATED_DATE", nullable = false)
    private Timestamp updatedDate;
}
