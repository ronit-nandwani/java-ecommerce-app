package com.ronit.productservice.services;

import com.ronit.productservice.exceptions.CategoryNotFoundException;
import com.ronit.productservice.models.Category;
import com.ronit.productservice.models.Product;
import com.ronit.productservice.exceptions.ProductNotFoundException;
import com.ronit.productservice.models.Product;
import com.ronit.productservice.repositories.CategoryRepository;
import com.ronit.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
//        Optional<Product> optionalProduct = productRepository.findById(productId);
//
//        if (optionalProduct.isEmpty()) {
//            throw new ProductNotFoundException(productId);
//        }
//
//        return optionalProduct.get();

        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) throws CategoryNotFoundException {
        Category category = product.getCategory();

        if (category == null) {
            throw new CategoryNotFoundException("Product can't be created without category, Please try again with category.");
        }

        //Find the category with the title
        Optional<Category> optionalCategory = categoryRepository.findByTitle(category.getTitle());

        if (optionalCategory.isEmpty()) {
            //There's no category in the DB with the given title.
            //Create a category object and save it in the DB.
            category = categoryRepository.save(category);
        } else {
            category = optionalCategory.get();
        }
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) throws ProductNotFoundException {
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException(productId);
        }
        productRepository.deleteById(productId);
    }
}
