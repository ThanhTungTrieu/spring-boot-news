package com.springbootweb.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
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

    public Long getId() {
        return id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

}
