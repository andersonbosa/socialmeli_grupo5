package com.bootcamp.socialmeligrupo5.dto;

import java.util.List;

public record PromoProductsListResponseDTO(
        Long userId,
        String userName,
        List<PromoPostDTO> posts
) {
}
