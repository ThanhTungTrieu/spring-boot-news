package com.springbootweb.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "createdDate")
    @CreatedDate
    protected Date createdDate;

    @Column(name = "createdBy")
    @CreatedBy
    protected String createdBy;

    @Column(name = "modifiedDate")
    @LastModifiedDate
    protected Date modifiedDate;

    @Column(name = "modifiedBy")
    @LastModifiedBy
    protected String modifiedBy;

}
