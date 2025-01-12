package com.shubham.productcatalogservice.TableInheritanceExamples.JoinedClass;

import jakarta.persistence.Entity;

@Entity(name="jc_mentor")
public class Mentor extends User {
    Double ratings;
}
