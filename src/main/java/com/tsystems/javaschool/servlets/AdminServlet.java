package com.tsystems.javaschool.servlets;

import com.tsystems.javaschool.entities.*;
import com.tsystems.javaschool.entities.Number;
import com.tsystems.javaschool.exceptions.IncompatibleOptionException;
import com.tsystems.javaschool.exceptions.RequiredOptionException;
import com.tsystems.javaschool.exceptions.WrongIdException;
import com.tsystems.javaschool.persistence.PersistenceUtil;
import com.tsystems.javaschool.services.OperatorService;
import com.tsystems.javaschool.services.impl.OperatorServiceImpl;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


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
                    resp.sendError(404, "Can't create client" + e.getMessage());
                    em.getTransaction().rollback();
                }
                em.getTransaction().commit();
                req.getRequestDispatcher("mobile/admin.jsp").forward(req, resp);
                break;

            case "addRole":
                String description = req.getParameter("description");
                EntityManager entityManager = PersistenceUtil.getEntityManager();
                entityManager.getTransaction().begin();
                try {
                    operatorService.addRole(description);
                } catch (Exception e) {
                    entityManager.getTransaction().rollback();
                }
                entityManager.getTransaction().commit();
                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
                break;
            case "concludeContact":
                long clientId = Long.valueOf(req.getParameter("clientId"));
                long tariffId = Long.valueOf(req.getParameter("tariffId"));
                long number = Long.valueOf(req.getParameter("number"));
                EntityManager entityManager1 = PersistenceUtil.getEntityManager();
                entityManager1.getTransaction().begin();
                try {
                    operatorService.concludeContract(clientId, tariffId, number);
                } catch (WrongIdException e) {
                    resp.sendError(404, "Can't find tariff or client" + e.getMessage());
                    entityManager1.getTransaction().rollback();
                }
                entityManager1.getTransaction().commit();
                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
                break;
            case "generateNumber":
                EntityManager entityManager2 = PersistenceUtil.getEntityManager();
                entityManager2.getTransaction().begin();
                Number number1 = operatorService.generateUniqueNumber();
                entityManager2.getTransaction().commit();
                req.getSession().setAttribute("number", number1);
                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
                break;
            case "dropContractOption":
                long contractId = Long.valueOf(req.getParameter("contractId"));
                long optionId = Long.valueOf(req.getParameter("optionId"));
                EntityManager entityManager3 = PersistenceUtil.getEntityManager();
                entityManager3.getTransaction().begin();
                try {
                    operatorService.shutDownContractOption(contractId, optionId);
                } catch (RequiredOptionException e) {
                    resp.sendError(404, "Can't shut down required option" + e.getMessage());
                    entityManager3.getTransaction().rollback();
                } catch (WrongIdException e) {
                    resp.sendError(404, "Can't find contract or option" + e.getMessage());
                    entityManager3.getTransaction().rollback();
                }
                entityManager3.getTransaction().commit();
                resp.sendRedirect("/admin.jsp");
                break;
            case "getClients":
                EntityManager entityManager4 = PersistenceUtil.getEntityManager();
                entityManager4.getTransaction().begin();
                List<Client> clients = operatorService.getClients();
                entityManager4.getTransaction().commit();
                req.getSession().setAttribute("clients", clients);
                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
                break;
            case "getContracts":
                EntityManager entityManager5 = PersistenceUtil.getEntityManager();
                entityManager5.getTransaction().begin();
                List<Contract> contracts = operatorService.getContracts();
                entityManager5.getTransaction().commit();
                req.getSession().setAttribute("contracts", contracts);
                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
                break;
            case "getTariffs":
                EntityManager entityManager6 = PersistenceUtil.getEntityManager();
                entityManager6.getTransaction().begin();
                List<Tariff> tariffs = operatorService.getTariffs();
                entityManager6.getTransaction().commit();
                req.getSession().setAttribute("tariffs", tariffs);
                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
                break;
            case "blockContract":
                long number2 = Long.valueOf(req.getParameter("number"));
                EntityManager entityManager7 = PersistenceUtil.getEntityManager();
                entityManager7.getTransaction().begin();
                try {
                    operatorService.blockNumber(number2);
                } catch (WrongIdException e) {
                    resp.sendError(404, "Can't find number" + e.getMessage());
                    entityManager7.getTransaction().rollback();
                }
                entityManager7.getTransaction().commit();
                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
                break;
            case "deployContract":
                long number3 = Long.valueOf(req.getParameter("number"));
                EntityManager entityManager8 = PersistenceUtil.getEntityManager();
                entityManager8.getTransaction().begin();
                try {
                    operatorService.blockNumber(number3);
                } catch (WrongIdException e) {
                    resp.sendError(404, "Can't find number" + e.getMessage());
                    entityManager8.getTransaction().rollback();
                }
                entityManager8.getTransaction().commit();
                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
                break;
            case "findClient":
                long number4 = Long.valueOf(req.getParameter("number"));
                EntityManager em1 = PersistenceUtil.getEntityManager();
                em1.getTransaction().begin();
                Client client1 = null;
                try {
                    client1 = operatorService.find(number4);
                } catch (Exception e) {
                    resp.sendError(404, "Can't find client" + e.getMessage());
                    em1.getTransaction().rollback();
                }
                req.getSession().setAttribute("foundedClient", client1);
                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
                break;
            case "changeTariff":
                long contractId1 = Long.valueOf(req.getParameter("contractId"));
                long tariffId1 = Long.valueOf(req.getParameter("tariffId"));
                EntityManager em2 = PersistenceUtil.getEntityManager();
                em2.getTransaction().begin();
                try {
                    operatorService.changeTariff(contractId1, tariffId1);
                } catch (WrongIdException e) {
                    resp.sendError(404, "Can't find contract or tariff" + e.getMessage());
                    em2.getTransaction().rollback();
                }
                em2.getTransaction().commit();
                resp.sendRedirect("/admin.jsp");
                break;
            case "addTariff":
                String name1 = req.getParameter("name");
                String[] values = req.getParameterValues("optionsId");
                long[] optionsId = new long[values.length];
                for (int i = 0; i< values.length; i++){
                    optionsId[i] = Long.valueOf(values[i]);
                }
                EntityManager em7 = PersistenceUtil.getEntityManager();
                em7.getTransaction().begin();
                try {
                    operatorService.addTariff(name1, optionsId);
                } catch (WrongIdException e) {
                    resp.sendError(404, "Cant't find option" + e.getMessage());
                    em7.getTransaction().rollback();
                }
                em7.getTransaction().commit();
                resp.sendRedirect("/admin.jsp");

            case "dropTariff":
                long tariffId3 = Long.valueOf(req.getParameter("tariffId"));
                EntityManager em8 = PersistenceUtil.getEntityManager();
                em8.getTransaction().begin();
                try {
                    operatorService.dropTariff(tariffId3);
                } catch (WrongIdException e) {
                    resp.sendError(404, "Can't find tariff");
                    em8.getTransaction().rollback();
                }
                em8.getTransaction().commit();
                resp.sendRedirect("/admin.jsp");
            case "addOption":
                String optionName = req.getParameter("name");
                double val1 = Double.valueOf(req.getParameter("optionPrice"));
                double val2 = Double.valueOf(req.getParameter("connectionPrice"));
                BigDecimal optionPrice = new BigDecimal(val1);
                BigDecimal connectionPrice = new BigDecimal(val2);
                EntityManager em3 = PersistenceUtil.getEntityManager();
                em3.getTransaction().begin();
                try {
                    operatorService.addOption(optionName, optionPrice, connectionPrice);
                } catch (Exception e) {
                    resp.sendError(404, "Can't add option." + e.getMessage());
                    em3.getTransaction().rollback();
                }
                em3.getTransaction().commit();
                resp.sendRedirect("/admin.jsp");
                break;
            case "dropTariffOption":
                long tariffId2 = Long.valueOf(req.getParameter("tariffId"));
                long optionId1 = Long.valueOf(req.getParameter("optionId"));
                EntityManager em4 = PersistenceUtil.getEntityManager();
                em4.getTransaction().begin();
                try {
                    operatorService.dropOption(tariffId2, optionId1);
                } catch (WrongIdException e) {
                    resp.sendError(404, "Can't find tariff or option.");
                    em4.getTransaction().rollback();
                }
                em4.getTransaction().commit();
                resp.sendRedirect("/admin.jsp");
                break;
            case "setIncompatibleOptions":
                long baseOptionId = Long.valueOf(req.getParameter("baseOptionId"));
                long incOptionId = Long.valueOf(req.getParameter("incOptionId"));
                EntityManager em5 = PersistenceUtil.getEntityManager();
                em5.getTransaction().begin();
                try {
                    operatorService.setIncompatibleOptions(baseOptionId, incOptionId);
                } catch (IncompatibleOptionException e) {
                    resp.sendError(404, "Can't set incompatible option");
                    em5.getTransaction().rollback();
                }
                em5.getTransaction().commit();
                resp.sendRedirect("/admin.jsp");
                break;
            case "setRequiredOptions":
                long baseOptionId1 = Long.valueOf(req.getParameter("baseOptionId"));
                long incOptionId1 = Long.valueOf(req.getParameter("incOptionId"));
                EntityManager em6 = PersistenceUtil.getEntityManager();
                em6.getTransaction().begin();
                try {
                    operatorService.setIncompatibleOptions(baseOptionId1, incOptionId1);
                } catch (IncompatibleOptionException e) {
                    resp.sendError(404, "Can't set incompatible option");
                    em6.getTransaction().rollback();
                }
                em6.getTransaction().commit();
                resp.sendRedirect("/admin.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
