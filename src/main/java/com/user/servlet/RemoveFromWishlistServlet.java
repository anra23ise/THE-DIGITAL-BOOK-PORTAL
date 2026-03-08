package com.user.servlet;

import java.io.IOException;

import com.DAO.WishlistDAOImpl;
import com.DB.DBConnect;
import com.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/remove_from_wishlist")
public class RemoveFromWishlistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the book ID from the request
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        // Get the current user from the session
        User user = (User) request.getSession().getAttribute("userobj");

        // Check if user is logged in
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Create the WishlistDAO instance and remove the book from the wishlist
        WishlistDAOImpl dao = new WishlistDAOImpl(DBConnect.getConn());
        boolean removed = dao.removeFromWishlist(user.getId(), bookId);

        if (removed) {
            // If successful, redirect to the wishlist page
            //response.sendRedirect("wishlist.jsp");
            System.out.println("Removed from wishlist.");
            request.setAttribute("message", "Removed from wishlist.");
            request.getRequestDispatcher("wishlist.jsp").forward(request, response);
        } else {
            // Handle failure (optional)
            System.out.println("Failed to remove from wishlist.");
            request.setAttribute("message", "Failed to remove from wishlist.");
            request.getRequestDispatcher("wishlist.jsp").forward(request, response);
        }
    }
}
