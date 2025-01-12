package com.shubham.productcatalogservice.repos;

import com.shubham.productcatalogservice.models.Category;
import com.shubham.productcatalogservice.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    /*
    if we use fetch type as lazy, then only asked table will call
    or if we use eager type then join will be called from both the tables if we asked that table or not.
     */
    @Test
    @Transactional // this is used to test on the same db of production when we run any transaction then as soon as execution completed rollback will happen
    public void testFetchTypes(){
        Category category = categoryRepo.findById(22L).get();
        for(Product product : category.getProducts()){
            System.out.println(product.getName());
        }
    }

    @Test
    @Transactional /* if we use fetchtypemode as select and we try to fetch products for each category then
    it will consume more time and create queries 1 for category and no of rows for product from category
    to solve this use subselect fetchtypemode in that case only 1 query for product will be created
    , also we can have batchsize with select then in that case 1 batch of that size from product table
    along with 1 from category table
    */
    public void testOnePlusJPAProblem(){
        List<Category> categoryList = categoryRepo.findAll();
        for (Category category : categoryList){
            for(Product product : category.getProducts()){
                System.out.println(product.getName());
            }
        }
    }
}