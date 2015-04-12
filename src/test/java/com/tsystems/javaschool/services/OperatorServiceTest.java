package com.tsystems.javaschool.services;

import com.tsystems.javaschool.dao.impl.ClientDao;
import com.tsystems.javaschool.dao.impl.ContractDao;
import com.tsystems.javaschool.dao.impl.NumberDao;
import com.tsystems.javaschool.dao.impl.TariffDao;
import com.tsystems.javaschool.entities.*;
import com.tsystems.javaschool.entities.Number;
import com.tsystems.javaschool.exceptions.WrongIdException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/spring-mvc.xml"})
public class OperatorServiceTest {

    @Autowired
    OperatorService operatorService;

    @Autowired
    ContractDao contractDao;

    @Autowired
    NumberDao numberDao;

    @Autowired
    ClientDao clientDao;

    @Autowired
    TariffDao tariffDao;

    /**
     * Tests the contract's concluding method with a good developments.
     */
    @Test
    @Transactional
    @Rollback
    public void concludeContractTest() throws WrongIdException {
        Client client = clientDao.findByNameSurname("John", "Smith");
        Tariff tariff = tariffDao.getById(11L);
        Number number = operatorService.generateUniqueNumber();
        operatorService.concludeContract(client.getName(), client.getSurname(), tariff.getId(), number.getNumber());
        Contract contract = contractDao.findByNumber(number.getNumber());

        Assert.assertNotNull(contract);
        Assert.assertEquals(client, contract.getClient());
        Assert.assertEquals(tariff, contract.getTariff());
    }

    /**
     * Tests the contract's concluding method with a bad developments, actually when client is not found.
     */
    @Test(expected = WrongIdException.class)
    @Transactional
    @Rollback
    public void concludeContractTestWithInvalidClient() throws WrongIdException {
        Tariff tariff = tariffDao.getById(11L);
        Number number = operatorService.generateUniqueNumber();
        operatorService.concludeContract("John", "B", tariff.getId(), number.getNumber());
    }

    /**
     * Tests the contract's concluding method with a bad developments, actually when tariff is not found.
     */
    @Test(expected = WrongIdException.class)
    @Transactional
    @Rollback
    public void concludeContractTestWithInvalidTariffId() throws WrongIdException {
        Client client = clientDao.findByNameSurname("John", "Smith");
        Number number = operatorService.generateUniqueNumber();
        operatorService.concludeContract(client.getName(), client.getSurname(), 1L, number.getNumber());
    }



}
