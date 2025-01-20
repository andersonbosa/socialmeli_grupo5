package com.bootcamp.socialmeligrupo5.repository;

import com.bootcamp.socialmeligrupo5.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryPostRepository implements PostRepository {
		public List<Post> posts = new ArrayList<>();

		@Override
		public void create(Post post) {
				posts.add(post);
		}
}
