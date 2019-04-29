package org.jazzteam.martynchyk.servlets;

import org.jazzteam.martynchyk.UsersJDBC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "WelcomeServlet", urlPatterns = "/welcome")
public class WelcomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String errorMessage = null;

        if (UsersJDBC.isPasswordCorrect(login, password)) {
            request.getRequestDispatcher("/applications.jsp").forward(request, response);
        } else {
            errorMessage = "You are not the valid user...";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
