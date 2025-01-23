package com.bootcamp.socialmeligrupo5.dto;

import com.bootcamp.socialmeligrupo5.annotations.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreatePromoPostRequestDTO(
		@Id Long userId,
		@NotNull String date,
		@Valid ProductDTO product,
		@Positive Integer category,
		@Positive Double price,
		@NotNull Boolean hasPromo,
		@Min(value = 0)
		@Max(value = 1)
		Double discount
) {
}
