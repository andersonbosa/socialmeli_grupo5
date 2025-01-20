package com.bootcamp.socialmeligrupo5.controller;

import com.bootcamp.socialmeligrupo5.service.BuyerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final BuyerService buyerService;

  public UserController(BuyerService buyerService) {
    this.buyerService = buyerService;
  }

  @PostMapping("/{userId}/follow/{userIdToFollow}")
  public ResponseEntity<?> followSeller(
      @PathVariable Long userId,
      @PathVariable Long userIdToFollow
  ) {
    buyerService.followSeller(userId, userIdToFollow);
    return ResponseEntity.ok().build();
  }


}
