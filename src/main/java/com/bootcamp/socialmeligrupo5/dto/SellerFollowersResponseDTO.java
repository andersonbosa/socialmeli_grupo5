package com.bootcamp.socialmeligrupo5.dto;

import java.util.List;

public record SellerFollowersResponseDTO(Long userId, String userName, List<UserResponseDTO> followers) {
}
