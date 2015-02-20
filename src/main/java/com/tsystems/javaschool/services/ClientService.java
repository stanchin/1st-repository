package com.tsystems.javaschool.services;


import com.tsystems.javaschool.entities.Contract;
import com.tsystems.javaschool.entities.Option;
import com.tsystems.javaschool.entities.Tariff;

import java.util.List;

public interface ClientService {

    public List<Contract> getContracts(int clientId);

    public List<Tariff> getTariffs();

    public void changeTariff(int contractId);

    public List<Option> getCompatibleOptions(Tariff tariff);

    public void setOptions(int contractId);

    public void removeOptions(int contractId);

    public void blockNumber();

    public void deployNumber();

}
