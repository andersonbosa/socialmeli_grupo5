package com.bootcamp.socialmeligrupo5.dto;

import com.bootcamp.socialmeligrupo5.annotations.Id;
import jakarta.validation.constraints.NotEmpty;

public record ProductDTO(
    @Id
    Long productId,
    @NotEmpty
    String productName,
    @NotEmpty
    String type,
    @NotEmpty
    String brand,
    @NotEmpty
    String color,
    @NotEmpty
    String notes) {
}
