package com.shubham.productcatalogservice.controllers;

import com.shubham.productcatalogservice.dtos.CategoryDto;
import com.shubham.productcatalogservice.dtos.ProductDto;
import com.shubham.productcatalogservice.models.Category;
import com.shubham.productcatalogservice.models.Product;
import com.shubham.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController// @RestController is a specialized @Controller annotation. @RestController is best used when you want to return data (e.g. JSON or XML) rather than a view (HTML).
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    public List<ProductDto> getAllProducts(){
        List<ProductDto> productDtos = new ArrayList<>();
        List<Product> products = productService.getAllProducts();
        for (Product product : products){
            productDtos.add(from(product));
        }
        return  productDtos;
    }

    @GetMapping("{productId}")
//    public Product findProductById(@PathVariable("productId") Long id){ // when pathvariable and parameter is different
    public ResponseEntity<ProductDto> findProductById(@PathVariable Long productId){
        try{
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            if(productId <= 0){
                headers.add("calledBy", "wrongperson");
                throw new IllegalArgumentException("Please try with productId > 0");
//                return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
            }
            Product product = productService.getProductById(productId);
            headers.add("calledBy", "intelligent");
            if(product == null)
                return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(from(product), headers, HttpStatus.OK);
        }catch (IllegalArgumentException exception){
            throw exception;
        }
    }

    private ProductDto from (Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if(product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody Product product){
        return null;
    }

    @PutMapping("/{id}")
    public ProductDto replaceProduct(@PathVariable Long id, @RequestBody ProductDto request){

        Product productRequest = from(request);
        Product product = productService.replaceProduct(id, productRequest);
        return from(product);
    }

    private Product from(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        if(!Objects.isNull(product.getCategory())){
            Category category = new Category();
            category.setName(productDto.getCategory().getName());
            product.setCategory(category);
        }
        return product;
    }
}
