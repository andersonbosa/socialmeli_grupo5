package com.bootcamp.socialmeligrupo5.dto;

import com.bootcamp.socialmeligrupo5.annotations.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreatePostRequestDTO(
		@Id Long userId,
		@NotNull
		String date,
		@Valid ProductDTO product,
		@Positive Integer category,
		@Positive Double price
) {
}
