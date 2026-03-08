package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Wishlist;

public class WishlistDAOImpl implements WishlistDAO {

    private Connection conn;

    public WishlistDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean addToWishlist(int userId, int bookId) {
        boolean added = false;
        try {
            // Check if the entry already exists
            String checkSql = "SELECT COUNT(*) FROM wishlist WHERE user_id = ? AND book_id = ?";
            PreparedStatement checkPs = conn.prepareStatement(checkSql);
            checkPs.setInt(1, userId);
            checkPs.setInt(2, bookId);
            ResultSet rs = checkPs.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Already in wishlist!");
                return false; // Entry exists, no need to insert
            }
    
            // Insert into wishlist
            String sql = "INSERT INTO wishlist(user_id, book_id) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, bookId);
            added = ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return added;
    }

    @Override
    public List<Wishlist> getWishlistByUser(int userId) {
        List<Wishlist> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM wishlist WHERE user_id=?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            BookDAOImpl bookDAO = new BookDAOImpl(conn);

            while (rs.next()) {
                Wishlist w = new Wishlist();
                w.setId(rs.getInt("id"));
                w.setUserId(rs.getInt("user_id"));
                w.setBookId(rs.getInt("book_id"));
                w.setBook(bookDAO.getBookById(rs.getInt("book_id")));
                list.add(w);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean removeFromWishlist(int userId, int bookId) {
        boolean removed = false;
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM wishlist WHERE user_id=? AND book_id=?");
            ps.setInt(1, userId);
            ps.setInt(2, bookId);
            removed = ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return removed;
    }
}
