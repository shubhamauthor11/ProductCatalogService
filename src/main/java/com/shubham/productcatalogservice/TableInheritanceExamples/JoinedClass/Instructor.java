package com.shubham.productcatalogservice.TableInheritanceExamples.JoinedClass;

import jakarta.persistence.Entity;

@Entity(name="jc_instructor")
public class Instructor extends User {
    String company;
}
