package com.bootcamp.socialmeligrupo5.service;

import com.bootcamp.socialmeligrupo5.dto.FollowersCountResponseDTO;
import com.bootcamp.socialmeligrupo5.dto.PromoProductsCountResponseDTO;
import com.bootcamp.socialmeligrupo5.dto.SellerFollowersResponseDTO;
import com.bootcamp.socialmeligrupo5.dto.UserResponseDTO;
import com.bootcamp.socialmeligrupo5.entity.Buyer;
import com.bootcamp.socialmeligrupo5.entity.Post;
import com.bootcamp.socialmeligrupo5.entity.Seller;
import com.bootcamp.socialmeligrupo5.exception.NotFoundException;
import com.bootcamp.socialmeligrupo5.repository.PostRepository;
import com.bootcamp.socialmeligrupo5.repository.SellerRepository;
import org.springframework.stereotype.Service;

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
        return new FollowersCountResponseDTO(seller.getId(), seller.getName(), sellerFollowers.size());
    }

    public SellerFollowersResponseDTO listSellerFollowers(Long sellerId) {
        Seller seller = findSeller(sellerId);

        List<UserResponseDTO> followers = seller.getFollowers().stream().map(f -> new UserResponseDTO(f.getId(), f.getName())).toList();

        return new SellerFollowersResponseDTO(seller.getId(), seller.getName(), followers);
    }

    public PromoProductsCountResponseDTO countSellerPromoProducts(Long sellerId) {
        Seller seller = findSeller(sellerId);
        List<Post> posts = postRepository.findBySellerId(sellerId);
        Long count = posts.stream().filter(Post::getHasPromo).count();
        int promoProductsCount = Integer.parseInt(count.toString());
        return new PromoProductsCountResponseDTO(sellerId, seller.getName(), promoProductsCount);
    }

    public Seller findSeller(Long userId) {
        Seller seller = sellerRepository.findById(userId);
        if (seller == null) {
            throw new NotFoundException("O vendedor enviado não foi localizado!");
        }
        return seller;
    }
}
