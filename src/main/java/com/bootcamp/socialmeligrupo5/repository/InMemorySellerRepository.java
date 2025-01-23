package com.bootcamp.socialmeligrupo5.repository;

import com.bootcamp.socialmeligrupo5.entity.Seller;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemorySellerRepository implements SellerRepository {
	public List<Seller> sellers = new ArrayList<>();

	public InMemorySellerRepository() {
		loadDataBase();
	}

	@Override
	public Seller findById(Long id) {
		return sellers.stream().filter(seller -> seller.getId().equals(id)).findFirst()
				.orElse(null);
	}

	@Override
	public void update(Seller seller) {

	}

	private void loadDataBase() {

		try {
			File file;
			ObjectMapper objectMapper = new ObjectMapper();
			List<Seller> sellersJson;

			file = ResourceUtils.getFile("src/main/resources/users_seller_50.json");
			sellersJson = objectMapper.readValue(
					file, new TypeReference<List<Seller>>() {
					}
			);

			sellers = sellersJson;
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}


	}
}
