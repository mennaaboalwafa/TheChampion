package com.bankmasr.thechampion.domain.audit;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Base abstract class for entities which will hold definitions for created, last modified, created by,
 * last modified by attributes.
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditingEntity implements Serializable {

//    @CreatedBy
//    @Column(name = "CREATED_BY")
//    private long createdBy;
    @CreatedDate
    @Column(name = "CREATED_AT", updatable = false)
    private Timestamp createdAt;
//    @LastModifiedBy
//    @Column(name = "MODIFIED_BY")
//    private Long modifiedBy;
    @LastModifiedDate
    @Column(name = "MODIFIED_AT")
    private Timestamp modifiedAt;
}
