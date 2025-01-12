package com.shubham.productcatalogservice.repos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    public void testJpa() {

//        List<Product> productList = productRepo.findProductByOrderByPrice();
//        for (Product product : productList) {
//            System.out.println(product.getPrice());
//        }

        System.out.println(productRepo.findProductTitleById(25L));
        System.out.println(productRepo.findCategoryNameFromProductId(25L));
    }

}