package com.bootcamp.socialmeligrupo5.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreatePromoPostRequestDTO(
        @Positive(message = "user_id deve ser um número positivo") Long user_id,
        @NotNull(message = "date não deve ser vazio") String date,
        @Valid ProductDTO product,
        @Positive(message = "category deve ser um número positivo") Integer category,
        @Positive(message = "price deve ser um número positivo") Double price,
        @NotNull(message = "has_promo não deve ser vazio") Boolean has_promo,
        @Min(value = 0, message = "discount deve ser valor entre 1 e 0") @Max(value = 1, message = "discount deve ser valor entre 1 e 0") Double discount
) {
}
