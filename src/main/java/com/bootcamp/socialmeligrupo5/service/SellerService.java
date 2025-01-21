package com.bootcamp.socialmeligrupo5.service;

import com.bootcamp.socialmeligrupo5.dto.FollowersCountResponseDTO;
import com.bootcamp.socialmeligrupo5.entity.Buyer;
import com.bootcamp.socialmeligrupo5.entity.Seller;
import com.bootcamp.socialmeligrupo5.exception.NotFoundException;
import com.bootcamp.socialmeligrupo5.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    private Seller findSeller(Long userId) {
        Seller seller = sellerRepository.findById(userId);
        if (seller == null) {
            throw new NotFoundException("O vendedor enviado não foi localizado!");
        }
        return seller;
    }

    public FollowersCountResponseDTO followersCount(Long userId) {
        Seller seller = findSeller(userId);
        Set<Buyer> sellerFollowers = seller.getFollowers();
        return new FollowersCountResponseDTO(seller.getId(), seller.getName(), sellerFollowers.size());
    }

}
