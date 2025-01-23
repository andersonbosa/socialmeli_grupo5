package com.bootcamp.socialmeligrupo5.repository;

import com.bootcamp.socialmeligrupo5.entity.Seller;

public interface SellerRepository {
    void update(Seller seller);

    Seller findById(Long id);
}
