package com.bootcamp.socialmeligrupo5.service;

import com.bootcamp.socialmeligrupo5.repository.PostRepository;

public class PostService {
		private final PostRepository postRepository;

		public PostService(PostRepository postRepository) {
				this.postRepository = postRepository;
		}

}
