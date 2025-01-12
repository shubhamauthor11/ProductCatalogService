package com.shubham.productcatalogservice.services;

import com.shubham.productcatalogservice.models.Product;
import com.shubham.productcatalogservice.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary // it means this class will always be refer
@Service("sps") // naming the service which we can use with qualifer to autowired the class
public class StorageProductService implements IProductService{

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product getProductById(Long productId) {
        Optional<Product> productOptional= productRepo.findProductById(productId);
        return productOptional.orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product replaceProduct(Long productId, Product request) {
        return null;
    }

    @Override
    public Product save(Product product) {
        return productRepo.save(product);
    }
}
