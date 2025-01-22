package com.bootcamp.socialmeligrupo5.controller;

import com.bootcamp.socialmeligrupo5.dto.CreatePostRequestDTO;
import com.bootcamp.socialmeligrupo5.dto.CreatePromoPostRequestDTO;
import com.bootcamp.socialmeligrupo5.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/promo-post")
    public ResponseEntity<?> newPromoPost(@Valid @RequestBody CreatePromoPostRequestDTO dto) {
        postService.registerNewPromoPost(dto);
        return ResponseEntity.ok().build();
    }
}