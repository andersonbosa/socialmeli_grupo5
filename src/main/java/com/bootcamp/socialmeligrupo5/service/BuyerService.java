package com.bootcamp.socialmeligrupo5.service;

import com.bootcamp.socialmeligrupo5.dto.BuyerFollowingResponseDTO;
import com.bootcamp.socialmeligrupo5.dto.UserResponseDTO;
import com.bootcamp.socialmeligrupo5.entity.Buyer;
import com.bootcamp.socialmeligrupo5.entity.Seller;
import com.bootcamp.socialmeligrupo5.exception.NotFoundException;
import com.bootcamp.socialmeligrupo5.repository.BuyerRepository;
import com.bootcamp.socialmeligrupo5.repository.SellerRepository;
import com.bootcamp.socialmeligrupo5.util.UserUtil;
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
        Buyer buyer = findBuyer(userId);
        Seller seller = sellerRepository.findById(userIdToFollow);
        if (seller == null) {
            throw new NotFoundException("O vendedor enviado nao foi localizado!");
        }

        buyer.follow(seller);
        seller.addFollower(buyer);
    }

    public BuyerFollowingResponseDTO buyerFollowing(Long userId, String order) {
        Buyer buyer = findBuyer(userId);

        List<UserResponseDTO> following =
                buyer.getFollowing().stream().map(b -> new UserResponseDTO(b.getId(), b.getName()))
                        .toList();

        if (order != null) {
            following = UserUtil.listUsersWithOrder(following, order);
        }

        return new BuyerFollowingResponseDTO(buyer.getId(), buyer.getName(), following);
    }


    private Buyer findBuyer(Long userId) {
        Buyer buyer = buyerRepository.findById(userId);
        if (buyer == null) {
            throw new NotFoundException("O comprador enviado não foi localizado!");
        }
        return buyer;
    }

}
