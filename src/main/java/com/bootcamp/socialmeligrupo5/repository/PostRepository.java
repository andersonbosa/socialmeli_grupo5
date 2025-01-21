package com.bootcamp.socialmeligrupo5.repository;

import com.bootcamp.socialmeligrupo5.entity.Post;

import java.time.LocalDate;
import java.util.List;

public interface PostRepository {
		void create(Post post);
		List<Post> findBySellerIdBetweenDates(Long sellerId, LocalDate start, LocalDate end);
    List<Post> findBySellerId(Long sellerId);
}
