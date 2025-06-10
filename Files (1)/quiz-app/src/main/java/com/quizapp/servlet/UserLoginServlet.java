package com.quizapp.servlet;
 
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quizapp.dao.UserDAO;
import com.quizapp.model.User;
 
@WebServlet("/userlogin")
public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
 
        UserDAO userDAO = new UserDAO();
        try {
            User user = userDAO.loginUser(email, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("quizList.jsp");
            } else {
                response.sendRedirect("userlogin.jsp?error=Invalid credentials");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("userlogin.jsp?error=Server error");
        }
    }
}
