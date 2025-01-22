package com.bootcamp.socialmeligrupo5.dto;

import java.time.LocalDate;

public record PostDTO(Long userId, Long postId, LocalDate date, ProductDTO product, Integer category, Double price) {
}
