package com.ronit.productservice.services;

import com.ronit.productservice.exceptions.CategoryNotFoundException;
import com.ronit.productservice.exceptions.ProductNotFoundException;
import com.ronit.productservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product createProduct(Product product) throws CategoryNotFoundException;

    void deleteProduct(Long productId) throws ProductNotFoundException;

    Page<Product> getProductsByTitle(String title, int pageNumber, int pageSize);
}
