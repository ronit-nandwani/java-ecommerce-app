package com.ronit.productservice.controllers;

import com.ronit.productservice.commons.AuthCommons;
import com.ronit.productservice.exceptions.CategoryNotFoundException;
import com.ronit.productservice.exceptions.ProductNotFoundException;
import com.ronit.productservice.models.Product;
import com.ronit.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private AuthCommons authCommons;
    private ProductService productService;

    public ProductController(@Qualifier("selfProductService") ProductService productService, AuthCommons authCommons) {
        this.productService = productService;
        this.authCommons = authCommons;
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
        //UserDto userDto = authCommons.validateToken(token);

//        if (userDto == null) {
//            //UnAuthorized access.
//            throw new UnAuthorizedException("Invalid token provided.");
//        }
        return productService.getSingleProduct(productId);
    }

    @GetMapping("/")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/")
    public Product createProduct(@RequestBody Product product) throws CategoryNotFoundException {
        return productService.createProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
        productService.deleteProduct(productId);
    }

    @GetMapping("/title/{title}/{pageNumber}/{pageSize}")
    public Page<Product> getProductByTitle(@PathVariable("title") String title, @PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize) {
        return productService.getProductsByTitle(title, pageNumber, pageSize);
    }
}
