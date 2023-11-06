package com.lambarki.mongodbdemo.utils;
import com.lambarki.mongodbdemo.dtos.CategoryDTO;
import com.lambarki.mongodbdemo.dtos.ProductCreateUpdateDTO;
import com.lambarki.mongodbdemo.dtos.ProductResponseDTO;
import com.lambarki.mongodbdemo.product.Product;

import java.util.ArrayList;

public class ProductMapper {
    private ProductMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Product convertToEntity(ProductCreateUpdateDTO dto) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .tags(dto.getTags())
                .build();
    }

    public static ProductResponseDTO convertToResponseDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setTags(product.getTags() != null ? new ArrayList<>(product.getTags()) : null);

        if (product.getCategory() != null) {
            // Map the category if it's not null
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(product.getCategory().getId());
            categoryDTO.setName(product.getCategory().getName());
            // ... Set any other necessary fields from Category to CategoryDTO
            dto.setCategory(categoryDTO);
        } else {
            // Handle the null case appropriately, maybe set category to null or a default value
            dto.setCategory(null);
        }

        return dto;
    }

}