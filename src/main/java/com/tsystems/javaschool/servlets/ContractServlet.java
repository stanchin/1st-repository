package com.tsystems.javaschool.servlets;


import com.tsystems.javaschool.entities.Contract;
import com.tsystems.javaschool.exceptions.WrongIdException;
import com.tsystems.javaschool.persistence.Session;
import com.tsystems.javaschool.services.impl.ClientServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ContractServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClientServiceImpl clientService = new ClientServiceImpl();
        String action = req.getParameter("action");
        long clientId = Long.valueOf(req.getParameter("clientId"));

        Session session = Session.getInstance();
        session.setOpened(Boolean.valueOf(req.getParameter("sessionStatus")));
        req.setAttribute("session", session);
        switch (action){
            case "getContract":
                try {
                    List<Contract> contracts = clientService.getContracts(clientId);
                    req.getSession().setAttribute("contracts", contracts);
                    req.getRequestDispatcher("/client.jsp").forward(req, resp);
                } catch (WrongIdException e) {
                    resp.sendError(404, "Wrong client id");
                }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
