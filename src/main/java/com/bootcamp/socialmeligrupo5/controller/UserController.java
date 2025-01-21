package com.bootcamp.socialmeligrupo5.controller;

import com.bootcamp.socialmeligrupo5.dto.FollowersCountResponseDTO;
import com.bootcamp.socialmeligrupo5.dto.SellerFollowersResponseDTO;
import com.bootcamp.socialmeligrupo5.dto.BuyerFollowingResponseDTO;
import com.bootcamp.socialmeligrupo5.service.BuyerService;
import com.bootcamp.socialmeligrupo5.service.SellerService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

@RestController
@Validated
@RequestMapping("/users")
public class UserController {

    private final BuyerService buyerService;
    private final SellerService sellerService;

    public UserController(BuyerService buyerService, SellerService sellerService) {
        this.buyerService = buyerService;
        this.sellerService = sellerService;
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> followSeller(
            @PathVariable Long userId,
            @PathVariable Long userIdToFollow
    ) {
        buyerService.followSeller(userId, userIdToFollow);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<FollowersCountResponseDTO> followersCount(
            @PathVariable @NotNull @Positive Long userId
    ) {
        return ResponseEntity.ok().body(sellerService.followersCount(userId));
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<SellerFollowersResponseDTO> listSellerFollowers(
        @PathVariable @NotNull @Positive Long userId
    ) {
        return ResponseEntity.ok().body(sellerService.listSellerFollowers(userId));
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<BuyerFollowingResponseDTO> buyerFollowing(
            @PathVariable @NotNull @Positive Long userId
    ) {
        return ResponseEntity.ok().body(buyerService.buyerFollowing(userId));
    }

}


