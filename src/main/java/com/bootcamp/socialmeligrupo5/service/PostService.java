package com.bootcamp.socialmeligrupo5.service;

import com.bootcamp.socialmeligrupo5.dto.PostDto;
import com.bootcamp.socialmeligrupo5.dto.ProductDto;
import com.bootcamp.socialmeligrupo5.entity.Post;
import com.bootcamp.socialmeligrupo5.entity.Product;
import com.bootcamp.socialmeligrupo5.exception.NotFoundException;
import com.bootcamp.socialmeligrupo5.repository.PostRepository;
import com.bootcamp.socialmeligrupo5.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final SellerRepository sellerRepository;
    private Long id = 1L;

    public PostService(PostRepository postRepository, SellerRepository sellerRepository) {
        this.postRepository = postRepository;
        this.sellerRepository = sellerRepository;
    }

    public void registerNewPost(PostDto postDto) {
        Post post = convertPostDtoToPost(postDto);
        if (post.getId() == null || post.getCategory() == null || post.getPrice() == null || post.getSellerId() == null || post.getDate() == null || post.getProduct() == null) {
            throw new NotFoundException("A publicação possui atributos incorretos.");
        }

        if (sellerRepository.findById(post.getSellerId()) == null) {
            throw new NotFoundException("Não existe um vendedor com o identificador fornecido.");
        }
        this.postRepository.create(post);
        System.out.println(post);
    }

    private Post convertPostDtoToPost(PostDto p) {
        Product product = convertProductDtoToProduct(p.product());
        return new Post(
                id++,
                LocalDate.parse(p.date(), DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                p.category(),
                p.user_id(),
                product,
                p.price()
        );

    }

    private Product convertProductDtoToProduct(ProductDto prodDto) {
        return new Product(
                prodDto.brand(),
                prodDto.color(),
                prodDto.product_id(),
                prodDto.product_name(),
                prodDto.notes()
        );
    }

    private PostDto convertPostToPostDto(Post post) {
        ProductDto productDto = convertProductToProductDto(post.getProduct());
        return new PostDto(
                post.getSellerId(),
                post.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                productDto,
                post.getCategory(),
                post.getPrice()
        );
    }

    private ProductDto convertProductToProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getType(),
                product.getBrand(),
                product.getColor(),
                product.getNotes()
        );
    }
}
