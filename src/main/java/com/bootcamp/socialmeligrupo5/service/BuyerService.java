package com.bootcamp.socialmeligrupo5.service;

import com.bootcamp.socialmeligrupo5.dto.BuyerFollowingResponseDTO;
import com.bootcamp.socialmeligrupo5.dto.UserResponseDTO;
import com.bootcamp.socialmeligrupo5.entity.Buyer;
import com.bootcamp.socialmeligrupo5.entity.Seller;
import com.bootcamp.socialmeligrupo5.exception.NotFoundException;
import com.bootcamp.socialmeligrupo5.repository.BuyerRepository;
import com.bootcamp.socialmeligrupo5.util.UserUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerService {

    private final BuyerRepository buyerRepository;
    private final SellerService sellerService;

    public BuyerService(BuyerRepository buyerRepository, SellerService sellerService) {
        this.buyerRepository = buyerRepository;
        this.sellerService = sellerService;
    }

    public void followSeller(Long userId, Long userIdToFollow) {
        Buyer buyer = findBuyer(userId);
        Seller seller = sellerService.findSeller(userIdToFollow);

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

    public void unfollowSeller(Long userId, Long userIdToUnfollow) {
        Buyer buyer = findBuyer(userId);
        Seller seller = sellerService.findSeller(userIdToUnfollow);
        isFollower(buyer, seller);
        buyer.unfollow(seller);
        seller.removeFollower(buyer);
    }

    private void isFollower(Buyer buyer, Seller seller) {
        seller.getFollowers().stream()
            .filter(f -> f.getId().equals(buyer.getId()))
            .findFirst()
            .orElseThrow(() -> new NotFoundException("O comprador enviado não é um seguidor do vendedor enviado!"));
    }

}
