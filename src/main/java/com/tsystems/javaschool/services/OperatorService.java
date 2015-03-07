package com.tsystems.javaschool.services;


import com.tsystems.javaschool.entities.*;
import com.tsystems.javaschool.entities.Number;
import com.tsystems.javaschool.exceptions.IncompatibleOptionException;
import com.tsystems.javaschool.exceptions.RequiredOptionException;
import com.tsystems.javaschool.exceptions.WrongIdException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OperatorService {

    public void addClient(String name, String surname, Date birthday, String address, long passport, String email,
                          String password, long roleId);

    public void addRole(String desc);

    public void concludeContract(long clientId, long tariffId, long number) throws WrongIdException;

    public Number generateUniqueNumber();

    public void setNumber(long num, Contract contract);

    public void setTariff(Contract contract, long tariffId) throws WrongIdException;

    public void setOptions(long contractId, long...optionsId) throws IncompatibleOptionException, WrongIdException;

    public void shutDownContractOption(long contractId, long optionId) throws RequiredOptionException, WrongIdException;

    public List<Client> getClients();

    public List<Contract> getContracts();

    public List<Tariff> getTariffs();

    public void blockNumber(long clientId) throws WrongIdException;

    public void deployNumber(long clientId) throws WrongIdException;

    public Client find(long number);

    public void changeTariff(long contractId, long tariffId) throws WrongIdException;

    public void addTariff(String name, long...optionsId) throws WrongIdException;

    public void dropTariff(long tariffId) throws WrongIdException;

    public void addOption(String name, BigDecimal optionPrice, BigDecimal connectionPrice);

    public void dropOption(long tariffId, long optionId) throws WrongIdException;

    public List<Option> setIncompatibleOptions(long optionId, long...optionsId) throws IncompatibleOptionException;

    public List<Option> setRequiredOptions(long optionId, long...optionsId) throws RequiredOptionException;
}
