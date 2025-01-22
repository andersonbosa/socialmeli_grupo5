package com.bootcamp.socialmeligrupo5.service;

import com.bootcamp.socialmeligrupo5.dto.*;
import com.bootcamp.socialmeligrupo5.entity.Post;
import com.bootcamp.socialmeligrupo5.entity.Product;
import com.bootcamp.socialmeligrupo5.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final SellerService sellerService;
    private final BuyerService buyerService;

    public PostService(PostRepository postRepository, SellerService sellerService, BuyerService buyerService){
        this.postRepository = postRepository;
        this.sellerService = sellerService;
        this.buyerService = buyerService;
    }

    public void registerNewPost(CreatePostRequestDTO postDto) {
        Post post = convertPostDtoToPost(postDto);
        sellerService.findSeller(post.getSellerId());
        this.postRepository.create(post);
    }

    public List<PostDTO> findFollowedSellersLastTwoWeeksPosts(Long userId) {
        LocalDate twoWeeksAgo = LocalDate.now().minusWeeks(2);
        LocalDate today = LocalDate.now();

        BuyerFollowingResponseDTO buyerFollowing = buyerService.buyerFollowing(userId);
        List<Long> sellerIds = buyerFollowing.following().stream().map(UserResponseDTO::userId).toList();

        List<Post> posts = postRepository.findBySellerIdBetweenDates(sellerIds, twoWeeksAgo, today);

        return posts.stream().map(this::convertPostToPostDto).toList();
    }

    private Post convertPostDtoToPost(CreatePostRequestDTO p) {
        Product product = convertProductDtoToProduct(p.product());
        return new Post(
                (long) postRepository.findAll().size(),
                LocalDate.parse(p.date(), DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                p.category(),
                p.userId(),
                product,
                p.price()
        );

    }

    private PostDTO convertPostToPostDto(Post post) {
        return new PostDTO(post.getSellerId(), post.getId(), post.getDate(), convertProductToProductDto(post.getProduct()), post.getCategory(), post.getPrice());
    }

    private Product convertProductDtoToProduct(ProductDTO prodDto) {
        return new Product(
                prodDto.brand(),
                prodDto.color(),
                prodDto.productId(),
                prodDto.productName(),
                prodDto.notes(),
                prodDto.type()
        );
    }

    private ProductDTO convertProductToProductDto(Product product) {
        return new ProductDTO(
            product.getId(),
            product.getName(),
            product.getType(),
            product.getBrand(),
            product.getColor(),
            product.getNotes()
        );
    }
}
