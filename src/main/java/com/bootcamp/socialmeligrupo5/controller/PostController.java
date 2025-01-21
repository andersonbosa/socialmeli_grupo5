package com.bootcamp.socialmeligrupo5.controller;

import com.bootcamp.socialmeligrupo5.dto.PostDTO;
import com.bootcamp.socialmeligrupo5.service.PostService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public ResponseEntity<?> newPost(@RequestBody @NotNull(message = "A publicação possui atributos incorretos.") PostDTO postDto) {
        postService.registerNewPost(postDto);
        return ResponseEntity.ok().build();
    }
}