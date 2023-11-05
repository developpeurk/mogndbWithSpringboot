package com.lambarki.mongodbdemo.product;

import com.lambarki.mongodbdemo.dtos.ProductCreateUpdateDTO;
import com.lambarki.mongodbdemo.dtos.ProductResponseDTO;
import com.lambarki.mongodbdemo.utils.ProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testSaveProduct() {
        ProductCreateUpdateDTO dto = new ProductCreateUpdateDTO();
        dto.setName("Test Product");
        dto.setDescription("Test Description");

        Product product = ProductMapper.convertToEntity(dto);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductResponseDTO savedDto = productService.save(dto);

        assertNotNull(savedDto);
        assertEquals(dto.getName(), savedDto.getName());
        assertEquals(dto.getDescription(), savedDto.getDescription());
    }
}
