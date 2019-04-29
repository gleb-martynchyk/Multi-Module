package org.jazzteam.martynchyk;

import com.jazzteam.martynchyk.Triangle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet("/GetTriangle")
public class GetTriangle extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double[] input;
        String[] inputString = new String[6];
        for (int i = 0; i < inputString.length / 2; i++) {
            inputString[i * 2] = request.getParameter("x" + i);
            inputString[i * 2 + 1] = request.getParameter("y" + i);
        }

        response.setContentType("text/plain");
        PrintWriter writer = response.getWriter();

        try {
            input = Arrays.stream(inputString)
                    .mapToDouble(Double::parseDouble).toArray();
        } catch (NumberFormatException e) {
            writer.println("Not valid data");
            return;
        }
        Triangle triangle = new Triangle(input);
        double area = triangle.area();
        if (area == -1) {
            writer.println("Not valid data");
        } else {
            request.setAttribute("perimeter", triangle.perimeter());
            request.setAttribute("area", area);
            writer.println("Perimeter:" + triangle.perimeter() + "   " + "Area" + area);
        }
    }
}
