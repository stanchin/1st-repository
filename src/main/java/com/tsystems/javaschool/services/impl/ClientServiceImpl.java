package com.tsystems.javaschool.services.impl;


import com.tsystems.javaschool.dao.impl.*;
import com.tsystems.javaschool.entities.*;
import com.tsystems.javaschool.exceptions.IncompatibleOptionException;
import com.tsystems.javaschool.exceptions.WrongIdException;
import com.tsystems.javaschool.services.ClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private static final Logger LOGGER = Logger.getLogger(ClientServiceImpl.class);

    @Autowired
    private ContractDao contractDao;
    @Autowired
    private ClientDao clientDao;
    @Autowired
    private TariffDao tariffDao;
    @Autowired
    private OptionDao optionDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    public Client getClient(String email, String password) {
        return clientDao.findByEmailPass(email, password);
    }

    @Override
    public List<Contract> getContracts(long clientId) throws WrongIdException {
        LOGGER.debug("Getting client contracts");
        Client client = clientDao.getById(clientId);
        if (client == null) {
            throw new WrongIdException("Client with id = " + clientId + " doesn't exist.");
        }
        return client.getNumbers();
    }

    @Override
    public List<Tariff> getTariffs() {
        LOGGER.debug("Getting tariffs");
        return tariffDao.getAll();
    }

    @Override
    public Tariff getTariff(long tariffId) {
        LOGGER.debug("Getting tariff");
        return tariffDao.getById(tariffId);
    }

    @Override
    public Contract getContract(long contractId) {
        return contractDao.getById(contractId);
    }

    @Override
    public Option getOption(long optionId) {
        return optionDao.getById(optionId);
    }

    @Override
    public List<Option> getTariffOptions(long tariffId) {
        LOGGER.debug("Getting tariff's options");
        Tariff tariff = tariffDao.getById(tariffId);
        return tariff.getOptions();
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
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
    public void lockNumber(long contractId) throws WrongIdException {
        LOGGER.debug("Blocking users contract");
        Contract contract = contractDao.getById(contractId);
        if (contract == null) throw new WrongIdException("Contract with id = " + contractId + " doesn't exist.");
        contract.setBlockedByUser(true);
        contractDao.update(contract);
    }

    @Override
    public void unlockNumber(long contractId) throws WrongIdException {
        LOGGER.debug("Deploying users contract");
        Contract contract = contractDao.getById(contractId);
        if (contract == null) throw new WrongIdException("Contract with id = " + contractId + " doesn't exist.");
        contract.setBlockedByUser(false);
        contractDao.update(contract);
    }

    @Override
    public Role getRole(String authority) {
        return roleDao.getByDescription(authority);
    }
}
