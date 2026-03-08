package com.user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.WishlistDAOImpl;
import com.DB.DBConnect;
import com.entity.User;
import com.entity.Wishlist;

@WebServlet("/wishlist")
public class WishlistServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if user is logged in
        User user = (User) request.getSession().getAttribute("userobj");
        if (user != null) {
            // Fetch the user's wishlist
            WishlistDAOImpl wishlistDAO = new WishlistDAOImpl(DBConnect.getConn());
            List<Wishlist> wishlist = wishlistDAO.getWishlistByUser(user.getId());
            request.setAttribute("wishlist", wishlist);
            request.getRequestDispatcher("wishlist.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Add a book to wishlist
        User user = (User) request.getSession().getAttribute("userobj");
        if (user != null) {
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            WishlistDAOImpl wishlistDAO = new WishlistDAOImpl(DBConnect.getConn());
            boolean success = wishlistDAO.addToWishlist(user.getId(), bookId);
            if (success) {
                response.sendRedirect("wishlist");
            } else {
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
    
    @WebServlet("/remove_from_wishlist")
    public class RemoveFromWishlistServlet extends HttpServlet {

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Remove a book from wishlist
            User user = (User) request.getSession().getAttribute("userobj");
            if (user != null) {
                int bookId = Integer.parseInt(request.getParameter("bookId"));
                WishlistDAOImpl wishlistDAO = new WishlistDAOImpl(DBConnect.getConn());
                boolean success = wishlistDAO.removeFromWishlist(user.getId(), bookId);
                if (success) {
                    response.sendRedirect("wishlist");
                } else {
                    response.sendRedirect("error.jsp");
                }
            } else {
                response.sendRedirect("login.jsp");
            }
        }
    }
}
