package com.bootcamp.socialmeligrupo5.repository;

import com.bootcamp.socialmeligrupo5.entity.Buyer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryBuyerRepository implements BuyerRepository {
		public List<Buyer> buyers = new ArrayList<>();

		public InMemoryBuyerRepository() {}

		@Override
		public Buyer findById(Long id) {
				return null;
		}

		@Override
		public void update(Buyer buyer) {

		}
}
