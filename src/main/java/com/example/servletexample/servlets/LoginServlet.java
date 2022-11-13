package com.example.servletexample.servlets;

import com.example.servletexample.model.User;
import com.example.servletexample.runTimeRepository.Users;
import org.thymeleaf.util.ObjectUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@WebServlet(name = "IndexServlet", urlPatterns = {"/","/login/*"})
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Check if user is already connected, if not show login page */
        System.out.println("GET   LOGIN");
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        System.out.println("USER " + currentUser);

        System.out.println("ALL USERS");
        //Users.INSTANCE.getUsers().forEach(System.out::println);
        /* If not logged */
        if(Objects.isNull(currentUser)) {
            getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
        else {
            getServletContext().getRequestDispatcher("/home").forward(request, response);
        }
    }
    ///Cum chem metoda doGet din homeServlet din doPost care este in LoginServlet

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Check has the right credentials and use HomeServlet */

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        String email = request.getParameter("email");
        String token = request.getParameter("token");
        int moneyAmount = Integer.parseInt(request.getParameter("moneyAmount"));

        //Optional<User> userByEmailAndPassword = Users.INSTANCE.findUserByEmailAndPassword(email, password);
        if (Users.INSTANCE.hasValidCredentials(email,token)) {

            User user = Users.INSTANCE.getUserByToken(token);

            int moneyAfterDiscount=moneyAmount;

            if(moneyAmount>=500) {
                switch (user.getLevel()) {
                    case 1: {
                        moneyAfterDiscount = moneyAmount - 5;
                        break;
                    }
                    case 2: {
                        moneyAfterDiscount = moneyAmount - 10;
                        break;
                    }
                    case 3: {
                        moneyAfterDiscount = moneyAmount - 15;
                        break;
                    }
                    case 4: {
                        moneyAfterDiscount = moneyAmount - 20;
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }

            if(moneyAmount>=100)
            {
                Users.INSTANCE.addUserPoints(user.getToken());
            }

            user = Users.INSTANCE.getUserByToken(token);

            request.setAttribute("email", email);
            request.setAttribute("token", token);
            request.setAttribute("moneyAmount", String.valueOf(moneyAmount));
            request.setAttribute("moneyAfterDiscount", String.valueOf(moneyAfterDiscount));
            request.setAttribute("level", String.valueOf(user.getLevel()));
            request.setAttribute("points", String.valueOf(user.getPoints()));

            getServletContext().getRequestDispatcher("/jsp/home.jsp").forward(request, response);
        }
        else {
            /* Otherwise, reload the form  */
            getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }
}