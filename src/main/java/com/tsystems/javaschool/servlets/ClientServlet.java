package com.tsystems.javaschool.servlets;

import com.tsystems.javaschool.entities.Client;
import com.tsystems.javaschool.entities.Contract;
import com.tsystems.javaschool.entities.Option;
import com.tsystems.javaschool.entities.Tariff;
import com.tsystems.javaschool.exceptions.IncompatibleOptionException;
import com.tsystems.javaschool.exceptions.WrongIdException;
import com.tsystems.javaschool.persistence.PersistenceUtil;
import com.tsystems.javaschool.services.ClientService;
import com.tsystems.javaschool.services.impl.ClientServiceImpl;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClientService clientService = new ClientServiceImpl();
        Client client = (Client) req.getSession().getAttribute("client");

        String action = req.getParameter("action");

        switch (action){
            case "showContracts":
                try {
                    List<Contract> contracts = clientService.getContracts(client.getId());
                    req.getSession().setAttribute("contracts", contracts);
                    req.getRequestDispatcher("/contract.jsp").forward(req, resp);
                } catch (WrongIdException e) {
                    resp.sendError(404, "Client not found");
                }
                break;
            case "showTariffs":
                List<Tariff> tariffs = clientService.getTariffs();
                req.getSession().setAttribute("tariffs", tariffs);
                req.getRequestDispatcher("/tariff.jsp").forward(req, resp);
                break;
            case "showTariffOptions":
                long tariffId = Long.valueOf(req.getParameter("tariffId"));
                List<Option> options = clientService.getTariffOptions(tariffId);
                req.getSession().setAttribute("options", options);
                req.getRequestDispatcher("/tariff.jsp").forward(req, resp);
                break;
            case "changeTariff":
                long newTariffId = Long.valueOf(req.getParameter("tariffId"));
                long contractId = Long.valueOf(req.getParameter("contractId"));
                EntityManager em = PersistenceUtil.getEntityManager();
                Tariff tariff = clientService.getTariff(newTariffId);
                Contract contract = clientService.getContract(contractId);
                em.getTransaction().begin();
                try {
                    contract.setTariff(tariff);
                } catch (Exception e) {
                    em.getTransaction().rollback();
                }
                em.getTransaction().commit();
                req.getRequestDispatcher("/contract.jsp");
                break;
            case "addOption":
                long contractId1 = Long.valueOf(req.getParameter("contractId"));
                long optionId = Long.valueOf(req.getParameter("optionId"));
                EntityManager em1 = PersistenceUtil.getEntityManager();
                em1.getTransaction().begin();
                try {
                    clientService.setOptions(contractId1, optionId);
                } catch (WrongIdException e) {
                    resp.sendError(404, "Can't find contract.");
                    em1.getTransaction().rollback();
                } catch (IncompatibleOptionException e) {
                    resp.sendError(404, "Can't merge incompatible option");
                    em1.getTransaction().rollback();
                }
                em1.getTransaction().commit();
                req.getRequestDispatcher("/contract.jsp").forward(req, resp);
                break;
            case "dropContractOption":
                long contractId2 = Long.valueOf(req.getParameter("contractId"));
                long optionId1 = Long.valueOf(req.getParameter("optionId"));
                EntityManager em2 = PersistenceUtil.getEntityManager();
                em2.getTransaction().begin();
                try {
                    clientService.removeOptions(contractId2, optionId1);
                } catch (WrongIdException e) {
                    resp.sendError(404, "Can't find option");
                    em2.getTransaction().rollback();
                }
                em2.getTransaction().commit();
                req.getRequestDispatcher("/contract.jsp").forward(req, resp);
                break;
            case "blockContract":
                long contractId3 = Long.valueOf(req.getParameter("contractId"));
                EntityManager entityManager = PersistenceUtil.getEntityManager();
                entityManager.getTransaction().begin();
                try {
                    clientService.blockNumber(contractId3);
                } catch (WrongIdException e) {
                    resp.sendError(404, "Can't find contract");
                    entityManager.getTransaction().rollback();
                }
                entityManager.getTransaction().commit();
                req.getRequestDispatcher("/contract.jsp").forward(req, resp);
                break;
            case "deployContract":
                long contractId4 = Long.valueOf(req.getParameter("contractId"));
                EntityManager entityManager1 = PersistenceUtil.getEntityManager();
                entityManager1.getTransaction().begin();
                try {
                    clientService.deployNumber(contractId4);
                } catch (WrongIdException e) {
                    resp.sendError(404, "Can't find contract");
                    entityManager1.getTransaction().rollback();
                }
                entityManager1.getTransaction().commit();
                req.getRequestDispatcher("/contract.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
