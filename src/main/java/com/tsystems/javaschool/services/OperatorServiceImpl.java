package com.tsystems.javaschool.services;


import com.tsystems.javaschool.entities.*;
import com.tsystems.javaschool.entities.Number;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class OperatorServiceImpl implements OperatorService{
    private static final Logger LOGGER = Logger.getLogger(OperatorServiceImpl.class);
    EntityManager em;

    public OperatorServiceImpl(EntityManager em){
        LOGGER.info("Creating operator service");
        this.em = em;
    }

    @Override
    public void addClient(String name, String surname, Date birthday, String address, Long passport, String email,
                          String password, int roleId) {
        LOGGER.info("Creating new client");
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
        Role role = new Role();
        role.setRole(desc);
        em.persist(role);
    }

    @Override
    public void concludeContract(Client client, int tariffId, long number) {
        LOGGER.info("Concluding contract");
        Contract contract = new Contract();
        contract.setClient(client);
        em.persist(contract);
        setTariff(contract, tariffId);
        setNumber(number, contract);
        em.merge(contract);
    }

    @Override
    public Number generateUniqueNumber() {
        LOGGER.info("Generating unique number");
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
        LOGGER.info("Setting number to contract");
        Number number = em.find(Number.class, num);
        contract.setNumber(number);
        number.setContract(contract);
        em.merge(contract);
        em.merge(number);
    }

    @Override
    public void setTariff(Contract contract, int tariffId) {
        Tariff tariff = em.find(Tariff.class, tariffId);
        contract.setTariff(tariff);
        em.merge(contract);
    }

    @Override
    public void setOptions(int contractId, Integer... optionsId) {

    }

    @Override
    public void shutDownContractOption(int contractId, int optionId) {

    }

    @Override
    public List<Client> getClients() {
        return null;
    }

    @Override
    public List<Contract> getContracts() {
        return null;
    }

    @Override
    public List<Tariff> getTariffs() {
        return null;
    }

    @Override
    public void blockClient(int clientId) {

    }

    @Override
    public void deployClient(int clientId) {

    }

    @Override
    public Client find(int number) {
        return null;
    }

    @Override
    public void changeTariff(int contractId, List<Tariff> tariffs) {

    }

    @Override
    public void addTariff(String name, Integer...optionsId) {
        LOGGER.info("Adding tariff");
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
        LOGGER.info("Deleting tariff");
        Tariff tariff = em.find(Tariff.class, tariffId);
        em.remove(tariff);
    }

    @Override
    public void addOption(String name, BigDecimal optionPrice, BigDecimal connectionPrice) {
        LOGGER.info("Creating new option");
        Option option = new Option();
        option.setName(name);
        option.setOptionPrice(optionPrice);
        option.setConnectionPrice(connectionPrice);
        em.persist(option);
    }

    @Override
    public void dropOption(int tariffId, int optionId) {
        LOGGER.info("Drop option");
        Tariff tariff = em.find(Tariff.class, tariffId);
        Option option = em.find(Option.class, optionId);
        tariff.getOptions().remove(option);
        em.merge(tariff);
    }

    @Override
    public List<Option> setIncompatibleOptions(int optionId, Integer... optionsId) {
        LOGGER.info("Setting incompatible options");
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
        LOGGER.info("Setting required options");
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
