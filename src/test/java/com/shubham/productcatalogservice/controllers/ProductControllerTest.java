package com.shubham.productcatalogservice.controllers;

import com.shubham.productcatalogservice.dtos.ProductDto;
import com.shubham.productcatalogservice.models.Product;
import com.shubham.productcatalogservice.services.IProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean // mock the bean of the service
    private IProductService productService;

    @Captor
    private ArgumentCaptor<Long> idCaptor; //to check are we passing the correct argument in service and controller layer

    @Test
    @DisplayName("get product with id 4 will run fine")
    public void Test_GetProductById_ReturnProductSuccessfully(){ // testing findProductById()

        //Arrange
        Long productId = 4L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Iphone");
        when(productService.getProductById(productId)).thenReturn(new Product());

        //Act
        ResponseEntity<ProductDto> response = productController.findProductById(productId);

        //Assert
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(productId, response.getBody().getId());
        verify(productService,times(1)).getProductById(productId); // validate if there is interaction happens with productsservice at 1 time above in the method
    }

    @Test
    public void Test_GetProductById_CalledWithInvalidId_ResultsInIllegalArgumentException(){

        //Act and Assert
        Exception exception = assertThrows(IllegalArgumentException.class, ()->
                productController.findProductById(-1L));

        assertEquals("Please try with productId > 0",exception.getMessage());
        verify(productService,times(0)).getProductById(-1L);

        //Todo: for productId = 0
    }

    @Test
    public void Test_GetProductById_ProductServiceCalledWithCorrectArguments_RunSuccessfully(){

        //Arrange
        Long productId = 4L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Iphone");
        when(productService.getProductById(productId)).thenReturn(new Product());

        //Act
        ResponseEntity<ProductDto> response = productController.findProductById(productId);

        //Assert
        verify(productService).getProductById(idCaptor.capture()); //capturing the argument value of id
        assertEquals(4L, idCaptor.getValue());
    }

}