package com.bootcamp.socialmeligrupo5.service;

import com.bootcamp.socialmeligrupo5.dto.PromoProductsCountResponseDTO;
import com.bootcamp.socialmeligrupo5.entity.Post;
import com.bootcamp.socialmeligrupo5.entity.Seller;
import com.bootcamp.socialmeligrupo5.exception.BadRequestException;
import com.bootcamp.socialmeligrupo5.repository.PostRepository;
import com.bootcamp.socialmeligrupo5.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;
    private final PostRepository postRepository;

    public SellerService(SellerRepository sellerRepository, PostRepository postRepository1) {
        this.sellerRepository = sellerRepository;
        this.postRepository = postRepository1;
    }


    public PromoProductsCountResponseDTO countSellerPromoProducts(Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId);
        if (seller == null) {
            throw new BadRequestException(String.format("Vendedor com id '%d' não encontrado.", sellerId));
        }

        List<Post> posts = postRepository.findBySellerId(sellerId);
        Long count = posts.stream().filter(Post::getHasPromo).count();
        int promoProductsCount = Integer.parseInt(count.toString());

        return new PromoProductsCountResponseDTO(sellerId, seller.getName(), promoProductsCount);
    }

}
