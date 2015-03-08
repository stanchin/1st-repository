package com.tsystems.javaschool.servlets;

import com.tsystems.javaschool.entities.*;
import com.tsystems.javaschool.entities.Number;
import com.tsystems.javaschool.exceptions.IncompatibleOptionException;
import com.tsystems.javaschool.exceptions.RequiredOptionException;
import com.tsystems.javaschool.exceptions.WrongIdException;
import com.tsystems.javaschool.services.OperatorService;
import com.tsystems.javaschool.services.impl.OperatorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
                SimpleDateFormat sdf = new SimpleDateFormat();
                sdf.applyPattern("yyyy-mm-dd");
                Date birthday = null;
                try {
                    birthday = sdf.parse(req.getParameter("birthday"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String address = req.getParameter("address");
                String email = req.getParameter("email");
                String password = String.valueOf(req.getParameter("password").hashCode());
                long passport = Long.valueOf(req.getParameter("passport"));
                long roleId = Long.valueOf(req.getParameter("roleId"));
                System.out.println("" + name + surname + birthday + address + email + password + passport + roleId);

                try {
                    operatorService.addClient(name, surname, birthday, address, passport, email, password,  roleId);
                } catch (Exception e) {
                    req.setAttribute("error", e.getMessage());
                    req.getRequestDispatcher("/error.jsp").forward(req, resp);
                }
                req.getRequestDispatcher("admin.jsp").forward(req, resp);
                break;

            case "addRole":
                String description = req.getParameter("description");
                try {
                    operatorService.addRole(description);
                } catch (Exception e) {
                    resp.sendError(404, "Can't add role");
                }
                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
                break;
            case "concludeContact":
                long clientId = Long.valueOf(req.getParameter("clientId"));
                long tariffId = Long.valueOf(req.getParameter("tariffId"));
                long number = Long.valueOf(req.getParameter("number"));
                try {
                    operatorService.concludeContract(clientId, tariffId, number);
                } catch (WrongIdException e) {
                    req.setAttribute("error", e.getMessage());
                    req.getRequestDispatcher("/error.jsp").forward(req, resp);
                }
                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
                break;
            case "generateNumber":
                Number number1 = operatorService.generateUniqueNumber();
                req.getSession().setAttribute("number", number1);
                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
                break;
            case "dropContractOption":
                long contractId = Long.valueOf(req.getParameter("contractId"));
                long optionId = Long.valueOf(req.getParameter("optionId"));
                try {
                    operatorService.shutDownContractOption(contractId, optionId);
                } catch (RequiredOptionException e) {
                    req.setAttribute("error", e.getMessage());
                    req.getRequestDispatcher("/error.jsp").forward(req, resp);
                } catch (WrongIdException e) {
                    req.setAttribute("error", e.getMessage());
                    req.getRequestDispatcher("/error.jsp").forward(req, resp);
                }
                resp.sendRedirect("/admin.jsp");
                break;
            case "getClients":
                List<Client> clients = operatorService.getClients();
                req.getSession().setAttribute("clients", clients);
                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
                break;
            case "getContracts":
                List<Contract> contracts = operatorService.getContracts();
                req.getSession().setAttribute("contracts", contracts);
                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
                break;
            case "getTariffs":
                List<Tariff> tariffs = operatorService.getTariffs();
                req.getSession().setAttribute("tariffs", tariffs);
                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
                break;
            case "blockContract":
                long number2 = Long.valueOf(req.getParameter("number"));
                try {
                    operatorService.blockNumber(number2);
                } catch (WrongIdException e) {
                    req.setAttribute("error", e.getMessage());
                    req.getRequestDispatcher("/error.jsp").forward(req, resp);
                }
                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
                break;
            case "deployContract":
                long number3 = Long.valueOf(req.getParameter("number"));
                try {
                    operatorService.blockNumber(number3);
                } catch (WrongIdException e) {
                    req.setAttribute("error", e.getMessage());
                    req.getRequestDispatcher("/error.jsp").forward(req, resp);
                }
                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
                break;
            case "findClient":
                long number4 = Long.valueOf(req.getParameter("number"));
                Client client1 = null;
                try {
                    client1 = operatorService.find(number4);
                } catch (Exception e) {
                    req.setAttribute("error", e.getMessage());
                    req.getRequestDispatcher("/error.jsp").forward(req, resp);
                }
                req.getSession().setAttribute("foundedClient", client1);
                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
                break;
            case "changeTariff":
                long contractId1 = Long.valueOf(req.getParameter("contractId"));
                long tariffId1 = Long.valueOf(req.getParameter("tariffId"));
                try {
                    operatorService.changeTariff(contractId1, tariffId1);
                } catch (WrongIdException e) {
                    req.setAttribute("error", e.getMessage());
                    req.getRequestDispatcher("/error.jsp").forward(req, resp);
                }
                resp.sendRedirect("/admin.jsp");
                break;
            case "addTariff":
                String name1 = req.getParameter("name");
                String[] values = req.getParameterValues("optionsId");
                long[] optionsId = new long[values.length];
                for (int i = 0; i< values.length; i++){
                    optionsId[i] = Long.valueOf(values[i]);
                }
                try {
                    operatorService.addTariff(name1, optionsId);
                } catch (WrongIdException e) {
                    req.setAttribute("error", e.getMessage());
                    req.getRequestDispatcher("/error.jsp").forward(req, resp);
                }
                resp.sendRedirect("/admin.jsp");

            case "dropTariff":
                long tariffId3 = Long.valueOf(req.getParameter("tariffId"));
                try {
                    operatorService.dropTariff(tariffId3);
                } catch (WrongIdException e) {
                    req.setAttribute("error", e.getMessage());
                    req.getRequestDispatcher("/error.jsp").forward(req, resp);
                }
                resp.sendRedirect("/admin.jsp");
            case "addOption":
                String optionName = req.getParameter("name");
                double val1 = Double.valueOf(req.getParameter("optionPrice"));
                double val2 = Double.valueOf(req.getParameter("connectionPrice"));
                BigDecimal optionPrice = new BigDecimal(val1);
                BigDecimal connectionPrice = new BigDecimal(val2);
                try {
                    operatorService.addOption(optionName, optionPrice, connectionPrice);
                } catch (Exception e) {
                    req.setAttribute("error", e.getMessage());
                    req.getRequestDispatcher("/error.jsp").forward(req, resp);
                }
                resp.sendRedirect("/admin.jsp");
                break;
            case "dropTariffOption":
                long tariffId2 = Long.valueOf(req.getParameter("tariffId"));
                long optionId1 = Long.valueOf(req.getParameter("optionId"));
                try {
                    operatorService.dropOption(tariffId2, optionId1);
                } catch (WrongIdException e) {
                    req.setAttribute("error", e.getMessage());
                    req.getRequestDispatcher("/error.jsp").forward(req, resp);
                }
                resp.sendRedirect("/admin.jsp");
                break;
            case "setIncompatibleOptions":
                long baseOptionId = Long.valueOf(req.getParameter("baseOptionId"));
                long incOptionId = Long.valueOf(req.getParameter("incOptionId"));
                try {
                    operatorService.setIncompatibleOptions(baseOptionId, incOptionId);
                } catch (IncompatibleOptionException e) {
                    req.setAttribute("error", e.getMessage());
                    req.getRequestDispatcher("/error.jsp").forward(req, resp);
                }
                resp.sendRedirect("/admin.jsp");
                break;
            case "setRequiredOptions":
                long baseOptionId1 = Long.valueOf(req.getParameter("baseOptionId"));
                long incOptionId1 = Long.valueOf(req.getParameter("incOptionId"));

                try {
                    operatorService.setIncompatibleOptions(baseOptionId1, incOptionId1);
                } catch (IncompatibleOptionException e) {
                    req.setAttribute("error", e.getMessage());
                    req.getRequestDispatcher("/error.jsp").forward(req, resp);
                }
                resp.sendRedirect("/admin.jsp");
                break;
            case "showOptions":
                List<Option> options = operatorService.getOptions();
                req.getSession().setAttribute("options", options);
                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
