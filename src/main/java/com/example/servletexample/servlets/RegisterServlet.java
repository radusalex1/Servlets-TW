package com.example.servletexample.servlets;

import com.example.servletexample.model.User;
import com.example.servletexample.runTimeRepository.Users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register/*")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DO GET REGISTER");
        getServletContext().getRequestDispatcher("/jsp/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        System.out.println("REGISTER " + email);
        User user = new User(email);

        HttpSession session = request.getSession();
        session.setAttribute("currentUser", user);
        if(Users.INSTANCE.ifExists(user)==false)
        {
            Users.INSTANCE.registerUser(user);
            getServletContext().getRequestDispatcher("/login").forward(request, response);
        }
        else
        {
            getServletContext().getRequestDispatcher("/register").forward(request, response);
        }
    }
}
