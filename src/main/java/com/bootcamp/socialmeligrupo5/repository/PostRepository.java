package com.bootcamp.socialmeligrupo5.repository;

import com.bootcamp.socialmeligrupo5.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    void create(Post post);

    List<Post> findBySellerId(Long sellerId);
}
