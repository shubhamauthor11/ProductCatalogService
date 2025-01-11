package com.shubham.productcatalogservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product extends BaseModel {
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    @ManyToOne(cascade = CascadeType.ALL) // many is class one is field
    // cascade is useful when there is no cateogry present and if we add list 1 procuct then cateogry will automatically created and if no product is there then complete category will be deleted
    // to do read all cascade type
    private Category category;
    private Boolean isPrime;
}
