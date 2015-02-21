package com.tsystems.javaschool.services;


import com.tsystems.javaschool.entities.Client;
import com.tsystems.javaschool.entities.Contract;
import com.tsystems.javaschool.entities.Option;
import com.tsystems.javaschool.entities.Tariff;
import com.tsystems.javaschool.persistence.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    EntityManager em = HibernateUtil.getEntityManager();

    @Override
    public List<Contract> getContracts(int clientId) {
        Client client = em.find(Client.class, clientId);
        if (client == null) return null;
        return client.getNumbers();
    }

    @Override
    public List<Tariff> getTariffs() {
        TypedQuery<Tariff> query = em.createQuery("select t from Tariff t", Tariff.class);
        return query.getResultList();
    }

    @Override
    public void changeTariff(int contractId, Tariff tariff){
        Contract contract = em.find(Contract.class, contractId);
        List<Option> list = getCompatibleOptions(tariff);
        contract.setTariff(tariff);
        contract.setOptions(list);
    }

    @Override
    public List<Option> getCompatibleOptions(Tariff tariff) {
        return tariff.getOptions();
    }

    @Override
    public void setOptions(int contractId, List<Option> options) {
        Contract contract = em.find(Contract.class, contractId);
        contract.setOptions(options);
        List<Option> reqOptions = new LinkedList<Option>();
        for (Option o : options){
            contract.setOptions(o.getReqOptions());
        }
    }

    @Override
    public void removeOptions(int contractId, List<Option> options){
        Contract contract = em.find(Contract.class, contractId);
        if (contract == null) throw new NullPointerException();
        List<Option> contractOptions = contract.getOptions();
        contractOptions.removeAll(options);
    }

    @Override
    public void blockNumber(int contractId) {
        Contract contract = em.find(Contract.class, contractId);
        contract.setBlockedByUser(true);
    }

    @Override
    public void deployNumber(int contractId) {
        Contract contract = em.find(Contract.class, contractId);
        contract.setBlockedByUser(false);
    }
}
