package com.quizapp.servlet;
 
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quizapp.dao.UserDAO;
import com.quizapp.model.User;
 
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
 
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.registerUser(user);
            response.sendRedirect("userlogin.jsp?success=Account created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp?error=Registration failed");
        }
    }
}