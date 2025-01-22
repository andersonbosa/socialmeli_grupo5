package com.bootcamp.socialmeligrupo5.controller;

import com.bootcamp.socialmeligrupo5.annotations.Id;
import com.bootcamp.socialmeligrupo5.dto.BuyerFollowingResponseDTO;
import com.bootcamp.socialmeligrupo5.dto.FollowersCountResponseDTO;
import com.bootcamp.socialmeligrupo5.dto.SellerFollowersResponseDTO;
import com.bootcamp.socialmeligrupo5.service.BuyerService;
import com.bootcamp.socialmeligrupo5.service.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
            @PathVariable @Id Long userId,
            @PathVariable @Id Long userIdToFollow
    ) {
        buyerService.followSeller(userId, userIdToFollow);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<FollowersCountResponseDTO> followersCount(
            @PathVariable @Id Long userId
    ) {
        return ResponseEntity.ok().body(sellerService.followersCount(userId));
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<SellerFollowersResponseDTO> listSellerFollowers(
        @PathVariable @Id Long userId,
        @RequestParam(required = false) String order
    ) {
        return ResponseEntity.ok().body(sellerService.listSellerFollowers(userId, order));
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<BuyerFollowingResponseDTO> buyerFollowing(
            @PathVariable @Id Long userId,
            @RequestParam(required = false) String order
    ) {
        return ResponseEntity.ok().body(buyerService.buyerFollowing(userId, order));
    }

}


