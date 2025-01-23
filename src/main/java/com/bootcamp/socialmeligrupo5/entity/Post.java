package com.bootcamp.socialmeligrupo5.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Post {
    private Long id;
    @JsonFormat(pattern = "dd-MM-yyyy", locale = "pt-BR")
    private LocalDate date;
    private Integer category;
    private Long sellerId;
    private Product product;
    private Double price;
    private Double discount;
    private Boolean hasPromo;

    public Post() {
    }

    public Post(
        Long id, LocalDate date, Integer category, Long sellerId, Product product, Double price,
        Double discount, Boolean hasPromo
    ) {
        this.id = id;
        this.date = date;
        this.category = category;
        this.sellerId = sellerId;
        this.product = product;
        this.price = price;
        this.discount = discount;
        this.hasPromo = hasPromo;
    }

    public Post(
        Long id, LocalDate date, Integer category, Long sellerId, Product product,
        Double price
    ) {
        this.id = id;
        this.date = date;
        this.category = category;
        this.sellerId = sellerId;
        this.product = product;
        this.price = price;
        this.discount = 0.0;
        this.hasPromo = false;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Boolean getHasPromo() {
        return hasPromo;
    }

    public void setHasPromo(Boolean hasPromo) {
        this.hasPromo = hasPromo;
    }
}
