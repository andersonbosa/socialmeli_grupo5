package com.bootcamp.socialmeligrupo5.repository;

import com.bootcamp.socialmeligrupo5.entity.Buyer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryBuyerRepository implements BuyerRepository {
    public List<Buyer> buyers = new ArrayList<>();

    public InMemoryBuyerRepository() {
        loadDataBase();
    }

    @Override
    public Buyer findById(Long id) {
        return this.buyers.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void update(Buyer buyer) {

    }

    private void loadDataBase() {

        try {
            File file;
            ObjectMapper objectMapper = new ObjectMapper();
            List<Buyer> buyersJson;

            file = ResourceUtils.getFile("src/main/resources/users_buyer_50.json");
            buyersJson = objectMapper.readValue(
                file, new TypeReference<List<Buyer>>() {
                }
            );

            buyers = buyersJson;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


    }

}
