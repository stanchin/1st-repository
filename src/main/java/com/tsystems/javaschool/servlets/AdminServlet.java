package com.tsystems.javaschool.servlets;

import com.tsystems.javaschool.entities.Client;
import com.tsystems.javaschool.persistence.PersistenceUtil;
import com.tsystems.javaschool.services.OperatorService;
import com.tsystems.javaschool.services.impl.OperatorServiceImpl;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OperatorService operatorService = new OperatorServiceImpl();
        Client client = (Client) req.getAttribute("client");

        String action = req.getParameter("action");

        switch (action) {
            case "addClient":
                String name = req.getParameter("name");
                String surname = req.getParameter("surname");
                long birthD = Long.valueOf(req.getParameter("birthday"));
                Date birthday = new Date(birthD);
                String address = req.getParameter("address");
                String email = req.getParameter("email");
                String password = String.valueOf(req.getParameter("password").hashCode());
                long passport = Long.valueOf(req.getParameter("passport"));
                long roleId = Long.valueOf(req.getParameter("roleId"));

                EntityManager em = PersistenceUtil.getEntityManager();
                em.getTransaction().begin();
                try {
                    operatorService.addClient(name, surname, birthday, address, passport, email, password,  roleId);
                } catch (Exception e) {
                    resp.sendError(404, "Can't create client");
                    em.getTransaction().rollback();
                }
                em.getTransaction().commit();
                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
                break;

            case "addRole":
            case "concludeContact":
            case "generateNumber":
            case "setNumberToContract":
            case "setTariffToContract":
            case "setOptionsToContract":
            case "dropContractOption":
            case "getClients":
            case "getContracts":
            case "getTariffs":
            case "blockContract":
            case "deployContract":
            case "findClient":
            case "changeTariff":
            case "addTariff":
            case "dropTariff":
            case "addOption":
            case "dropOption":
            case "setIncompatibleOptions":
            case "setRequiredOptions":

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
