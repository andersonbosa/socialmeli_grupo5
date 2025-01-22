package com.bootcamp.socialmeligrupo5.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record ProductDTO(
        @Positive(message = "productId deve ser um número positivo")
        Long productId,
        @NotEmpty(message = "productName não deve ser vazio")
        String productName,
        @NotEmpty(message = "type não deve ser vazio")
        String type,
        @NotEmpty(message = "brand não deve ser vazio")
        String brand,
        @NotEmpty(message = "color não deve ser vazio")
        String color,
        @NotEmpty(message = "notes não deve ser vazio")
        String notes) {}