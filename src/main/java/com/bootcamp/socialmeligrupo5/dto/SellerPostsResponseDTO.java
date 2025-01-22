package com.bootcamp.socialmeligrupo5.dto;

import java.util.List;

public record SellerPostsResponseDTO(Long userId, List<PostDTO> posts) {
}
