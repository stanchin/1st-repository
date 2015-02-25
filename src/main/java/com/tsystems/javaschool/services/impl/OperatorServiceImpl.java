package com.tsystems.javaschool.services.impl;


import com.tsystems.javaschool.entities.*;
import com.tsystems.javaschool.entities.Number;
import com.tsystems.javaschool.services.OperatorService;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class OperatorServiceImpl implements OperatorService {
    private static final Logger LOGGER = Logger.getLogger(OperatorServiceImpl.class);
    EntityManager em;

    public OperatorServiceImpl(EntityManager em){
        LOGGER.debug("Creating operator service");
        this.em = em;
    }

    @Override
    public void addClient(String name, String surname, Date birthday, String address, Long passport, String email,
                          String password, int roleId) {
        LOGGER.debug("Creating new client");
        Client client = new Client();
        client.setName(name);
        client.setSurname(surname);
        client.setBirthday(birthday);
        client.setAddress(address);
        client.setPassport(passport);
        client.setEmail(email);
        client.setPassword(password);
        Role role = em.find(Role.class, roleId);
        client.setRole(role);
        em.persist(client);
    }

    @Override
    public void addRole(String desc) {
        LOGGER.debug("Adding new role");
        Role role = new Role();
        role.setRole(desc);
        em.persist(role);
    }

    @Override
    public void concludeContract(Client client, int tariffId, long number) {
        LOGGER.debug("Concluding contract");
        Contract contract = new Contract();
        contract.setClient(client);
        em.persist(contract);
        setTariff(contract, tariffId);
        setNumber(number, contract);
        em.merge(contract);
    }

    @Override
    public Number generateUniqueNumber() {
        LOGGER.debug("Generating unique number");
        Number number = new Number();
        Random random = new Random(47);

        while (true) {
            int code = random.nextInt(9) + 900;
            int num = 1000000 + random.nextInt(9000000);
            long telNum = Long.parseLong(code + "" + num);
            Number findNumber = em.find(Number.class, telNum);
            if (findNumber == null){
                number.setNumber(telNum);
                em.persist(number);
                break;
            }
        }
        return number;
    }

    @Override
    public void setNumber(long num, Contract contract) {
        LOGGER.debug("Setting number to contract");
        Number number = em.find(Number.class, num);
        contract.setNumber(number);
        number.setContract(contract);
        em.merge(contract);
        em.merge(number);
    }

    @Override
    public void setTariff(Contract contract, int tariffId) {
        LOGGER.debug("Setting tariff");
        Tariff tariff = em.find(Tariff.class, tariffId);
        contract.setTariff(tariff);
        contract.setOptions(tariff.getOptions());
        em.merge(contract);
    }

    @Override
    public void setOptions(int contractId, Integer... optionsId) {
        LOGGER.debug("Setting options");
        Contract contract = em.find(Contract.class, contractId);
        List<Option> options = new ArrayList<Option>();
        List<Option> contractOptions;

        if (contract.getOptions() != null){
            contractOptions = contract.getOptions();
            for (int id : optionsId){
                Option option = em.find(Option.class, id);

                for (Option o : contractOptions){
                    if (o.getIncOptions().contains(option)){
                        LOGGER.error("Option can't be merged with incompatible option");
                        return;
                    } else
                        options.add(option);
                }
            }
        }
        contract.setOptions(options);
        em.merge(contract);
    }

    @Override
    public void shutDownContractOption(int contractId, int optionId) {
        LOGGER.debug("Removing option");
        Contract contract = em.find(Contract.class, contractId);
        Option option = em.find(Option.class, optionId);
        List<Option> options = contract.getOptions();
        for (Option opt : options){
            if (opt.getReqOptions().contains(option)){
                LOGGER.error("Can't drop required option");
                break;
            }
        }
        options.remove(option);
        contract.setOptions(options);
        em.merge(contract);
    }

    @Override
    public List<Client> getClients() {
        TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c", Client.class);
        return query.getResultList();
    }

    @Override
    public List<Contract> getContracts() {
        TypedQuery<Contract> query = em.createQuery("SELECT c FROM Contract c", Contract.class);
        return query.getResultList();
    }

    @Override
    public List<Tariff> getTariffs() {
        TypedQuery<Tariff> query = em.createQuery("SELECT t FROM Tariff t", Tariff.class);
        return query.getResultList();
    }

    @Override
    public void blockClient(int clientId) {
        LOGGER.debug("Blocking client");
        Contract contract = em.createQuery(
                "SELECT c FROM Client cl JOIN cl.numbers c" +
                        " WHERE cl.id = :clientId", Contract.class).
                setParameter("clientId", clientId).getSingleResult();
        contract.setBlockedByOperator(true);
        em.merge(contract);
    }

    @Override
    public void deployClient(int clientId) {
        LOGGER.debug("Deploying client");
        Contract contract = em.createQuery(
                "SELECT c FROM Client cl JOIN cl.numbers c" +
                        " WHERE cl.id = :clientId", Contract.class).
                setParameter("clientId", clientId).getSingleResult();
        contract.setBlockedByOperator(false);
        em.merge(contract);
    }

    @Override
    public Client find(int number) {
        LOGGER.debug("Searching client");
        return em.createQuery("SELECT c.client FROM Contract c " +
                " WHERE c.number = :number", Client.class).
                setParameter("number", number).getSingleResult();
    }

    @Override
    public void changeTariff(int contractId, int tariffId) {
        LOGGER.debug("Changing tariff");
        Contract contract = em.find(Contract.class, contractId);
        Tariff tariff = em.find(Tariff.class, tariffId);
        contract.setTariff(tariff);
        contract.setOptions(tariff.getOptions());
        em.merge(contract);
    }

    @Override
    public void addTariff(String name, Integer...optionsId) {
        LOGGER.debug("Adding tariff");
        Tariff tariff = new Tariff();
        tariff.setName(name);

        Double price = 0d;
        List<Option> options = new ArrayList<Option>();
        for (int id : optionsId){
            Option option = em.find(Option.class, id);
            if (option != null) {
                price += option.getConnectionPrice().doubleValue();
                price += option.getOptionPrice().doubleValue();
                options.add(option);
            }
        }
        tariff.setPrice(new BigDecimal(price));
        tariff.setOptions(options);
        em.persist(tariff);
    }

    @Override
    public void dropTariff(int tariffId) {
        LOGGER.debug("Deleting tariff");
        Tariff tariff = em.find(Tariff.class, tariffId);
        em.remove(tariff);
    }

    @Override
    public void addOption(String name, BigDecimal optionPrice, BigDecimal connectionPrice) {
        LOGGER.debug("Creating new option");
        Option option = new Option();
        option.setName(name);
        option.setOptionPrice(optionPrice);
        option.setConnectionPrice(connectionPrice);
        em.persist(option);
    }

    @Override
    public void dropOption(int tariffId, int optionId) {
        LOGGER.debug("Drop option");
        Tariff tariff = em.find(Tariff.class, tariffId);
        Option option = em.find(Option.class, optionId);
        tariff.getOptions().remove(option);
        em.merge(tariff);
    }

    @Override
    public List<Option> setIncompatibleOptions(int optionId, Integer... optionsId) {
        LOGGER.debug("Setting incompatible options");
        Option option = em.find(Option.class, optionId);
        List<Option> incOptions = new ArrayList<Option>();
        for (int id : optionsId){
            Option incOption = em.find(Option.class, id);
            if (option.getReqOptions().contains(incOption)){
                LOGGER.error("Option can't be merged with required option");
                return null;
            }
            if (incOption != null) incOptions.add(incOption);
        }
        option.setIncOptions(incOptions);
        em.merge(option);
        return incOptions;
    }

    @Override
    public List<Option> setRequiredOptions(int optionId, Integer... optionsId) {
        LOGGER.debug("Setting required options");
        Option option = em.find(Option.class, optionId);
        List<Option> reqOptions = new ArrayList<Option>();
        for (int id : optionsId){
            Option reqOption = em.find(Option.class, id);
            if (option.getIncOptions().contains(reqOption)){
                LOGGER.error("Option can't be merged with incompatible option");
                return null;
            }
            if (reqOption != null) reqOptions.add(reqOption);
        }
        option.setReqOptions(reqOptions);
        em.merge(option);
        return reqOptions;
    }
}
