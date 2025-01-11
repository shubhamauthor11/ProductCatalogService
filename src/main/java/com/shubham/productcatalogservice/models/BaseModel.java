package com.shubham.productcatalogservice.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass; // coming post adding jpa
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass // adding fields but will not create table
public abstract class BaseModel {
    @Id // assuring as primary key
    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private State state;
}
