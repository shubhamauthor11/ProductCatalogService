package com.shubham.productcatalogservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String name;
    private String description;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY) // mappedBy to tell jpa you have already considered this relationship this comes in case of one to many and many to one
    @Fetch(FetchMode.SUBSELECT)
//    @BatchSize(size = 10) // use this with select fetchtypemode
    private List<Product> products;
}
