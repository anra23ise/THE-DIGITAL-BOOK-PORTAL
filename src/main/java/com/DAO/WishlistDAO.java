package com.DAO;

import java.util.List;

import com.entity.Wishlist;

public interface WishlistDAO {
    public boolean addToWishlist(int userId, int bookId);
    public List<Wishlist> getWishlistByUser(int userId);
    public boolean removeFromWishlist(int userId, int bookId);
}
