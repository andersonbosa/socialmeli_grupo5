package com.bootcamp.socialmeligrupo5.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreatePostRequestDTO(@NotNull @Positive Long user_id, @NotNull String date, @Valid ProductDTO product, @NotNull @Positive Integer category, @NotNull @Positive Double price) {}
