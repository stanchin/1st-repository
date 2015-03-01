package com.tsystems.javaschool.services.impl;


import com.tsystems.javaschool.dao.impl.ClientDao;
import com.tsystems.javaschool.dao.impl.ContractDao;
import com.tsystems.javaschool.dao.impl.OptionDao;
import com.tsystems.javaschool.dao.impl.TariffDao;
import com.tsystems.javaschool.entities.Client;
import com.tsystems.javaschool.entities.Contract;
import com.tsystems.javaschool.entities.Option;
import com.tsystems.javaschool.entities.Tariff;
import com.tsystems.javaschool.exceptions.IncompatibleOptionException;
import com.tsystems.javaschool.exceptions.WrongIdException;
import com.tsystems.javaschool.services.ClientService;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    private static final Logger LOGGER = Logger.getLogger(ClientServiceImpl.class);
    EntityManager em;

    private static ContractDao contractDao = new ContractDao();
    private static ClientDao clientDao = new ClientDao();
    private static TariffDao tariffDao = new TariffDao();
    private static OptionDao optionDao = new OptionDao();

    public ClientServiceImpl(EntityManager em) {
        LOGGER.debug("Creating client service");
        this.em = em;
    }

    @Override
    public Client getClient(String email, String password) {
        return clientDao.findByEmailPass(email, password);
    }

    @Override
    public List<Contract> getContracts(long clientId) throws WrongIdException {
        LOGGER.debug("Getting client contracts");
        Client client = clientDao.getById(clientId);
        if (client == null) throw new WrongIdException("Client with id = " + clientId + " doesn't exist.");
        return client.getNumbers();
    }

    @Override
    public List<Tariff> getTariffs() {
        LOGGER.debug("Getting tariffs");
        return tariffDao.getAll();
    }

    @Override
    public void changeTariff(long contractId, long tariffId) throws WrongIdException {
        LOGGER.debug("Changing tariff");
        Contract contract = contractDao.getById(contractId);
        if (contract == null) throw new WrongIdException("Contract with id = " + contractId + " doesn't exist.");
        Tariff tariff = tariffDao.getById(tariffId);
        if (tariff == null) throw new WrongIdException("Tariff with id = " + tariffId + " doesn't exist.");
        List<Option> list = tariff.getOptions();
        contract.setTariff(tariff);
        contract.setOptions(list);
        contractDao.update(contract);
    }

    @Override
    public void setOptions(long contractId, long... optionsId) throws WrongIdException, IncompatibleOptionException {
        LOGGER.debug("Setting options");
        Contract contract = contractDao.getById(contractId);
        if (contract == null) throw new WrongIdException("Contract with id = " + contractId + " doesn't exist.");
        List<Option> options = new ArrayList<Option>();
        List<Option> contractOptions;

        if (contract.getOptions() != null){
            contractOptions = contract.getOptions();
            for (long id : optionsId){
                Option option = optionDao.getById(id);

                for (Option o : contractOptions){
                    if (o.getIncOptions().contains(option)){
                        LOGGER.error("Option can't be merged with incompatible option");
                        throw new IncompatibleOptionException("Option can't be merged with incompatible option");
                    } else
                        options.add(option);
                }
            }
        }
        contract.setOptions(options);
        contractDao.update(contract);
    }

    @Override
    public void removeOptions(long contractId, long... optionsId) throws WrongIdException {
        LOGGER.debug("Removing options");
        Contract contract = contractDao.getById(contractId);
        if (contract == null) throw new WrongIdException("Contract with id = " + contractId + " doesn't exist.");
        List<Option> options = new ArrayList<Option>();
        List<Option> contractOptions = contract.getOptions();
        for (long id : optionsId) {
            Option option = optionDao.getById(id);
            if (option != null) {
                options.add(option);
            } else throw new WrongIdException("Option with id = " + id + " doesn't exist.");
        }
        contractOptions.removeAll(options);
        contract.setOptions(contractOptions);
        contractDao.update(contract);
    }

    @Override
    public void blockNumber(long contractId) throws WrongIdException {
        LOGGER.debug("Blocking users contract");
        Contract contract = contractDao.getById(contractId);
        if (contract == null) throw new WrongIdException("Contract with id = " + contractId + " doesn't exist.");
        contract.setBlockedByUser(true);
        contractDao.update(contract);
    }

    @Override
    public void deployNumber(long contractId) throws WrongIdException {
        LOGGER.debug("Deploying users contract");
        Contract contract = contractDao.getById(contractId);
        if (contract == null) throw new WrongIdException("Contract with id = " + contractId + " doesn't exist.");
        contract.setBlockedByUser(false);
        contractDao.update(contract);
    }
}
