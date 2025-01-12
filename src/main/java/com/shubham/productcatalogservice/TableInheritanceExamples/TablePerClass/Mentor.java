package com.shubham.productcatalogservice.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name="pc_mentor")
public class Mentor extends User {
    Double ratings;
}
