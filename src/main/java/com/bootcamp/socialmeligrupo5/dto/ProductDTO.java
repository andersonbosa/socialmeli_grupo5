package com.bootcamp.socialmeligrupo5.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record ProductDTO(
        @Positive(message = "product_id deve ser um número positivo")
        Long product_id,
        @NotEmpty(message = "product_name não deve ser vazio")
        String product_name,
        @NotEmpty(message = "type não deve ser vazio")
        String type,
        @NotEmpty(message = "brand não deve ser vazio")
        String brand,
        @NotEmpty(message = "color não deve ser vazio")
        String color,
        @NotEmpty(message = "notes não deve ser vazio")
        String notes) {}