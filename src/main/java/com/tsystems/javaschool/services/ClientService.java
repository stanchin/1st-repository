package com.tsystems.javaschool.services;


import com.tsystems.javaschool.entities.*;
import com.tsystems.javaschool.exceptions.IncompatibleOptionException;
import com.tsystems.javaschool.exceptions.RequiredOptionException;
import com.tsystems.javaschool.exceptions.WrongIdException;

import java.util.List;
/*
* Interface that contains methods of business logic, which client of company
* can use, when working with program.
* */
public interface ClientService {

    /**
     * Returns the Client entity with presented email and password fields.
     * @param email the email of the user
     * @param password the password of the user
     *                 @return Client object
     */
    public Client getClient (String email, String password);

    /**
     * Returns all client contracts represented in database.
     * @param clientId client's id in the database
     *                 @return list of contract objects
     *                 @throws com.tsystems.javaschool.exceptions.WrongIdException
     */
    public List<Contract> getContracts(long clientId) throws WrongIdException;

    /**
     * Returns all tariffs represented in database.
     * @return list of tariff objects
     */
    public List<Tariff> getTariffs();

    /**
     * Returns tariff from database by tariff's id.
     * @param tariffId the id of necessary column in tariffs table
     *                 @return Tariff object
     */
    public Tariff getTariff(long tariffId);

    /**
     * Returns contract from database by contract's id.
     * @param contractId the id of necessary column in contracts table
     *                   @return Contract object
     */
    public Contract getContract(long contractId);

    /**
     * Returns option from database by option's id.
     * @param optionId the id of necessary column in options table
     *                 @return Option object
     */
    public Option getOption(long optionId);

    /**
     * Returns tariff included options.
     * @param tariffId the id of necessary tariff
     *                 @throws com.tsystems.javaschool.exceptions.WrongIdException
     *                 @return list of all options
     */
    public List<Option> getTariffOptions(long tariffId) throws WrongIdException;

    /**
     * Changes the contract's tariff.
     * @param tariffId the id of new tariff chosen
     *                 @param contractId the id of contract client have
     *                                   @throws com.tsystems.javaschool.exceptions.WrongIdException
     */
    public void changeTariff(long contractId, long tariffId) throws WrongIdException;

    /**
     * Set extra options to clients' contract.
     * @param contractId the id of necessary contract
     *                   @param optionsId array of options to include
     *                   @throws com.tsystems.javaschool.exceptions.WrongIdException
     *                   @throws com.tsystems.javaschool.exceptions.IncompatibleOptionException
     *                   @throws com.tsystems.javaschool.exceptions.RequiredOptionException
     */
    public void setOptions(long contractId, long... optionsId) throws WrongIdException, IncompatibleOptionException, RequiredOptionException;

    /**
     * Removes the extra contract option.
     * @param contractId the id of necessary contract
     *                   @param optionId the id of option to remove
     *                                   @throws com.tsystems.javaschool.exceptions.WrongIdException
     */
    public void removeOption(long contractId, long optionId) throws WrongIdException;

    /**
     * Locks the telephone number of client.
     * @param contractId the id of necessary contract
     *                   @throws com.tsystems.javaschool.exceptions.WrongIdException
     */
    public void lockNumber(long contractId) throws WrongIdException;

    /**
     * Unlocks the telephone number of client.
     * @param contractId the id of necessary contract
     *                   @throws com.tsystems.javaschool.exceptions.WrongIdException
     */
    public void unlockNumber(long contractId) throws WrongIdException;

    /**
     * Returns the role for user.
     * @param authority granted authority on session
     *                  @return Role object
     */
    public Role getRole(String authority);
}
