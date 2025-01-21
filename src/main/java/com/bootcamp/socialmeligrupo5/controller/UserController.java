package com.bootcamp.socialmeligrupo5.controller;

import com.bootcamp.socialmeligrupo5.service.BuyerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping("/{userId}/followed/list")
  public ResponseEntity<?> sellersFollowedByUser(
          @PathVariable Long userId
  ) {
    return new ResponseEntity<>(buyerService.sellersFollowedByUser(userId), HttpStatus.OK);
  }


}
