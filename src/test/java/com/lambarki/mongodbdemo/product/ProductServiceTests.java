package com.lambarki.mongodbdemo.product;

import com.lambarki.mongodbdemo.category.Category;
import com.lambarki.mongodbdemo.category.CategoryRepository;
import com.lambarki.mongodbdemo.dtos.ProductCreateUpdateDTO;
import com.lambarki.mongodbdemo.dtos.ProductResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTests {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    private ProductCreateUpdateDTO createUpdateDTO;
    private Product product;
    private Category mockCategory;

    @BeforeEach
    void setUp() {
        // Assuming tags and categoryId can be null
        createUpdateDTO = new ProductCreateUpdateDTO(
                "Test Product",
                "Test Description",
                new ArrayList<>(),
                "some-category-id"
        );

        // Assuming Category is a class with an appropriate constructor
        mockCategory = new Category("some-category-id", "Category Name", "Category Description");

        product = new Product("1", "Test Product", "Test Description", new ArrayList<>(), mockCategory);

        when(categoryRepository.findById("some-category-id")).thenReturn(Optional.of(mockCategory));
        when(productRepository.save(any(Product.class))).thenReturn(product);
    }

    @Test
    void whenSavingProduct_itShouldReturnProductResponseDTO() {
        ProductResponseDTO responseDTO = productService.save(createUpdateDTO);

        assertNotNull(responseDTO);
        assertEquals("1", responseDTO.getId());
        assertEquals("Test Product", responseDTO.getName());
        assertEquals("Test Description", responseDTO.getDescription());
        // If you want to check the category as well, add this
        assertEquals(mockCategory.getId(), responseDTO.getCategory().getId());
    }

    // Additional tests...
}
