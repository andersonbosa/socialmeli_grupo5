package com.bootcamp.socialmeligrupo5.repository;

import com.bootcamp.socialmeligrupo5.entity.Buyer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryBuyerRepository {
		private List<Buyer> buyers = new ArrayList<>();

		public InMemoryBuyerRepository() {}
}
