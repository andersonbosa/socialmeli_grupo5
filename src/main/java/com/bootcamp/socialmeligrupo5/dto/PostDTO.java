package com.bootcamp.socialmeligrupo5.dto;

public record PostDto(Long user_id, String date, ProductDto product, Integer category, Double price) {}
