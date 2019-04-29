package org.jazzteam.martynchyk.servlets;

import com.jazzteam.martynchyk.facade.library.Encryptor;
import com.jazzteam.martynchyk.facade.library.Key;
import com.jazzteam.martynchyk.facade.library.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EncryptionServlet", urlPatterns = "/encryption-servlet")
public class EncryptionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("ACTION");
        String message;
        Key key = new Key();
        if (action.equals("ENCRYPTION")) {
            message = request.getParameter("input");
            key.generateKey();
            Message messageObj = new Message(Encryptor.encrypt(key.getKey(), message));
            request.setAttribute("key", key.getKey());
            request.setAttribute("message", messageObj.getMassage());
            request.getRequestDispatcher("/encryption.jsp").forward(request, response);
        } else {
            message = request.getParameter("inputEncrypted");
            int keyInt = Integer.parseInt(request.getParameter("key"));
            request.setAttribute("key", null);
            request.setAttribute("messageEncrypted", Encryptor.decrypt(keyInt, message));
            request.getRequestDispatcher("/encryption.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
