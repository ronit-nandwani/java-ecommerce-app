package com.ronit.productservice.services;

import com.ronit.productservice.exceptions.ProductNotFoundException;
import com.ronit.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product createProduct(Product product);

    boolean deleteProduct(Long productId);
}
