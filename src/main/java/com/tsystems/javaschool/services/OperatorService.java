package com.tsystems.javaschool.services;


import com.tsystems.javaschool.entities.*;
import com.tsystems.javaschool.entities.Number;

import java.util.List;

public interface OperatorService {

    public void addClient();

    public void concludeContract();

    public Number generateUniqueNumber();

    public void setNumber(Number number, int contractId, int clientId);

    public void setTariff(int contractId, int tariffId);

    public void setOptions(int contractId, Integer...optionsId);

    public void shutDownContractOption(int contractId, int optionId);

    public List<Client> getClients();

    public List<Contract> getContracts();

    public List<Tariff> getTariffs();

    public void blockClient(int clientId);

    public void deployClient(int clientId);

    public Client find(int number);

    public void changeTariff(int contractId, List<Tariff> tariffs);

    public void addTariff();

    public void dropTariff(int tariffId);

    public void addOption(int tariffId, int optionId);

    public void dropOption(int tariffId, int optionId);

    public void setIncompatibleOptions(int optionId, Integer...optionsId);

    public void setRequiredOptions(int optionId, Integer...optionsId);
}
