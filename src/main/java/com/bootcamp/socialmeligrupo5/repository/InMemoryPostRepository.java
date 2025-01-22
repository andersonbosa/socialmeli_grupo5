package com.bootcamp.socialmeligrupo5.repository;

import com.bootcamp.socialmeligrupo5.entity.Post;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.time.LocalDate;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class InMemoryPostRepository implements PostRepository {
	public List<Post> posts = new ArrayList<>();

	public InMemoryPostRepository() {
		loadDatabase();
	}

	@Override
	public void create(Post post) {
		posts.add(post);
	}

	@Override
	public List<Post> findBySellerIdBetweenDates(List<Long> sellerIds, LocalDate start, LocalDate end) {
		return posts.stream().filter(
				post -> sellerIds.contains(post.getSellerId()) && (!post.getDate().isBefore(start) && !post.getDate().isAfter(end))
		).sorted(Comparator.comparing(Post::getDate).reversed()).toList();
	}

	@Override
	public List<Post> findBySellerId(Long sellerId) {
		return posts.stream()
				.filter(post -> post.getSellerId().equals(sellerId))
				.toList();
	}

	private void loadDatabase() {
		try {
			ObjectMapper objectMapper = new ObjectMapper();

			// support Java 8 date time apis
			objectMapper.registerModule(new JavaTimeModule());

			File file = ResourceUtils.getFile("src/main/resources/posts.json");
			List<Post> newPosts = objectMapper.readValue(file, new TypeReference<List<Post>>() {
			});
			posts.addAll(newPosts);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public List<Post> findAll() {
		return posts;
	}
}
