package com.bootcamp.socialmeligrupo5.entity;

import java.util.HashSet;
import java.util.Set;

public class Buyer extends User {
		private final Set<Seller> following = new HashSet<>();

		public Buyer(Long id, String name) {
				super(id, name);
		}

		public Set<Seller> getFollowing() {
				return following;
		}

		public void follow(Seller seller) {
				this.following.add(seller);
		}

		public void unfollow(Seller seller) {
				this.following.remove(seller);
		}
}
