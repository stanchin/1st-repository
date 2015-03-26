package com.tsystems.javaschool.services;


import com.tsystems.javaschool.entities.*;
import com.tsystems.javaschool.exceptions.IncompatibleOptionException;
import com.tsystems.javaschool.exceptions.WrongIdException;

import java.util.List;

public interface ClientService {

    public Client getClient (String email, String password);

    public List<Contract> getContracts(long clientId) throws WrongIdException;

    public List<Tariff> getTariffs();

    public Tariff getTariff(long tariffId);

    public Contract getContract(long contractId);

    public Option getOption(long optionId);

    public List<Option> getTariffOptions(long tariffId);

    public void changeTariff(long contractId, long tariffId) throws WrongIdException;

    public void setOptions(long contractId, long... optionsId) throws WrongIdException, IncompatibleOptionException;

    public void removeOptions(long contractId, long... optionsId) throws WrongIdException;

    public void lockNumber(long contractId) throws WrongIdException;

    public void unlockNumber(long contractId) throws WrongIdException;

    public Role getRole(String authority);
}
