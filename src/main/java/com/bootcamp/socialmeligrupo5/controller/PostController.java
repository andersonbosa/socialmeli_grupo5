package com.bootcamp.socialmeligrupo5.controller;

import com.bootcamp.socialmeligrupo5.dto.CreatePostRequestDTO;
import com.bootcamp.socialmeligrupo5.dto.PostDTO;
import com.bootcamp.socialmeligrupo5.dto.CreatePromoPostRequestDTO;
import com.bootcamp.socialmeligrupo5.service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<PostDTO>> findFollowedSellersRecentPosts(@PathVariable @Positive Long userId) {
        return ResponseEntity.ok(postService.findFollowedSellersLastTwoWeeksPosts(userId));
    }

    @PostMapping("/promo-post")
    public ResponseEntity<?> newPromoPost(@Valid @RequestBody CreatePromoPostRequestDTO dto) {
        postService.registerNewPromoPost(dto);
        return ResponseEntity.ok().build();
    }
}