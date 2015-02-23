package com.tsystems.javaschool.services;


import com.tsystems.javaschool.entities.*;
import com.tsystems.javaschool.entities.Number;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OperatorService {

    public void addClient(String name, String surname, Date birthday, String address, Long passport, String email,
                          String password, int roleId);

    public void addRole(String desc);

    public void concludeContract(Client client, int tariffId, long number);

    public Number generateUniqueNumber();

    public void setNumber(long num, Contract contract);

    public void setTariff(Contract contract, int tariffId);

    public void setOptions(int contractId, Integer...optionsId);

    public void shutDownContractOption(int contractId, int optionId);

    public List<Client> getClients();

    public List<Contract> getContracts();

    public List<Tariff> getTariffs();

    public void blockClient(int clientId);

    public void deployClient(int clientId);

    public Client find(int number);

    public void changeTariff(int contractId, int tariffId);

    public void addTariff(String name, Integer...optionsId);

    public void dropTariff(int tariffId);

    public void addOption(String name, BigDecimal optionPrice, BigDecimal connectionPrice);

    public void dropOption(int tariffId, int optionId);

    public List<Option> setIncompatibleOptions(int optionId, Integer...optionsId);

    public List<Option> setRequiredOptions(int optionId, Integer...optionsId);
}
