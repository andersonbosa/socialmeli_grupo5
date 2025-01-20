package com.bootcamp.socialmeligrupo5.repository;

import com.bootcamp.socialmeligrupo5.entity.Seller;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemorySellerRepository implements SellerRepository {
		public List<Seller> sellers;

		public InMemorySellerRepository() {}

		@Override
		public Seller findById(Long id) {
				return sellers.stream().filter(seller -> seller.getId().equals(id)).findFirst().orElse(null);
		}

		@Override
		public void update(Seller seller) {

		}
}
