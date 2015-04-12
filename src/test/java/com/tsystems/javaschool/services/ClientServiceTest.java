package com.tsystems.javaschool.services;


import com.tsystems.javaschool.dao.impl.ContractDao;
import com.tsystems.javaschool.dao.impl.OptionDao;
import com.tsystems.javaschool.entities.Contract;
import com.tsystems.javaschool.entities.Option;
import com.tsystems.javaschool.exceptions.IncompatibleOptionException;
import com.tsystems.javaschool.exceptions.RequiredOptionException;
import com.tsystems.javaschool.exceptions.WrongIdException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/spring-mvc.xml"})
public class ClientServiceTest {

    @Autowired
    ContractDao contractDao;

    @Autowired
    OptionDao optionDao;

    @Autowired
    ClientService clientService;

    @Autowired
    OperatorService operatorService;

    /**
     * Tests a good aspect of transactions when setting an options to contract.
     */
    @Test
    @Transactional
    @Rollback
    public void setOptionTestGood() throws WrongIdException, IncompatibleOptionException, RequiredOptionException {
        Contract contract = contractDao.getById(24L);

        Option option1 = optionDao.getById(16L);
        Option option2 = optionDao.getById(17L);
        Option option3 = optionDao.getById(18L);

        clientService.setOptions(contract.getId(), option1.getId(), option2.getId(), option3.getId());

        List<Option> optionList = new ArrayList<>();
        optionList.add(option1);
        optionList.add(option2);
        optionList.add(option3);
        contract.setOptions(optionList);

        Contract contract1 = contractDao.getById(24L);
        Assert.assertEquals(contract, contract1);
    }

    /**
     * Tests a bad aspect of transactions, actually when contract doesn't exists.
     */
    @Test(expected = WrongIdException.class)
    @Transactional
    @Rollback
    public void setOptionTestWhenContractNull()
            throws WrongIdException, IncompatibleOptionException, RequiredOptionException {

        Option option1 = optionDao.getById(16L);
        Option option2 = optionDao.getById(17L);
        Option option3 = optionDao.getById(18L);

        clientService.setOptions(10L, option1.getId(), option2.getId(), option3.getId());
    }

    /**
     * Tests a bad aspect of transactions, actually when any of options to add on doesn't exists.
     */
    @Test(expected = WrongIdException.class)
    @Transactional
    @Rollback
    public void setOptionWhenOptionNull()
            throws WrongIdException, IncompatibleOptionException, RequiredOptionException {
        Contract contract = contractDao.getById(24L);

        Option option1 = optionDao.getById(16L);
        Option option2 = optionDao.getById(17L);

        clientService.setOptions(contract.getId(), option1.getId(), option2.getId(), 1L);
    }

    /**
     * Tests a bad aspect of transactions, actually when any of options have an incompatible option in contract.
     */
    @Test(expected = IncompatibleOptionException.class)
    @Transactional
    @Rollback
    public void setOptionWhenOptionIncompatible()
            throws WrongIdException, IncompatibleOptionException, RequiredOptionException {
        Contract contract = contractDao.getById(24L);

        Option option1 = optionDao.getById(15L);
        Option option2 = optionDao.getById(12L);

        clientService.setOptions(contract.getId(), option1.getId(), option2.getId());
    }

    /**
     * Tests a bad aspect of transactions, actually when any of options doesn't have an required option in contract.
     */
    @Test(expected = RequiredOptionException.class)
    @Transactional
    @Rollback
    public void setOptionWhenRequiredOptionAbsents()
            throws WrongIdException, IncompatibleOptionException, RequiredOptionException {
        Contract contract = contractDao.getById(24L);

        Option option1 = optionDao.getById(16L);
        Option option2 = optionDao.getById(17L);

        operatorService.setRequiredOptions(option1.getId(), option2.getId());

        clientService.setOptions(contract.getId(), option1.getId());
    }
}
