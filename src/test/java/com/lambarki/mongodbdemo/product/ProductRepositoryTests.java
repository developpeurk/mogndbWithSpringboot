package com.lambarki.mongodbdemo.product;

import org.junit.jupiter.api.AfterEach;
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
        // Given
        Product product = new Product(null, "Test Product", "Test Description", null, null); // Assuming 'tags' and 'category' are other fields

        // When
        Product savedProduct = productRepository.save(product);

        // Then
        assertNotNull(savedProduct.getId(), "Saved product should have a non-null ID");
        Product foundProduct = productRepository.findById(savedProduct.getId()).orElseThrow(
                () -> new AssertionError("Product not found with id: " + savedProduct.getId())
        );

        assertEquals(savedProduct.getName(), foundProduct.getName(), "Product names should match");
        assertEquals(savedProduct.getDescription(), foundProduct.getDescription(), "Product descriptions should match");
        // Include more assertions if there are more fields to check
    }

    @AfterEach
    void tearDown() {
        // Cleanup the database after each test
        productRepository.deleteAll();
    }
}
