package com.bootcamp.socialmeligrupo5.service;

import com.bootcamp.socialmeligrupo5.dto.SellerFollowersResponseDTO;
import com.bootcamp.socialmeligrupo5.dto.UserResponseDTO;
import com.bootcamp.socialmeligrupo5.entity.Seller;
import com.bootcamp.socialmeligrupo5.exception.NotFoundException;
import com.bootcamp.socialmeligrupo5.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

  private final SellerRepository repository;

  public SellerService(SellerRepository repository) {
    this.repository = repository;
  }

  public SellerFollowersResponseDTO listSellerFollowers(Long sellerId) {
    Seller seller = repository.findById(sellerId);
    if (seller == null) {
      throw new NotFoundException("Seller not found");
    }

    List<UserResponseDTO> followers = seller.getFollowers().stream().map(f -> new UserResponseDTO(f.getId(), f.getName())).toList();

    return new SellerFollowersResponseDTO(seller.getId(), seller.getName(), followers);
  }
}
