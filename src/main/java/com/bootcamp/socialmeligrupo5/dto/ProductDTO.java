package com.bootcamp.socialmeligrupo5.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record ProductDTO(
        @Positive
        Long product_id,
        @NotEmpty
        String product_name,
        @NotEmpty
        String type,
        @NotEmpty
        String brand,
        @NotEmpty
        String color,
        @NotEmpty
        String notes) {}