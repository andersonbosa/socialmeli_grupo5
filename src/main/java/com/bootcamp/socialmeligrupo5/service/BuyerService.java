package com.bootcamp.socialmeligrupo5.service;

import com.bootcamp.socialmeligrupo5.dto.BuyerFollowingResponseDTO;
import com.bootcamp.socialmeligrupo5.dto.UserResponseDTO;
import com.bootcamp.socialmeligrupo5.entity.Buyer;
import com.bootcamp.socialmeligrupo5.entity.Seller;
import com.bootcamp.socialmeligrupo5.exception.NotFoundException;
import com.bootcamp.socialmeligrupo5.repository.BuyerRepository;
import com.bootcamp.socialmeligrupo5.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerService {

    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;

    public BuyerService(BuyerRepository buyerRepository, SellerRepository sellerRepository) {
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
    }

    public void followSeller(Long userId, Long userIdToFollow) {
        Buyer buyer = buyerRepository.findById(userId);
        if (buyer == null) {
            throw new NotFoundException("O comprador enviado nao foi localizado!");
        }
        Seller seller = sellerRepository.findById(userIdToFollow);
        if (seller == null) {
            throw new NotFoundException("O vendedor enviado nao foi localizado!");
        }

        buyer.follow(seller);
        seller.addFollower(buyer);

        System.out.println("Seller - " + seller);
        System.out.println("Buyer" + buyer);
    }

    public BuyerFollowingResponseDTO buyerFollowing(Long userId) {
        Buyer buyer = buyerRepository.findById(userId);

        List<UserResponseDTO> following = buyer.getFollowing().stream().map(b -> new UserResponseDTO(b.getId(), b.getName())).toList();

        return new BuyerFollowingResponseDTO(buyer.getId(), buyer.getName(), following);
    }

}
