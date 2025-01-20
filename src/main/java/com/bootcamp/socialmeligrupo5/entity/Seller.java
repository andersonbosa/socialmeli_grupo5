package com.bootcamp.socialmeligrupo5.entity;

import java.util.HashSet;
import java.util.Set;

public class Seller extends User {
    private Set<Buyer> followers = new HashSet<>();

    public Seller() {

    }

    public Seller(Long id, String name, Set<Buyer> followers) {
        super(id, name);
        this.followers = followers;
    }

    public Seller(Long id, String name) {
        super(id, name);
    }

    public Set<Buyer> getFollowers() {
        return followers;
    }

    public void addFollower(Buyer buyer) {
        this.followers.add(buyer);
    }

}
