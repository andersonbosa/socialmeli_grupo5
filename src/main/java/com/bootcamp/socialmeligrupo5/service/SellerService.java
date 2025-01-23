package com.bootcamp.socialmeligrupo5.service;

import com.bootcamp.socialmeligrupo5.dto.FollowersCountResponseDTO;
import com.bootcamp.socialmeligrupo5.dto.SellerFollowersResponseDTO;
import com.bootcamp.socialmeligrupo5.dto.UserResponseDTO;
import com.bootcamp.socialmeligrupo5.entity.Buyer;
import com.bootcamp.socialmeligrupo5.entity.Seller;
import com.bootcamp.socialmeligrupo5.exception.NotFoundException;
import com.bootcamp.socialmeligrupo5.repository.SellerRepository;
import com.bootcamp.socialmeligrupo5.util.UserUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SellerService {

	private final SellerRepository sellerRepository;

	public SellerService(SellerRepository sellerRepository) {
		this.sellerRepository = sellerRepository;
	}

	public FollowersCountResponseDTO followersCount(Long userId) {
		Seller seller = findSeller(userId);
		Set<Buyer> sellerFollowers = seller.getFollowers();
		return new FollowersCountResponseDTO(
				seller.getId(), seller.getName(), sellerFollowers.size());
	}

	public SellerFollowersResponseDTO listSellerFollowers(Long sellerId, String order) {
		Seller seller = findSeller(sellerId);

		List<UserResponseDTO> followers =
				seller.getFollowers().stream().map(f -> new UserResponseDTO(f.getId(), f.getName()))
						.toList();

		if (order != null) {
			followers = UserUtil.listUsersWithOrder(followers, order);
		}

		return new SellerFollowersResponseDTO(seller.getId(), seller.getName(), followers);
	}

	public Seller findSeller(Long userId) {
		Seller seller = sellerRepository.findById(userId);
		if (seller == null) {
			throw new NotFoundException("O vendedor enviado não foi localizado!");
		}
		return seller;
	}
}
