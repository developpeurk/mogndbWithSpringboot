package com.lambarki.mongodbdemo.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void whenSavingProduct_itShouldBeRetrievable() {
        Product product = new Product(null, "Test Product", "Test Description");
        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct.getId());
        Product foundProduct = productRepository.findById(savedProduct.getId()).orElse(null);

        assertNotNull(foundProduct);
        assertEquals(savedProduct.getName(), foundProduct.getName());
        assertEquals(savedProduct.getDescription(), foundProduct.getDescription());
    }
}