package com.ronit.productservice.controllers;

import com.ronit.productservice.exceptions.ProductNotFoundException;
import com.ronit.productservice.models.Product;
import com.ronit.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @MockitoBean
    @Qualifier("selfProductService")
    private ProductService productService;

    @Autowired
    private ProductController productController;

    @Test
    void testGetSingleProductPositiveCase() throws ProductNotFoundException {
        //Arrange
        Long productId = 10L;
        Product expectedProduct = new Product(); // @198347
        expectedProduct.setId(productId);
        expectedProduct.setTitle("iPhone 16");
        expectedProduct.setPrice(70000.0);

        when(productService.getSingleProduct(productId)).thenReturn(expectedProduct);  // @198347

        //Act
        Product actualProduct = productController.getSingleProduct(productId);

        //Assert
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    public void testGetSingleProductThrowsProductNotFoundException() throws ProductNotFoundException {
        ProductNotFoundException productNotFoundException = new ProductNotFoundException(-1L);
        when(productService.getSingleProduct(-1L))
                .thenThrow(productNotFoundException);

        Exception exception = assertThrows(
                ProductNotFoundException.class,
                () -> productController.getSingleProduct(-1L)
        );

        assertEquals(productNotFoundException.getMessage(), exception.getMessage());
    }
}