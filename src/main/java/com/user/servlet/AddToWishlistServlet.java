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
import jakarta.servlet.http.HttpSession;

@WebServlet("/add_to_wishlist")
public class AddToWishlistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userobj");

        // Check if the user is logged in
        if (user != null) {
            // Get the book ID from request parameters
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            int userId = user.getId();  // assuming User has a getId() method

            // Create WishlistDAO object
            WishlistDAOImpl wishlistDAO = new WishlistDAOImpl(DBConnect.getConn());

            // Add book to wishlist
            boolean isAdded = wishlistDAO.addToWishlist(userId, bookId);

            // Set the result of the operation as a request attribute
            if (isAdded) {
                session.setAttribute("wishmessage", "Book added to wishlist!");
                System.out.println("Book added to wishlist!!");
                //request.setAttribute("wishlistadded", "true");
            } else {
                session.setAttribute("wishmessage", "Entry Already Present in Wishlist!!!");
                System.out.println("Entry Already Present!");
            }

            // Forward to the all_recent_book.jsp with the result message
            request.getRequestDispatcher("all_recent_book.jsp").forward(request, response);
        } else {
            // If user is not logged in, redirect to login page
            response.sendRedirect("login.jsp");
        }
    }
}
