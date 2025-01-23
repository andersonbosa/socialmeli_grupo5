package com.bootcamp.socialmeligrupo5.service;

import com.bootcamp.socialmeligrupo5.dto.*;
import com.bootcamp.socialmeligrupo5.entity.Post;
import com.bootcamp.socialmeligrupo5.entity.Product;
import com.bootcamp.socialmeligrupo5.entity.Seller;
import com.bootcamp.socialmeligrupo5.exception.BadRequestException;
import com.bootcamp.socialmeligrupo5.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PostService {
    private final List<String> VALID_ORDERS = List.of("date_asc", "date_desc");
    private final PostRepository postRepository;
    private final SellerService sellerService;
    private final BuyerService buyerService;

    public PostService(
            PostRepository postRepository, SellerService sellerService, BuyerService buyerService) {
        this.postRepository = postRepository;
        this.sellerService = sellerService;
        this.buyerService = buyerService;
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

    public SellerPostsResponseDTO findFollowedSellersLastTwoWeeksPosts(Long userId, String order) {
        LocalDate twoWeeksAgo = LocalDate.now().minusWeeks(2);
        LocalDate today = LocalDate.now();

        BuyerFollowingResponseDTO buyerFollowing = buyerService.buyerFollowing(userId, null);
        List<Long> sellerIds =
                buyerFollowing.following().stream().map(UserResponseDTO::userId).toList();

        List<PostDTO> posts =
                postRepository.findBySellerIdBetweenDates(sellerIds, twoWeeksAgo, today).stream()
                        .map(this::convertPostToPostDto).toList();

        if (hasOrder(order)) {
            return new SellerPostsResponseDTO(userId, posts.reversed());
        }
        return new SellerPostsResponseDTO(userId, posts);
    }

    private boolean hasOrder(String order) {
        if (order == null) {
            return false;
        }

        if (!VALID_ORDERS.contains(order.toLowerCase())) {
            throw new BadRequestException("O tipo da ordenação informada não é permitida!");
        }
        return order.equalsIgnoreCase("date_asc");
    }

    public PromoProductsCountResponseDTO countSellerPromoProducts(Long sellerId) {
        Seller seller = sellerService.findSeller(sellerId);
        List<Post> posts = postRepository.findBySellerId(sellerId);
        long count = posts.stream().filter(Post::getHasPromo).count();
        return new PromoProductsCountResponseDTO(sellerId, seller.getName(), (int) count);
    }

    public PromoProductsListResponseDTO promoPostBySellerId(Long sellerId) {
        Seller seller = sellerService.findSeller(sellerId);

        List<PromoPostDTO> promoPosts = postRepository.findPromoPostBySellerId(sellerId).stream()
                .map(this::convertPostToPromoPostDto).toList();
        return new PromoProductsListResponseDTO(sellerId, seller.getName(), promoPosts);

    }

    private Post convertPostDtoToPost(CreatePostRequestDTO p) {
        Product product = convertProductDtoToProduct(p.product());
        return new Post(
                (long) postRepository.findAll().size(),
                LocalDate.parse(p.date(), DateTimeFormatter.ofPattern("dd-MM-yyyy")), p.category(),
                p.userId(), product, p.price()
        );
    }

    private Post convertPromoPostDtoToPost(CreatePromoPostRequestDTO dto) {
        Product product = convertProductDtoToProduct(dto.product());
        return new Post(
                (long) postRepository.findAll().size(),
                LocalDate.parse(dto.date(), DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                dto.category(), dto.userId(), product, dto.price(), dto.discount(), dto.hasPromo()
        );
    }

    private PostDTO convertPostToPostDto(Post post) {
        return new PostDTO(
                post.getSellerId(), post.getId(), post.getDate(),
                convertProductToProductDto(post.getProduct()), post.getCategory(), post.getPrice()
        );
    }

    private PromoPostDTO convertPostToPromoPostDto(Post post) {
        return new PromoPostDTO(
                post.getSellerId(), post.getId(), post.getDate(),
                convertProductToProductDto(post.getProduct()), post.getCategory(), post.getPrice(),
                post.getDiscount(), post.getHasPromo()
        );
    }

    private Product convertProductDtoToProduct(ProductDTO prodDto) {
        return new Product(
                prodDto.brand(), prodDto.color(), prodDto.productId(), prodDto.productName(),
                prodDto.notes(), prodDto.type()
        );
    }

    private ProductDTO convertProductToProductDto(Product product) {
        return new ProductDTO(
                product.getId(), product.getName(), product.getType(),
                product.getBrand(), product.getColor(), product.getNotes()
        );
    }
}
