package com.lambarki.mongodbdemo.product;

import com.lambarki.mongodbdemo.dtos.ProductCreateUpdateDTO;
import com.lambarki.mongodbdemo.dtos.ProductResponseDTO;
import com.lambarki.mongodbdemo.exception.ResourceNotFoundException;
import com.lambarki.mongodbdemo.utils.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public ProductResponseDTO save(ProductCreateUpdateDTO productDTO) {
        Product product = ProductMapper.convertToEntity(productDTO);
        Product savedProduct = repository.save(product);
        return ProductMapper.convertToResponseDTO(savedProduct);
    }

    public ProductResponseDTO findById(String id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return ProductMapper.convertToResponseDTO(product);
    }

    public List<ProductResponseDTO> findAll() {
        List<Product> products = repository.findAll();
        return products.stream()
                .map(ProductMapper::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
