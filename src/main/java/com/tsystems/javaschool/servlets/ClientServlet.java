package com.tsystems.javaschool.servlets;

import com.tsystems.javaschool.persistence.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long clientId = Long.valueOf(req.getParameter("clientId"));
        Session session = Session.getInstance();
        String action = req.getParameter("action");

        switch (action.toUpperCase()){
            case "SHOWCONTRACT":
            case "SHOWTARIFFS":
            case "SHOWOPTIONS":
            case "EDITCONTRACT":
            case "BLOCKNUMBER":

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
