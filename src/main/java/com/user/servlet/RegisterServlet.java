package com.user.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.DAO.UserDAOImpl;
import com.DB.DBConnect;
import com.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(RegisterServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String phno = req.getParameter("phno");
            String password = req.getParameter("password");
            String confirmPassword = req.getParameter("confirmPassword");
            String check = req.getParameter("check");
            check = "true";
            HttpSession session = req.getSession();

            System.out.println("password: " + password + ", confirmPassword: " + confirmPassword+ ", email: " + email);

            // Confirm password match check (server-side)
            if (!password.equals(confirmPassword)) {
                session.setAttribute("failedMsg", "Passwords do not match.");
                resp.sendRedirect("register.jsp");
                return;
            }

            User us = new User();
            us.setName(name);
            us.setEmail(email);
            us.setPhno(phno);
            us.setPassword(password);

            if (check != null) {
                UserDAOImpl dao = new UserDAOImpl(DBConnect.getConn());

                boolean userExists = dao.checkUser(email);
                if (!userExists) {
                    boolean registered = dao.userRegister(us);

                    if (registered) {
                        session.setAttribute("succMsg", "Registration successful! Please login.");
                        resp.sendRedirect("login.jsp"); // ✅ REDIRECT TO LOGIN
                    } else {
                        session.setAttribute("failedMsg", "Something went wrong on the server.");
                        resp.sendRedirect("register.jsp");
                    }
                } else {
                    session.setAttribute("failedMsg", "User already exists. Try another email.");
                    resp.sendRedirect("register.jsp");
                }
            } else {
                session.setAttribute("failedMsg", "Please agree to Terms & Conditions.");
                resp.sendRedirect("register.jsp");
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred in RegisterServlet", e);
        }
    }
}
