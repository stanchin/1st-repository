package com.tsystems.javaschool.services;


import com.tsystems.javaschool.entities.Client;
import com.tsystems.javaschool.entities.Contract;
import com.tsystems.javaschool.entities.Option;
import com.tsystems.javaschool.entities.Tariff;
import com.tsystems.javaschool.persistence.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    EntityManager em;

    @Override
    public List<Contract> getContracts(int clientId) {
        Client client;
        try {
            em = HibernateUtil.getEntityManager();
            client = em.find(Client.class, clientId);
            return client.getNumbers();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<Tariff> getTariffs() {
        return null;
    }

    @Override
    public void changeTariff(int contractId) {

    }

    @Override
    public List<Option> getCompatibleOptions(Tariff tariff) {
        return null;
    }

    @Override
    public void setOptions(int contractId) {

    }

    @Override
    public void removeOptions(int contractId) {

    }

    @Override
    public void blockNumber() {

    }

    @Override
    public void deployNumber() {

    }
}
