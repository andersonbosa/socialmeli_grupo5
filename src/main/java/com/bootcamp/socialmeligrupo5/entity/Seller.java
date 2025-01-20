package com.bootcamp.socialmeligrupo5.entity;

import java.util.HashSet;
import java.util.Set;

public class Seller extends User {
		private final Set<Buyer> followers = new HashSet<>();

		public Seller(Long id, String name) {
				super(id, name);
		}

		public Set<Buyer> getFollowers() {
				return followers;
		}

		public void addFollower(Buyer buyer) {
				this.followers.add(buyer);
		}
}
