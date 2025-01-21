package com.bootcamp.socialmeligrupo5.service;

import com.bootcamp.socialmeligrupo5.dto.PostDto;
import com.bootcamp.socialmeligrupo5.dto.ProductDto;
import com.bootcamp.socialmeligrupo5.entity.Post;
import com.bootcamp.socialmeligrupo5.entity.Product;
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

    public void registerNewPost(PostDto postDto) {
        Post post = convertPostDtoToPost(postDto);
        if (post.getId() == null || post.getCategory() == null || post.getPrice() == null || post.getSellerId() == null || post.getDate() == null || post.getProduct() == null) {
            throw new NotFoundException("A publicação possui atributos incorretos.");
        }

        if (sellerService.findSeller(post.getSellerId()) == null) {
            throw new NotFoundException("Não existe um vendedor com o identificador fornecido.");
        }
        this.postRepository.create(post);
    }

    private Post convertPostDtoToPost(PostDto p) {
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

    private Product convertProductDtoToProduct(ProductDto prodDto) {
        return new Product(
                prodDto.brand(),
                prodDto.color(),
                prodDto.product_id(),
                prodDto.product_name(),
                prodDto.notes()
        );
    }
}
