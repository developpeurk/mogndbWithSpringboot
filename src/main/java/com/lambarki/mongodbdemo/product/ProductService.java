package com.lambarki.mongodbdemo.product;

import com.lambarki.mongodbdemo.category.Category;
import com.lambarki.mongodbdemo.category.CategoryRepository;
import com.lambarki.mongodbdemo.dtos.ProductCreateUpdateDTO;
import com.lambarki.mongodbdemo.dtos.ProductResponseDTO;
import com.lambarki.mongodbdemo.exception.EntityNotFoundException;
import com.lambarki.mongodbdemo.utils.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    @Transactional
    public ProductResponseDTO save(ProductCreateUpdateDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with ID: " + productDTO.getCategoryId()));

        Product product = ProductMapper.convertToEntity(productDTO);
        product.setCategory(category); // Set the found category

        Product savedProduct = productRepository.save(product);
        return ProductMapper.convertToResponseDTO(savedProduct);
    }

    public ProductResponseDTO findById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + id));
        return ProductMapper.convertToResponseDTO(product);
    }

    public List<ProductResponseDTO> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductMapper::convertToResponseDTO)
                .toList(); // Using Stream.toList() here
    }


    @Transactional
    public void delete(String id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }

}
