package com.bootcamp.socialmeligrupo5.controller;

import com.bootcamp.socialmeligrupo5.annotations.Id;
import com.bootcamp.socialmeligrupo5.dto.*;
import com.bootcamp.socialmeligrupo5.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/products")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public ResponseEntity<?> newPost(@Valid @RequestBody CreatePostRequestDTO postDto) {
        postService.registerNewPost(postDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<SellerPostsResponseDTO> findFollowedSellersRecentPosts(@PathVariable @Id Long userId) {
        return ResponseEntity.ok(postService.findFollowedSellersLastTwoWeeksPosts(userId));
    }

    @PostMapping("/promo-post")
    public ResponseEntity<?> newPromoPost(@Valid @RequestBody CreatePromoPostRequestDTO dto) {
        postService.registerNewPromoPost(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/promo-post/count")
    public ResponseEntity<PromoProductsCountResponseDTO> getPromoPostCount(@RequestParam(name = "user_id") Long userId) {
        PromoProductsCountResponseDTO dto = this.postService.countSellerPromoProducts(userId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/promo-post/list")
    public ResponseEntity<PromoProductsListResponseDTO> getPromoPostBySellerId(@RequestParam(name = "user_id") Long userId) {
        PromoProductsListResponseDTO dto = postService.promoPostBySellerId(userId);
        return ResponseEntity.ok(dto);
    }
}