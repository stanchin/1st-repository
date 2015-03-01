package com.tsystems.javaschool.servlets;


import com.tsystems.javaschool.entities.Client;
import com.tsystems.javaschool.persistence.HibernateUtil;
import com.tsystems.javaschool.services.ClientService;
import com.tsystems.javaschool.services.impl.ClientServiceImpl;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private static HibernateUtil util = new HibernateUtil();
    private static EntityManager entityManager = util.getEntityManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        ClientService clientService = new ClientServiceImpl(entityManager);
        RequestDispatcher rd;

        Client client = clientService.getClient(email, password);
        if (client != null){
            String role = client.getRole().getRole();
            switch (role.toUpperCase()){
                case "ADMIN":
                    rd = req.getRequestDispatcher("admin.jsp");
                    req.setAttribute("client", client);
                    rd.forward(req, resp);
                    break;
                case "CLIENT":
                    rd = req.getRequestDispatcher("client.jsp");
                    req.setAttribute("client", client);
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
