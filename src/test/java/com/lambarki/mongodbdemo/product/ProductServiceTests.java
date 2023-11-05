package com.lambarki.mongodbdemo.product;

import com.lambarki.mongodbdemo.dtos.ProductCreateUpdateDTO;
import com.lambarki.mongodbdemo.dtos.ProductResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private ProductCreateUpdateDTO createUpdateDTO;
    private Product product;

    @BeforeEach
    void setUp() {
        createUpdateDTO = new ProductCreateUpdateDTO("Test Product", "Test Description");
        product = new Product("1", "Test Product", "Test Description");
    }

    @Test
    void whenSavingProduct_itShouldReturnProductResponseDTO() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductResponseDTO responseDTO = productService.save(createUpdateDTO);

        assertNotNull(responseDTO);
        assertEquals("1", responseDTO.getId());
        assertEquals("Test Product", responseDTO.getName());
        assertEquals("Test Description", responseDTO.getDescription());
    }
}
