package com.bootcamp.socialmeligrupo5.service;

import com.bootcamp.socialmeligrupo5.dto.BuyerFollowingResponseDTO;
import com.bootcamp.socialmeligrupo5.dto.UserResponseDTO;
import com.bootcamp.socialmeligrupo5.entity.Buyer;
import com.bootcamp.socialmeligrupo5.entity.Seller;
import com.bootcamp.socialmeligrupo5.exception.BadRequestException;
import com.bootcamp.socialmeligrupo5.exception.NotFoundException;
import com.bootcamp.socialmeligrupo5.repository.BuyerRepository;
import com.bootcamp.socialmeligrupo5.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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

    public BuyerFollowingResponseDTO buyerFollowing(Long userId) {
        Buyer buyer = findBuyer(userId);

        List<UserResponseDTO> following =
                buyer.getFollowing().stream().map(b -> new UserResponseDTO(b.getId(), b.getName()))
                        .toList();

        return new BuyerFollowingResponseDTO(buyer.getId(), buyer.getName(), following);
    }


    public BuyerFollowingResponseDTO buyerFollowingWithOrder(Long userId, String order) {

        if (order.isBlank()) {
            throw new BadRequestException("Necessário informar o tipo de ordenação desejada!");
        }

        Buyer buyer = findBuyer(userId);
        List<UserResponseDTO> orderedFollowing =
                buyer.getFollowing().stream().map(f -> new UserResponseDTO(f.getId(), f.getName()))
                        .toList();

        if (order.equalsIgnoreCase("name_asc")) {
            orderedFollowing = orderedFollowing.stream()
                    .sorted(Comparator.comparing(UserResponseDTO::userName)).toList();
        } else if (order.equalsIgnoreCase("name_desc")) {
            orderedFollowing = orderedFollowing.stream()
                    .sorted(Comparator.comparing(UserResponseDTO::userName).reversed()).toList();
        } else {
            throw new BadRequestException("O tipo da ordenção informada não é permitida!");
        }

        return new BuyerFollowingResponseDTO(buyer.getId(), buyer.getName(), orderedFollowing);
    }

    private Buyer findBuyer(Long userId) {
        Buyer buyer = buyerRepository.findById(userId);
        if (buyer == null) {
            throw new NotFoundException("O comprador enviado não foi localizado!");
        }
        return buyer;
    }

}
