package com.example.servletexample.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "/home", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GET HOME");

        String email = (String)request.getParameter("email");
        String token = (String)request.getParameter("token");

        int money = Integer.parseInt((String)request.getParameter("moneyAmount"));
        money =  money * 10;

        request.setAttribute("email",email);
        request.setAttribute("token",token);
        request.setAttribute("moneyAmount",String.valueOf(money));

        getServletContext().getRequestDispatcher("/jsp/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
