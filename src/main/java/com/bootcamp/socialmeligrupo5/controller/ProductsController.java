package com.bootcamp.socialmeligrupo5.controller;

import com.bootcamp.socialmeligrupo5.dto.PromoProductsCountResponseDTO;
import com.bootcamp.socialmeligrupo5.service.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final SellerService sellerService;

    public ProductsController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("/promo-post/count")
    public ResponseEntity<?> getPromoPostCount(@RequestParam(name = "user_id") Long userId) {
        PromoProductsCountResponseDTO dto = this.sellerService.countSellerPromoProducts(userId);
        return ResponseEntity.ok(dto);
    }

}
