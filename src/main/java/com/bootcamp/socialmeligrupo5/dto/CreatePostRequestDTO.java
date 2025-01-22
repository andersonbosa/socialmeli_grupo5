package com.bootcamp.socialmeligrupo5.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreatePostRequestDTO(
        @Positive(message = "userId deve ser um número positivo") Long userId,
        @NotNull(message = "date não deve ser vazio") String date,
        @Valid ProductDTO product,
        @Positive(message = "category deve ser um número positivo") Integer category,
        @Positive(message = "price deve ser um número positivo") Double price
) {}
