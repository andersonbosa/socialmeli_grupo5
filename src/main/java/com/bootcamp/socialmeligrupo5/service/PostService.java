package com.bootcamp.socialmeligrupo5.service;

import com.bootcamp.socialmeligrupo5.dto.CreatePostRequestDTO;
import com.bootcamp.socialmeligrupo5.dto.CreatePromoPostRequestDTO;
import com.bootcamp.socialmeligrupo5.dto.ProductDTO;
import com.bootcamp.socialmeligrupo5.entity.Post;
import com.bootcamp.socialmeligrupo5.entity.Product;
import com.bootcamp.socialmeligrupo5.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final SellerService sellerService;

    public PostService(PostRepository postRepository, SellerService sellerService) {
        this.postRepository = postRepository;
        this.sellerService = sellerService;
    }

    public void registerNewPost(CreatePostRequestDTO postDto) {
        Post post = convertPostDtoToPost(postDto);
        sellerService.findSeller(post.getSellerId());
        this.postRepository.create(post);
    }

    public void registerNewPromoPost(CreatePromoPostRequestDTO postDto) {
        Post post = convertPromoPostDtoToPost(postDto);
        sellerService.findSeller(post.getSellerId());
        this.postRepository.create(post);
    }

    private Post convertPostDtoToPost(CreatePostRequestDTO p) {
        Product product = convertProductDtoToProduct(p.product());
        return new Post(
                (long) postRepository.findAll().size(),
                LocalDate.parse(p.date(), DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                p.category(),
                p.user_id(),
                product,
                p.price()
        );
    }

    private Post convertPromoPostDtoToPost(CreatePromoPostRequestDTO dto) {
        Product product = convertProductDtoToProduct(dto.product());
        return new Post(
                (long) postRepository.findAll().size(),
                LocalDate.parse(dto.date(), DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                dto.category(),
                dto.user_id(),
                product,
                dto.price(),
                dto.discount(),
                dto.has_promo()
        );
    }

    private Product convertProductDtoToProduct(ProductDTO prodDto) {
        return new Product(
                prodDto.brand(),
                prodDto.color(),
                prodDto.product_id(),
                prodDto.product_name(),
                prodDto.notes()
        );
    }
}
