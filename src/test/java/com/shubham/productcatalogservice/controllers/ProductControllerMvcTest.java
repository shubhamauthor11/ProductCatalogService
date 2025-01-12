package com.shubham.productcatalogservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shubham.productcatalogservice.dtos.ProductDto;
import com.shubham.productcatalogservice.models.Product;
import com.shubham.productcatalogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerMvcTest {

    @MockBean
    private IProductService productService;

    @Autowired
    private MockMvc mockMvc; //simulation of like postman

    @Autowired
    private ObjectMapper objectMapper; //library use to convert data from productdto to json to string

    @Test
    public void Test_GetAllProducts_RunsSuccessfully() throws Exception { //testing getAllProducts() api from productcontroller

        //Arrange
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Iphone");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Macbook");

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        when(productService.getAllProducts()).thenReturn(productList);

        ProductDto productDto1 = new ProductDto();
        productDto1.setName("Iphone");
        productDto1.setId(1L);

        ProductDto productDto2 = new ProductDto();
        productDto2.setId(2L);
        productDto2.setName("Macbook");

        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(productDto1);
        productDtos.add(productDto2);

        //Act and Assert
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDtos)))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Iphone"))//asserting name of 1 json response
                .andExpect(jsonPath("$[1].length()").value(2));//asserting the length of response i.e. no of attributes from 1 response
                //.andExpect(jsonPath("$[1].name.length()").value(7));   //this doesn't work
    }

    @Test
    public void Test_CreateProduct_RunsSuccessfully() throws Exception {

        //Arrange
        Product product = new Product();
        product.setId(3L);
        product.setName("Ipad");

        when(productService.save(any(Product.class))).thenReturn(product); //using any because we can't pass the same product and return same to avoid same hashId and reference

        ProductDto productDto = new ProductDto();
        productDto.setId(3L);
        productDto.setName("Ipad");

        //Act and Assert
        mockMvc.perform(post
                        ("/products")
                        .content(objectMapper.writeValueAsString(productDto)) //passing the required data in post request in post
                        .contentType(MediaType.APPLICATION_JSON)//passing the header in post
                )
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDto)))
                .andExpect(jsonPath("$.id").value(product.getId())) //asserting the specific properties from json response i.e. id in this case
                .andExpect(jsonPath("$.name").value(product.getName()));
    }

//    {
//        "name" : "Ipad",
//        "id" : 2
//    }

}