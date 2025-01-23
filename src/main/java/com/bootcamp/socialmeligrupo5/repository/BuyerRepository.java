package com.bootcamp.socialmeligrupo5.repository;

import com.bootcamp.socialmeligrupo5.entity.Buyer;

public interface BuyerRepository {
	void update(Buyer buyer);

	Buyer findById(Long id);
}
