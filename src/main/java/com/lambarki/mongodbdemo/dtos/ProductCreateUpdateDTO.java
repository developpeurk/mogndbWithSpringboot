package com.lambarki.mongodbdemo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateUpdateDTO {
    @NotBlank(message = "Product name must not be empty.")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters long.")
    private String name;

    @NotBlank(message = "Product description must not be empty.")
    @Size(max = 500, message = "Product description must not exceed 500 characters.")
    private String description;
    private List<String> tags;
    private String categoryId; // This can be used to associate a product with a category



}