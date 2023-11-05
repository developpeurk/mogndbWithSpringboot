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
import static org.mockito.Mockito.*;
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
        productDTO = new ProductCreateUpdateDTO();
        productDTO.setName("Test Product");
        productDTO.setDescription("Test Description");

        productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId("1");
        productResponseDTO.setName("Test Product");
        productResponseDTO.setDescription("Test Description");
    }

    @Test
    void whenSaveProduct_thenReturnsSavedProduct() {
        when(productService.save(any(ProductCreateUpdateDTO.class))).thenReturn(productResponseDTO);

        ResponseEntity<ProductResponseDTO> response = productController.save(productDTO);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Test Product", response.getBody().getName());
    }

}
