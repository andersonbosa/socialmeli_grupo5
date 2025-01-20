package com.bootcamp.socialmeligrupo5.service;

import com.bootcamp.socialmeligrupo5.repository.SellerRepository;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

  private final SellerRepository repository;

  public SellerService(SellerRepository repository) {
    this.repository = repository;
  }


}
