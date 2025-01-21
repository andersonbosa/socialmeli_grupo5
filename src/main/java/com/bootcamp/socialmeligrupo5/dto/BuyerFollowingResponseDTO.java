package com.bootcamp.socialmeligrupo5.dto;

import java.util.List;

public record BuyerFollowingResponseDTO(Long userId, String userName, List<UserResponseDTO> following) {
}