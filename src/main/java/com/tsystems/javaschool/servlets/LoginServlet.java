package com.tsystems.javaschool.servlets;


import com.tsystems.javaschool.entities.Client;
import com.tsystems.javaschool.services.ClientService;
import com.tsystems.javaschool.services.impl.ClientServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s = req.getParameter("action");

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        ClientService clientService = new ClientServiceImpl();
        RequestDispatcher rd;

        /*entityManager.getTransaction().begin();*/
        Client client = null;
        try {
            client = clientService.getClient(email, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*entityManager.getTransaction().commit();*/

        if (client != null){
            String role = client.getRole().getRole();
            switch (role.toUpperCase()){
                case "ADMIN":
                    rd = req.getRequestDispatcher("admin.jsp");
                    req.getSession().setAttribute("client", client);
                    rd.forward(req, resp);
                    break;
                case "CLIENT":
                    rd = req.getRequestDispatcher("client.jsp");
                    req.getSession().setAttribute("client", client);
                    rd.forward(req, resp);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
