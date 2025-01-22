package com.bootcamp.socialmeligrupo5.repository;

import com.bootcamp.socialmeligrupo5.entity.Post;

import java.util.List;

public interface PostRepository {
    void create(Post post);

    List<Post> findBySellerId(Long sellerId);
    List<Post> findAll();
}
