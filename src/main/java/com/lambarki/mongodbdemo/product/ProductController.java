package com.lambarki.mongodbdemo.product;


import com.lambarki.mongodbdemo.dtos.ProductCreateUpdateDTO;
import com.lambarki.mongodbdemo.dtos.ProductResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;


    @PostMapping
    public ResponseEntity<ProductResponseDTO> save(@Valid @RequestBody ProductCreateUpdateDTO productDTO) {
        ProductResponseDTO savedProductDTO = service.save(productDTO);
        return new ResponseEntity<>(savedProductDTO, HttpStatus.CREATED);
    }



    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        List<ProductResponseDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable("productId") String productId) {
        ProductResponseDTO responseDTO = service.findById(productId);
        return ResponseEntity.ok(responseDTO);
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> delete(@PathVariable("productId") String productId) {
        service.delete(productId);
        return ResponseEntity.noContent().build();
    }
}