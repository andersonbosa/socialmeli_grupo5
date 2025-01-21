package com.bootcamp.socialmeligrupo5.service;

import com.bootcamp.socialmeligrupo5.dto.PostDTO;
import com.bootcamp.socialmeligrupo5.dto.ProductDTO;
import com.bootcamp.socialmeligrupo5.entity.Post;
import com.bootcamp.socialmeligrupo5.entity.Product;
import com.bootcamp.socialmeligrupo5.exception.BadRequestException;
import com.bootcamp.socialmeligrupo5.exception.NotFoundException;
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

    public void registerNewPost(PostDTO postDto) {
        Post post = convertPostDtoToPost(postDto);
        sellerService.findSeller(post.getSellerId());
        this.postRepository.create(post);
    }

    private Post convertPostDtoToPost(PostDTO p) {
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
