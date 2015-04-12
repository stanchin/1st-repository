package com.tsystems.javaschool.services;


import com.tsystems.javaschool.dto.ClientNumberDTO;
import com.tsystems.javaschool.dto.TariffDTO;
import com.tsystems.javaschool.entities.*;
import com.tsystems.javaschool.entities.Number;
import com.tsystems.javaschool.exceptions.IncompatibleOptionException;
import com.tsystems.javaschool.exceptions.RequiredOptionException;
import com.tsystems.javaschool.exceptions.WrongIdException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 * Interface contains business logic methods, which operator can use
 * during working with system.
 */
public interface OperatorService {

    /**
     * Creates new client in database. All fields are required to fill.
     * @param name the name of new user
     * @param surname the surname of new user
     * @param birthday the date of birth of new user
     * @param address the address of new user
     * @param passport the serial and number of user's passport
     * @param email the email of new user. used in login form
     * @param password the password to access the profile of new user
     * @param roleId the role of user. Can be 1 - admin, 2 - client
     */
    void addClient(String name, String surname, Date birthday, String address, long passport, String email,
                          String password, long roleId);

    /**
     * Concludes new contract with chosen client. Write the data to database.
     * @param name the name of the client
     * @param surname the surname of the client
     * @param tariffId the id of necessary tariff
     * @param number the number, which will be use by client
     *               @throws com.tsystems.javaschool.exceptions.WrongIdException
     */
    void concludeContract(String name, String surname, long tariffId, long number) throws WrongIdException;

    /**
     * Generates new unique number, that uses when operator conclude contract.
     */
    Number generateUniqueNumber();

    /**
     * Sets the number to contract.
     * @param contract the contract for including number
     * @param num number
     */
    void setNumber(long num, Contract contract);

    /**
     * Sets the tariff to contract
     * @param contract the contract for including tariff
     * @param tariffId the id of tariff to be included
     *                 @throws com.tsystems.javaschool.exceptions.WrongIdException
     */
    void setTariff(Contract contract, long tariffId) throws WrongIdException;

    /**
     * Set the extra options to contract.
     * @param contractId the id of client's contract
     * @param optionsId an array of options to be included
     *                  @throws com.tsystems.javaschool.exceptions.WrongIdException
     *                  @throws com.tsystems.javaschool.exceptions.IncompatibleOptionException
     *                  @throws com.tsystems.javaschool.exceptions.RequiredOptionException
     */
    void setOptions(long contractId, long... optionsId)
            throws IncompatibleOptionException, WrongIdException, RequiredOptionException;

    /**
     * Removes the contract's extra options.
     * @param contractId the contract id to make changes
     * @param optionId an array of options to be removed
     *                 @throws com.tsystems.javaschool.exceptions.RequiredOptionException
     *                 @throws com.tsystems.javaschool.exceptions.WrongIdException
     */
    void shutDownContractOption(long contractId, long optionId) throws RequiredOptionException, WrongIdException;

    /**
     * Returns all clients registered in program.
     * @return list of Client objects
     */
    List<Client> getClients();

    /**
     * Returns all contracts concluded in program.
     * @return list of Contract objects
     */
    List<Contract> getContracts();

    /**
     * Returns all tariffs created in program without options.
     * @return list of TariffDTO objects
     */
    List<TariffDTO> getTariffDTOs();

    /**
     * Locks Client's contract.
     * @param number the number of client to lock
     *                 @throws com.tsystems.javaschool.exceptions.WrongIdException
     */
    void lockNumber(long number) throws WrongIdException;

    /**
     * Unlocks Client's contract
     * @param number the number of client to unlock
     *               @throws com.tsystems.javaschool.exceptions.WrongIdException
     */
    void unlockNumber(long number) throws WrongIdException;

    /**
     * Finds client by number and return it as result.
     * @param number the number for searching
     *               @return Client object
     */
    Client find(long number);

    /**
     * Changes tariff of chosen contract.
     * @param contractId the id of contract to be changed
     * @param tariffId the id of new tariff
     *                 @throws com.tsystems.javaschool.exceptions.WrongIdException
     */
    void changeTariff(long contractId, long tariffId) throws WrongIdException;

    /**
     * Creates new tariff with chosen options.
     * @param name the name of new tariff
     * @param optionsId an array of options to be included
     *                  @throws com.tsystems.javaschool.exceptions.WrongIdException
     */
    void addTariff(String name, long... optionsId) throws WrongIdException;

    /**
     * Removes tariff from program.
     * @param tariffId the id of tariff to be removed
     *                 @throws com.tsystems.javaschool.exceptions.WrongIdException
     */
    void dropTariff(long tariffId) throws WrongIdException;

    /**
     * Creates an option in program with unique name, option price and connection price.
     * @param name new unique option name
     * @param connectionPrice price of connection
     * @param optionPrice price of option
     */
    void addOption(String name, BigDecimal optionPrice, BigDecimal connectionPrice);

    /**
     * Removes an option from tariff.
     * @param tariffId the id of tariff to be changed
     * @param optionId the id of option to be removed
     *                 @throws com.tsystems.javaschool.exceptions.WrongIdException
     */
    void dropOption(long tariffId, long optionId) throws WrongIdException;

    /**
     * Sets incompatible options to base option.
     * @param optionId base option's id
     * @param optionsId an array of incompatible options to be included
     *                  @throws com.tsystems.javaschool.exceptions.IncompatibleOptionException
     *                  @return list of incompatible options
     */
    List<Option> setIncompatibleOptions(long optionId, long... optionsId) throws IncompatibleOptionException;

    /**
     * Sets incompatible options to base option.
     * @param optionId base option's id
     * @param optionsId an array of required options to be included
     *                  @throws com.tsystems.javaschool.exceptions.RequiredOptionException
     *                  @return list of required options
     */
    List<Option> setRequiredOptions(long optionId, long... optionsId) throws RequiredOptionException;

    /**
     * Returns all options using in program.
     * @return list of Option objects
     */
    List<Option> getOptions();

    /**
     * Returns contract.
     * @param contractId the id of contract to be returned
     *                   @return Contract object
     */
    Contract getContract(Long contractId);

    /**
     * Returns tariff.
     * @param tariffId the id of tariff to be returned
     *                 @return Tariff object
     */
    Tariff getTariff(Long tariffId);

    /**
     * Returns the required options of chosen option.
     * @param optionId the id of option which required options need to be returned
     *                 @return list of required Option objects
     */
    List<Option> getRequiredOptions(long optionId);

    /**
     * Returns the incompatible options of chosen option.
     * @param optionId the id of option which incompatible options need to be returned
     *                 @return list of incompatible Option objects
     */
    List<Option> getIncompatibleOptions(long optionId);

    /**
     * Returns all clients from program with their contract numbers.
     * @return list of Client objects
     */
    List<ClientNumberDTO> getClientsByTariff(long tariffId);

    /**
     * Returns all tariffs created in program.
     * @return list of Tariff objects
     */
    List<Tariff> getTariffs();
}
