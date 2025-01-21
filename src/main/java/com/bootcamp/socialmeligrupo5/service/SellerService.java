package com.bootcamp.socialmeligrupo5.service;

import com.bootcamp.socialmeligrupo5.dto.FollowersCountResponseDTO;
import com.bootcamp.socialmeligrupo5.dto.PromoProductsCountResponseDTO;
import com.bootcamp.socialmeligrupo5.dto.SellerFollowersResponseDTO;
import com.bootcamp.socialmeligrupo5.dto.UserResponseDTO;
import com.bootcamp.socialmeligrupo5.entity.Buyer;
import com.bootcamp.socialmeligrupo5.entity.Post;
import com.bootcamp.socialmeligrupo5.entity.Seller;
import com.bootcamp.socialmeligrupo5.exception.BadRequestException;
import com.bootcamp.socialmeligrupo5.exception.NotFoundException;
import com.bootcamp.socialmeligrupo5.repository.PostRepository;
import com.bootcamp.socialmeligrupo5.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;
    private final PostRepository postRepository;

    public SellerService(SellerRepository sellerRepository, PostRepository postRepository) {
        this.sellerRepository = sellerRepository;
        this.postRepository = postRepository;
    }

    public FollowersCountResponseDTO followersCount(Long userId) {
        Seller seller = findSeller(userId);
        Set<Buyer> sellerFollowers = seller.getFollowers();
        return new FollowersCountResponseDTO(
                seller.getId(), seller.getName(), sellerFollowers.size());
    }

    public SellerFollowersResponseDTO listSellerFollowers(Long sellerId) {
        Seller seller = findSeller(sellerId);

        List<UserResponseDTO> followers =
                seller.getFollowers().stream().map(f -> new UserResponseDTO(f.getId(), f.getName()))
                        .toList();

        return new SellerFollowersResponseDTO(seller.getId(), seller.getName(), followers);
    }

    public SellerFollowersResponseDTO listFollowersWithOrder(Long userId, String order) {

        if (order.isBlank()) {
            throw new BadRequestException("Necessário informar a ordenção desejada!");
        }

        Seller seller = findSeller(userId);
        List<UserResponseDTO> orderedFollowers =
                seller.getFollowers().stream().map(f -> new UserResponseDTO(f.getId(), f.getName()))
                        .toList();

        if (order.equalsIgnoreCase("name_asc")) {
            orderedFollowers = orderedFollowers.stream()
                    .sorted(Comparator.comparing(UserResponseDTO::userName)).toList();
        } else if (order.equalsIgnoreCase("name_desc")) {
            orderedFollowers = orderedFollowers.stream()
                    .sorted(Comparator.comparing(UserResponseDTO::userName).reversed()).toList();
        } else {
            throw new BadRequestException("A ordenção informada não é permitida!");
        }

        return new SellerFollowersResponseDTO(seller.getId(), seller.getName(), orderedFollowers);
    }

    public PromoProductsCountResponseDTO countSellerPromoProducts(Long sellerId) {
        Seller seller = findSeller(sellerId);
        List<Post> posts = postRepository.findBySellerId(sellerId);
        Long count = posts.stream().filter(Post::getHasPromo).count();
        int promoProductsCount = Integer.parseInt(count.toString());
        return new PromoProductsCountResponseDTO(sellerId, seller.getName(), promoProductsCount);
    }

    private Seller findSeller(Long userId) {
        Seller seller = sellerRepository.findById(userId);
        if (seller == null) {
            throw new NotFoundException("O vendedor enviado não foi localizado!");
        }
        return seller;
    }
}
