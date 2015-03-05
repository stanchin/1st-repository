package com.tsystems.javaschool.servlets;

import com.tsystems.javaschool.entities.Client;
import com.tsystems.javaschool.persistence.Session;
import com.tsystems.javaschool.services.impl.ClientServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action){
            case "login":
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                ClientServiceImpl clientService = new ClientServiceImpl();
                Client client = null;
                try {
                    client = clientService.getClient(email, password);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(client != null){
                    Session session = Session.getInstance();
                    session.setOpened(true);
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("client", client);
                    httpSession.setAttribute("session", session);
                    if (client.getRole().getRole().equalsIgnoreCase("client")){
                        request.getRequestDispatcher("/client.jsp").forward(request, response);
                    } else
                        request.getRequestDispatcher("/admin.jsp").forward(request, response);
                }else{
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
                break;
            case "logout":
                try {
                    Session session = (Session) request.getSession().getAttribute("session");
                    session.setOpened(false);
                    request.getSession().setAttribute("session", session);
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                } catch (Exception e) {
                    response.sendError(300, "You're not logged in");
                }
                break;
        }
        // get request parameters for userID and password


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
