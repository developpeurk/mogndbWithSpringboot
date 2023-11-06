package com.lambarki.mongodbdemo.product;

import com.lambarki.mongodbdemo.dtos.ProductCreateUpdateDTO;
import com.lambarki.mongodbdemo.dtos.ProductResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private ProductCreateUpdateDTO productDTO;
    private ProductResponseDTO productResponseDTO;

    @BeforeEach
    void setUp() {
        // Initialized with sample data, ensure that you have appropriate constructor or setters in the DTO classes
        productDTO = new ProductCreateUpdateDTO("Test Product", "Test Description", null, null); // Assuming tags and category are nullable

        productResponseDTO = new ProductResponseDTO("1", "Test Product", "Test Description", null, null); // Assuming tags and category are nullable
    }

    @Test
    void whenSaveProduct_thenReturnsSavedProduct() {
        when(productService.save(any(ProductCreateUpdateDTO.class))).thenReturn(productResponseDTO);

        ResponseEntity<ProductResponseDTO> response = productController.save(productDTO);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("1", response.getBody().getId());
        assertEquals("Test Product", response.getBody().getName());
        assertEquals("Test Description", response.getBody().getDescription());
    }
}
