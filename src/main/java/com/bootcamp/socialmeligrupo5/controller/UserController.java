package com.bootcamp.socialmeligrupo5.controller;

import com.bootcamp.socialmeligrupo5.dto.SellerFollowersResponseDTO;
import com.bootcamp.socialmeligrupo5.service.BuyerService;
import com.bootcamp.socialmeligrupo5.service.SellerService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
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

  @GetMapping("/{userId}/followers/list")
  public ResponseEntity<SellerFollowersResponseDTO> listSellerFollowers(@PathVariable @NotNull Long userId) {
    return ResponseEntity.ok(sellerService.listSellerFollowers(userId));
  }
}
