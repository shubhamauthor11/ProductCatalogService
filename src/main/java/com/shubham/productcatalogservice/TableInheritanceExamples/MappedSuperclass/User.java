package com.shubham.productcatalogservice.TableInheritanceExamples.MappedSuperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.UUID;

@MappedSuperclass
public abstract class User {
    @Id
    UUID id;
    String email;
}
