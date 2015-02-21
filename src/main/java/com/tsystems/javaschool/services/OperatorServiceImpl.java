package com.tsystems.javaschool.services;


import com.tsystems.javaschool.entities.*;
import com.tsystems.javaschool.entities.Number;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
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
    public void concludeContract(Client client, Tariff tariff) {

    }

    @Override
    public Number generateUniqueNumber() {
        Number number = new Number();
        Random random = new Random(47);

        while (true) {
            int code = random.nextInt(9) + 900;
            int num = 1000000 + random.nextInt(9000000);
            int telNum = Integer.parseInt(code + "" + num);
            Number findNumber = em.find(Number.class, telNum);
            if (findNumber == null){
                number.setNumber(telNum);
                break;
            }
        }
        return number;
    }

    @Override
    public void setNumber(Number number, int contractId, int clientId) {

    }

    @Override
    public void setTariff(int contractId, int tariffId) {

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
    public void addTariff() {

    }

    @Override
    public void dropTariff(int tariffId) {

    }

    @Override
    public void addOption(int tariffId, int optionId) {

    }

    @Override
    public void dropOption(int tariffId, int optionId) {

    }

    @Override
    public void setIncompatibleOptions(int optionId, Integer... optionsId) {

    }

    @Override
    public void setRequiredOptions(int optionId, Integer... optionsId) {

    }
}
