package org.jazzteam.martynchyk.servlets;

import com.jazzteam.martynchyk.Triangle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "TriangleServlet", urlPatterns = "/triangle-area")
public class TriangleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] inputString = request.getParameterValues("input");
        double[] input;
        try {
            input = Arrays.stream(inputString)
                    .mapToDouble(Double::parseDouble).toArray();
        } catch (NumberFormatException e) {
            request.setAttribute("area", -1.0);
            request.getRequestDispatcher("/triangle.jsp").forward(request, response);
            return;
        }
        Triangle triangle = new Triangle(input);
        double area = triangle.area();
        if (area == -1) {
            request.setAttribute("area", area);
            request.getRequestDispatcher("/triangle.jsp").forward(request, response);
        } else {
            request.setAttribute("perimeter", triangle.perimeter());
            request.setAttribute("area", area);
            request.getRequestDispatcher("/triangle.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
