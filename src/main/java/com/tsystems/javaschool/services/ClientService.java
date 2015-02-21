package com.tsystems.javaschool.services;


import com.tsystems.javaschool.entities.Contract;
import com.tsystems.javaschool.entities.Option;
import com.tsystems.javaschool.entities.Tariff;

import java.util.List;

public interface ClientService {

    public List<Contract> getContracts(int clientId);

    public List<Tariff> getTariffs();

    public void changeTariff(int contractId, Tariff tariff);

    public List<Option> getCompatibleOptions(Tariff tariff);

    public void setOptions(int contractId, List<Option> options);

    public void removeOptions(int contractId, List<Option> options);

    public void blockNumber(int contractId);

    public void deployNumber(int contractId);

}
