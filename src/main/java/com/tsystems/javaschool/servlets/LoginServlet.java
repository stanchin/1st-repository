package com.tsystems.javaschool.servlets;

import com.tsystems.javaschool.entities.Client;
import com.tsystems.javaschool.services.impl.ClientServiceImpl;
import com.tsystems.javaschool.servlets.filters.AuthFilter;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    public static final String AUTHORIZATION_ATTRIBUTE = "ok";

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
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("client", client);
                    httpSession.setAttribute(AuthFilter.AUTHORISED_ATTRIBUTE, AUTHORIZATION_ATTRIBUTE);
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
                    HttpSession httpSession = request.getSession();
                    httpSession.removeAttribute(AuthFilter.AUTHORISED_ATTRIBUTE);
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                } catch (Exception e) {
                    response.sendError(300, "You're not logged in");
                }
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
