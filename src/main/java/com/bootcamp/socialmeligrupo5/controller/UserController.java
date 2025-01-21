package com.bootcamp.socialmeligrupo5.controller;

import com.bootcamp.socialmeligrupo5.dto.FollowersCountResponseDTO;
import com.bootcamp.socialmeligrupo5.dto.SellerFollowersResponseDTO;
import com.bootcamp.socialmeligrupo5.service.BuyerService;
import com.bootcamp.socialmeligrupo5.service.SellerService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
            @PathVariable @NotNull @Positive Long userId,
            @RequestParam(required = false) String order
    ) {

        SellerFollowersResponseDTO responseDTO = order == null ?
                sellerService.listSellerFollowers(userId) :
                sellerService.listFollowersWithOrder(userId, order);

        return ResponseEntity.ok().body(responseDTO);
    }
}
