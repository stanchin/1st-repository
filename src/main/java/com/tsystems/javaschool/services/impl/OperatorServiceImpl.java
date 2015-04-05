package com.tsystems.javaschool.services.impl;


import com.tsystems.javaschool.dao.impl.*;
import com.tsystems.javaschool.dto.ClientNumberDTO;
import com.tsystems.javaschool.entities.*;
import com.tsystems.javaschool.entities.Number;
import com.tsystems.javaschool.exceptions.IncompatibleOptionException;
import com.tsystems.javaschool.exceptions.RequiredOptionException;
import com.tsystems.javaschool.exceptions.WrongIdException;
import com.tsystems.javaschool.services.OperatorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
/**
 * Implementation of OperatorService interface. All methods transactional.
 */
@Service
@Transactional
public class OperatorServiceImpl implements OperatorService {

    private static final Logger LOGGER = Logger.getLogger(OperatorServiceImpl.class);

    @Autowired
    private ClientDao clientDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ContractDao contractDao;
    @Autowired
    private NumberDao numberDao;
    @Autowired
    private TariffDao tariffDao;
    @Autowired
    private OptionDao optionDao;

    @Override
    public void addClient(String name, String surname, Date birthday, String address, long passport, String email,
                          String password, long roleId) {
        LOGGER.debug("Creating new client");
        Client client = new Client();
        client.setName(name);
        client.setSurname(surname);
        client.setBirthday(birthday);
        client.setAddress(address);
        client.setPassport(passport);
        client.setEmail(email);
        client.setPassword(password);
        Role role = roleDao.getById(roleId);
        client.setRole(role);
        clientDao.create(client);
    }

    @Override
    public void concludeContract(String name, String surname, long tariffId, long number) throws WrongIdException {
        LOGGER.debug("Concluding contract");
        Contract contract = new Contract();
        Client client;
        try {
            client = clientDao.findByNameSurname(name, surname);
        } catch (Exception e) {
            throw new WrongIdException("Can't find client.");
        }
        contract.setClient(client);
        try {
            setTariff(contract, tariffId);
        } catch (WrongIdException e) {
            throw new WrongIdException("Can't find tariff.");
        }
        setNumber(number, contract);
        contractDao.create(contract);
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
            Number findNumber = numberDao.getByNumber(telNum);
            if (findNumber == null){
                number.setNumber(telNum);
                numberDao.create(number);
                break;
            }
        }
        return number;
    }

    @Override
    public void setNumber(long num, Contract contract) {
        LOGGER.debug("Setting number to contract");
        Number number = numberDao.getByNumber(num);
        contract.setNumber(number);
        number.setContract(contract);
        numberDao.update(number);
    }

    @Override
    public void setTariff(Contract contract, long tariffId) throws WrongIdException {
        LOGGER.debug("Setting tariff");
        Tariff tariff = tariffDao.getById(tariffId);
        if (tariff != null) {
            contract.setTariff(tariff);
        } else {
            throw new WrongIdException("Tariff with id = " + tariffId + " doesn't exist.");
        }
    }

    @Override
    public void setOptions(long contractId, long... optionsId) throws WrongIdException {

        LOGGER.debug("Setting options");
        Contract contract = contractDao.getById(contractId);
        if (contract == null) throw new WrongIdException("Contract with id = " + contractId + " doesn't exist.");

        List<Option> options = new ArrayList<>();
        List<Option> contractOptions = contract.getOptions();

        for (long id : optionsId){
            Option option = optionDao.getById(id);
            options.add(option);
        }

        contractOptions.addAll(options);
        contract.setOptions(contractOptions);
        contractDao.update(contract);
    }

    @Override
    public void shutDownContractOption(long contractId, long optionId)
            throws RequiredOptionException, WrongIdException {
        LOGGER.debug("Removing option");
        Contract contract = contractDao.getById(contractId);
        if (contract == null) throw new WrongIdException("Contract with id = " + contractId + " doesn't exist.");

        List<Option> contractOptions = contract.getOptions();

        Option option = optionDao.getById(optionId);
        List<Option> options = option.getReqOptions();

        contractOptions.removeAll(options);
        contractOptions.remove(option);

        contract.setOptions(contractOptions);
        contractDao.update(contract);
    }

    @Override
    public List<Client> getClients() {
        return clientDao.getAll();
    }

    @Override
    public List<Contract> getContracts() {
        return contractDao.getAll();
    }

    @Override
    public List<Tariff> getTariffs() {
        return tariffDao.getAll();
    }

    @Override
    public void lockNumber(long number) throws WrongIdException {
        LOGGER.debug("Blocking client");
        Contract contract = contractDao.findByNumber(number);
        if (contract == null) throw new WrongIdException(
                "Contract with number = " + number + " doesn't exists.");
        contract.setBlockedByOperator(true);
        contractDao.update(contract);
    }

    @Override
    public void unlockNumber(long number) throws WrongIdException {
        LOGGER.debug("Deploying client");
        Contract contract = contractDao.findByNumber(number);
        if (contract == null) throw new WrongIdException("Contract with number = " + number + " doesn't exists.");
        contract.setBlockedByOperator(false);
        contractDao.update(contract);
    }

    @Override
    public Client find(long number) {
        LOGGER.debug("Searching client");
        return clientDao.findByNumber(number);
    }

    @Override
    public void changeTariff(long contractId, long tariffId) throws WrongIdException {
        LOGGER.debug("Changing tariff");
        Contract contract = contractDao.getById(contractId);
        if (contract == null) throw new WrongIdException("Contract with id = " + contractId + " doesn't exist.");
        Tariff tariff = tariffDao.getById(tariffId);
        if (tariff == null) throw new WrongIdException("Tariff with id = " + tariffId + " doesn't exist.");

        contract.setTariff(tariff);
        contractDao.update(contract);
    }

    @Override
    public void addTariff(String name, Long[] optionsId) throws WrongIdException {
        LOGGER.debug("Adding tariff");
        Tariff tariff = new Tariff();
        tariff.setName(name);

        Double price = 0d;
        List<Option> options = new ArrayList<Option>();
        for (long id : optionsId){
            Option option = optionDao.getById(id);
            if (option != null) {
                price += option.getConnectionPrice().doubleValue();
                price += option.getOptionPrice().doubleValue();
                options.add(option);
            } else throw new WrongIdException("Option with id = " + id + " doesn't exist.");
        }
        tariff.setPrice(new BigDecimal(price));
        tariff.setOptions(options);
        tariffDao.create(tariff);
    }

    @Override
    public void dropTariff(long tariffId) throws WrongIdException {
        LOGGER.debug("Deleting tariff");
        Tariff tariff = tariffDao.getById(tariffId);
        if (tariff == null) throw new WrongIdException("Tariff with id = " + tariffId + " doesn't exist.");
        tariffDao.remove(tariff);
    }

    @Override
    public void addOption(String name, BigDecimal optionPrice, BigDecimal connectionPrice) {
        LOGGER.debug("Creating new option");
        Option option = new Option();
        option.setName(name);
        option.setOptionPrice(optionPrice);
        option.setConnectionPrice(connectionPrice);
        Option option1 = optionDao.create(option);
        System.out.println("created");
        System.out.println(option1);
    }

    @Override
    public void dropOption(long tariffId, long optionId) throws WrongIdException {
        LOGGER.debug("Drop option");
        Tariff tariff = tariffDao.getById(tariffId);
        if (tariff == null) throw new WrongIdException("Tariff with id = " + tariffId + " doesn't exist.");
        Option option = optionDao.getById(optionId);
        if (option == null) throw new WrongIdException("Option with id = " + optionId + " doesn't exist.");
        List<Option> reqOptions = option.getReqOptions();

        tariff.getOptions().remove(option);
        tariff.getOptions().removeAll(reqOptions);
        List<Option> tariffOptions = tariff.getOptions();

        double tariffPrice = 0d;

        for (Option o : tariffOptions){
            tariffPrice += o.getOptionPrice().doubleValue();
            tariffPrice += o.getConnectionPrice().doubleValue();
        }

        tariff.setPrice(new BigDecimal(tariffPrice));
        tariffDao.update(tariff);
    }

    @Override
    public List<Option> setIncompatibleOptions(long optionId, long[] optionsId) throws IncompatibleOptionException {
        LOGGER.debug("Setting incompatible options");
        Option option = optionDao.getById(optionId);
        List<Option> incOptions = new ArrayList<Option>();
        for (long id : optionsId){
            Option incOption = optionDao.getById(id);
            if (option.getReqOptions().contains(incOption)){
                LOGGER.error("Option can't be merged with required option");
                throw new IncompatibleOptionException("Option can't be merged with required option");
            }
            if (!option.getIncOptions().contains(incOption)) {
                incOptions.add(incOption);
                incOption.getIncOptions().add(option);
                optionDao.update(incOption);
            }
        }
        option.getIncOptions().addAll(incOptions);
        optionDao.update(option);
        return incOptions;
    }

    @Override
    public List<Option> setRequiredOptions(long optionId, long[] optionsId) throws RequiredOptionException {
        LOGGER.debug("Setting required options");
        Option option = optionDao.getById(optionId);
        List<Option> reqOptions = new ArrayList<Option>();
        for (long id : optionsId){
            Option reqOption = optionDao.getById(id);
            if (option.getIncOptions().contains(reqOption)){
                LOGGER.error("Option can't be merged with incompatible option");
                throw new RequiredOptionException("Option can't be merged with incompatible option");
            }
            if (!option.getReqOptions().contains(reqOption)){
                reqOptions.add(reqOption);
                reqOption.getReqOptions().add(option);
                optionDao.update(reqOption);
            }
        }
        option.getReqOptions().addAll(reqOptions);
        optionDao.update(option);
        return reqOptions;
    }

    @Override
    public List<Option> getOptions() {
        LOGGER.debug("Getting options");
        return optionDao.getAll();
    }

    @Override
    public Contract getContract(Long contractId) {
        return contractDao.getById(contractId);
    }

    @Override
    public Tariff getTariff(Long tariffId) {
        return tariffDao.getById(tariffId);
    }

    @Override
    public List<Option> getRequiredOptions(long optionId) {
        Option option = optionDao.getById(optionId);
        return option.getReqOptions();
    }

    @Override
    public List<Option> getIncompatibleOptions(long optionId) {
        Option option = optionDao.getById(optionId);
        return option.getIncOptions();
    }

    @Override
    public List<ClientNumberDTO> getClientsByTariff(long tariffId) {
        List<Contract> contracts = contractDao.getClientsByTariff(tariffId);
        List<ClientNumberDTO> clientNumbersDTOs = new ArrayList<>();
        for (Contract c : contracts){
            ClientNumberDTO clientNumberDTO = new ClientNumberDTO();
            clientNumberDTO.setClientName(c.getClient().getName());
            clientNumberDTO.setClientSurname(c.getClient().getSurname());
            clientNumberDTO.setClientEmail(c.getClient().getEmail());
            clientNumberDTO.setClientNumber(c.getNumber().getNumber());
            clientNumberDTO.setClientPassport(c.getClient().getPassport());
            clientNumbersDTOs.add(clientNumberDTO);
        }
        return clientNumbersDTOs;
    }
}
