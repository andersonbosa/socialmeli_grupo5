package com.bootcamp.socialmeligrupo5.entity;

import java.util.HashSet;
import java.util.Set;

public class Buyer extends User {
    private Set<Seller> following = new HashSet<>();

    public Buyer() {
    }

    public Buyer(Long id, String name) {
        super(id, name);
    }

    public Buyer(Long id, String name, Set<Seller> following) {
        super(id, name);
        this.following = following;
    }

    public Set<Seller> getFollowing() {
        return following;
    }

    public void follow(Seller seller) {
        this.following.add(seller);
    }

    public void unfollow(Seller seller) {
        this.following.remove(seller);
    }

}
