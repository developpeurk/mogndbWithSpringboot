package com.lambarki.mongodbdemo.utils;
import com.lambarki.mongodbdemo.dtos.ProductCreateUpdateDTO;
import com.lambarki.mongodbdemo.dtos.ProductResponseDTO;
import com.lambarki.mongodbdemo.product.Product;

public class ProductMapper {
    private ProductMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Product convertToEntity(ProductCreateUpdateDTO dto) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    public static ProductResponseDTO convertToResponseDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        return dto;
    }
}