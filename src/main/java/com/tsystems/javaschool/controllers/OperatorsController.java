package com.tsystems.javaschool.controllers;

import com.tsystems.javaschool.entities.*;
import com.tsystems.javaschool.entities.Number;
import com.tsystems.javaschool.exceptions.IncompatibleOptionException;
import com.tsystems.javaschool.exceptions.RequiredOptionException;
import com.tsystems.javaschool.exceptions.WrongIdException;
import com.tsystems.javaschool.services.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;

/**
 * The operator controller, which allows administrator(operator) to view information on pages,
 * located in '/WEB-INF/pages/operator' folder.
 */
@Controller
public class OperatorsController {

    @Autowired
    private OperatorService operatorService;

    @RequestMapping(value = "/addClient", method = RequestMethod.POST)
    public String addClient(@RequestParam String name, @RequestParam String surname,
                          @RequestParam(value="birthday")
                          @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday, @RequestParam String address,
                          @RequestParam String email, @RequestParam String password,
                          @RequestParam long passport, @RequestParam long roleId, Model model){

        operatorService.addClient(name, surname, birthday, address, passport, email, password, roleId);
        model.addAttribute("success", "Client added");
        return "operator/operator";
    }

    @RequestMapping(value = "/concludeContract", method = RequestMethod.POST)
    public String concludeContract(@RequestParam String name, @RequestParam String surname,
                            @RequestParam long tariffId, @RequestParam long number, Model model)
            throws WrongIdException {

        operatorService.concludeContract(name, surname, tariffId, number);
        model.addAttribute("success", "Contract concluded");
        return "operator/operator";
    }

    @RequestMapping(value = "/generateNumber", method = RequestMethod.POST)
    public @ResponseBody String generateNumber(){

        Number number = operatorService.generateUniqueNumber();
        return String.valueOf(number.getNumber());
    }

    @RequestMapping(value = "/dropContractOption", method = RequestMethod.POST)
    public String dropContractOption(@RequestParam long contractId, @RequestParam long optionId, Model model)
            throws WrongIdException, RequiredOptionException {

        operatorService.shutDownContractOption(contractId, optionId);
        model.addAttribute("success", "Option deleted");
        return "operator/operator";
    }

    @RequestMapping(value = "/getClients", method = RequestMethod.GET)
    public String getClients(Model model){

        List<Client> clients = operatorService.getClients();
        List<Client> checkedClients = new ArrayList<>();
        for (Client client : clients){
            if ("client".equals(client.getRole().getRole())) {
                checkedClients.add(client);
            }
        }
        model.addAttribute("clients", checkedClients);
        return "operator/clients";
    }

    @RequestMapping(value = "/getContracts", method = RequestMethod.GET)
    public String getContracts(Model model){

        model.addAttribute("contracts", operatorService.getContracts());
        return "operator/contracts";
    }

    @RequestMapping(value = "/getTariffs", method = RequestMethod.GET)
    public String getTariffs(Model model){

        model.addAttribute("tariffs", operatorService.getTariffDTOs());
        return "operator/tariffs";
    }

    @RequestMapping(value = "/lockContract", method = RequestMethod.POST)
    public String lockContract(@RequestParam long number, Model model) throws WrongIdException {

        operatorService.lockNumber(number);
        model.addAttribute("success", "Contract locked");
        return "operator/operator";
    }

    @RequestMapping(value = "/unlockContract", method = RequestMethod.POST)
    public String unlockContract(@RequestParam long number, Model model) throws WrongIdException {

        operatorService.unlockNumber(number);
        model.addAttribute("success", "Contract unlocked");
        return "operator/operator";
    }

    @RequestMapping(value = "/findClient", method = RequestMethod.GET)
    public String findClient(@RequestParam long number, Model model){

        List<Client> clients = new ArrayList<>();

        clients.add(operatorService.find(number));
        model.addAttribute("clients", clients);
        return "operator/clients";
    }

    @RequestMapping(value = "/changeTariff", method = RequestMethod.POST)
    public String changeTariff(@RequestParam long contractId, @RequestParam long tariffId, Model model)
            throws WrongIdException {

        operatorService.changeTariff(contractId, tariffId);
        model.addAttribute("success", "Tariff has been changed");
        return "operator/operator";
    }

    @RequestMapping(value = "/addTariff", method = RequestMethod.POST)
    public String addTariff(Model model, @RequestParam String name, @RequestParam long... optionsId )
            throws WrongIdException {

        operatorService.addTariff(name, optionsId);
        model.addAttribute("success", "Tariff created");
        return "operator/operator";
    }

    @RequestMapping(value = "/dropTariff", method = RequestMethod.POST)
    public String dropTariff(@RequestParam long tariffId, Model model) throws WrongIdException {

        operatorService.dropTariff(tariffId);
        model.addAttribute("success", "Tariff deleted");
        return "operator/operator";
    }

    @RequestMapping(value = "/addOption", method = RequestMethod.POST)
    public String addOption(@RequestParam String name, @RequestParam BigDecimal optionPrice,
                            @RequestParam BigDecimal connectionPrice, Model model){

        operatorService.addOption(name, optionPrice, connectionPrice);
        model.addAttribute("success", "Option created");
        return "operator/operator";
    }

    @RequestMapping(value = "/dropTariffOption", method = RequestMethod.POST)
    public String dropTariffOption(@RequestParam long tariffId, @RequestParam long optionId,
                                    Model model) throws WrongIdException {

        operatorService.dropOption(tariffId, optionId);
        model.addAttribute("success", "Option deleted from tariff");
        return "operator/operator";
    }

    @RequestMapping(value = "/setIncOpt", method = RequestMethod.POST)
    public String setIncompatibleOptions(Model model, @RequestParam long baseOptionId,
                                         @RequestParam long...incOptionsId)
            throws IncompatibleOptionException {

        operatorService.setIncompatibleOptions(baseOptionId, incOptionsId);
        model.addAttribute("success", "Incompatible options added on");
        return "operator/operator";
    }

    @RequestMapping(value = "/setReqOpt", method = RequestMethod.POST)
    public String setRequiredOptions(Model model, @RequestParam long baseOptionId,
                                     @RequestParam long... reqOptionsId)
            throws RequiredOptionException {

        operatorService.setRequiredOptions(baseOptionId, reqOptionsId);
        model.addAttribute("success", "Required options added on");
        return "operator/operator";
    }

    @RequestMapping(value = "/getOptions", method = RequestMethod.GET)
    public String getOptions(Model model){

        model.addAttribute("options", operatorService.getOptions());
        return "operator/options";
    }

    @RequestMapping(value = "/goToAdminPage", method = RequestMethod.GET)
    public String goToAdminPage(){
        return "operator/operator";
    }

    @RequestMapping(value = "/getAddClientPage", method = RequestMethod.GET)
    public String getAddClientPage(){
        return "operator/addClientPage";
    }

    @RequestMapping(value = "/getAddTariffPage", method = RequestMethod.GET)
    public String getAddTariffPage(Model model){
        model.addAttribute("options", operatorService.getOptions());
        return "operator/addTariffPage";
    }

    @RequestMapping(value = "/getAddOptionPage", method = RequestMethod.GET)
    public String getAddOptionPage(){
        return "operator/addOptionPage";
    }

    @RequestMapping(value = "/getAddIncOptionsPage", method = RequestMethod.GET)
    public String getAddIncOptionsPage(Model model){
        List<Option> optionList = operatorService.getOptions();
        model.addAttribute("options", optionList);
        return "operator/addIncOptionsPage";
    }

    @RequestMapping(value = "/getAddReqOptionsPage", method = RequestMethod.GET)
    public String getAddReqOptionsPage(Model model){
        List<Option> optionList = operatorService.getOptions();
        model.addAttribute("options", optionList);
        return "operator/addReqOptionsPage";
    }

    @RequestMapping(value = "/getAddContractPage", method = RequestMethod.GET)
    public String getAddContractPage(@RequestParam String name,
                                     @RequestParam String surname,
                                     Model model){

        List<Tariff> tariffList = operatorService.getTariffs();
        model.addAttribute("tariffs", tariffList);
        model.addAttribute("name", name);
        model.addAttribute("surname", surname);

        return "operator/addContractPage";
    }

    @RequestMapping(value = "/getAddExtraOptionsPage", method = RequestMethod.GET)
    public String getAddExtraOptionsPage(@RequestParam long contractId,@RequestParam long tariffId,
                                         @RequestParam String number,
                                         Model model){

        Tariff tariff = operatorService.getTariff(tariffId);
        Contract contract = operatorService.getContract(contractId);

        List<Option> optionList = operatorService.getOptions();
        List<Option> tariffOptions = tariff.getOptions();
        List<Option> contractOptions = contract.getOptions();
        Set<Option> incTariffOptionsSet = new HashSet<>();
        Set<Option> incContractOptionsSet = new HashSet<>();

        for (Option o : tariffOptions){
            incTariffOptionsSet.addAll(o.getIncOptions());
        }

        for (Option o : contractOptions){
            incContractOptionsSet.addAll(o.getIncOptions());
        }

        optionList.removeAll(tariffOptions);
        optionList.removeAll(incTariffOptionsSet);
        optionList.removeAll(contractOptions);
        optionList.removeAll(incContractOptionsSet);

        model.addAttribute("options", optionList);
        model.addAttribute("contractId", contractId);
        model.addAttribute("number", number);

        return "operator/addExtraOptionsPage";
    }

    @RequestMapping(value = "/getContractOptionsPage", method = RequestMethod.GET)
    public String getContractOptionsPage(@RequestParam long contractId, Model model){

        Contract contract = operatorService.getContract(contractId);
        model.addAttribute("contractOptions", contract.getOptions());
        model.addAttribute("contractId", contractId);

        return "operator/contractOptionsPage";
    }

    @RequestMapping(value = "/addContractOptionByAdmin", method = RequestMethod.POST)
    public String addContractOption(@RequestParam long contractId, @RequestParam long[] optionsId, Model model)
            throws WrongIdException, IncompatibleOptionException, RequiredOptionException {

        operatorService.setOptions(contractId, optionsId);
        model.addAttribute("success", "Option has been added on");
        return "operator/operator";
    }

    @RequestMapping(value = "/getTariffOptionsByAdmin", method = RequestMethod.GET)
    public String getTariffOptions(@RequestParam long tariffId, Model model){
        Tariff tariff = operatorService.getTariff(tariffId);
        model.addAttribute("options", tariff.getOptions());
        model.addAttribute("tariffName", tariff.getName());
        model.addAttribute("tariffId", tariff.getId());
        return "operator/tariffOptions";
    }

    @RequestMapping(value = "/getRequiredOptions", method = RequestMethod.GET)
    public String getRequiredOptions(@RequestParam long optionId, Model model){
        List<Option> reqOptions = operatorService.getRequiredOptions(optionId);
        model.addAttribute("reqOptions", reqOptions);
        return "operator/requiredOptions";
    }

    @RequestMapping(value = "/getIncompatibleOptions", method = RequestMethod.GET)
    public String getIncompatibleOptions(@RequestParam long optionId, Model model){
        List<Option> incOptions = operatorService.getIncompatibleOptions(optionId);
        model.addAttribute("incOptions", incOptions);
        return "operator/incompatibleOptions";
    }

    @RequestMapping(value = "/getChangeContractTariffFormByAdmin", method = RequestMethod.GET)
    public String getChangeContractTariffForm(@RequestParam long contractId, Model model){

        Contract contract = operatorService.getContract(contractId);
        List<Tariff> tariffs = operatorService.getTariffs();
        model.addAttribute("contract", contract);
        model.addAttribute("tariffs", tariffs);
        return "operator/changeContractTariffPage";
    }
}
