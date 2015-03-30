package com.tsystems.javaschool.controllers;

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
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Date;

@Controller
public class OperatorsController {

    @Autowired
    private OperatorService operatorService;

    @RequestMapping(value = "/addClient", method = RequestMethod.POST)
    public String addClient(@RequestParam String name, @RequestParam String surname,
                          @RequestParam(value="birthday")
                          @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday, @RequestParam String address,
                          @RequestParam String email, @RequestParam String password,
                          @RequestParam Long passport, @RequestParam Long roleId){

        operatorService.addClient(name, surname, birthday, address, passport, email, password, roleId);
        return "operator/clients";
    }

    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public String addRole(@RequestParam String description){

        operatorService.addRole(description);
        return "operator/operator";
    }

    @RequestMapping(value = "/concludeContract", method = RequestMethod.POST)
    public String concludeContract(@RequestParam String name, @RequestParam String surname,
                            @RequestParam Long tariffId, @RequestParam Long number, Model model)
            throws WrongIdException {

        operatorService.concludeContract(name, surname, tariffId, number);
        return "operator/contracts";
    }

    @RequestMapping(value = "/generateNumber", method = RequestMethod.POST)
    public String generateNumber(Model model){

        Number number = operatorService.generateUniqueNumber();
        model.addAttribute("number", number);
        return "createContract";
    }

    @RequestMapping(value = "/dropContractOption", method = RequestMethod.POST)
    public String dropContractOption(@RequestParam Long contractId, @RequestParam Long optionId)
            throws WrongIdException, RequiredOptionException {

        operatorService.shutDownContractOption(contractId, optionId);
        return "operator/contracts";
    }

    @RequestMapping(value = "/getClients", method = RequestMethod.GET)
    public String getClients(Model model){

        model.addAttribute("clients", operatorService.getClients());
        return "operator/clients";
    }

    @RequestMapping(value = "/getContracts", method = RequestMethod.GET)
    public String getContracts(Model model){

        model.addAttribute("contracts", operatorService.getContracts());
        return "operator/contracts";
    }

    @RequestMapping(value = "/getTariffs", method = RequestMethod.GET)
    public String getTariffs(Model model){

        model.addAttribute("tariffs", operatorService.getTariffs());
        return "operator/tariffs";
    }

    @RequestMapping(value = "/lockContract", method = RequestMethod.POST)
    public String lockContract(@RequestParam Long number) throws WrongIdException {

        operatorService.lockNumber(number);
        return "operator/contracts";
    }

    @RequestMapping(value = "/unlockContract", method = RequestMethod.POST)
    public String unlockContract(@RequestParam Long number) throws WrongIdException {

        operatorService.unlockNumber(number);
        return "operator/contracts";
    }

    @RequestMapping(value = "/findClient", method = RequestMethod.GET)
    public String findClient(@RequestParam Long number){

        operatorService.find(number);
        return "operator/clients";
    }

    @RequestMapping(value = "/changeTariff", method = RequestMethod.POST)
    public String changeTariff(@RequestParam Long contractId, @RequestParam Long tariffId)
            throws WrongIdException {

        operatorService.changeTariff(contractId, tariffId);
        return "operator/contracts";
    }

    @RequestMapping(value = "/addTariff", method = RequestMethod.POST)
    public String addTariff(@RequestParam String name, @RequestParam Long[] optionsId)
            throws WrongIdException {

        operatorService.addTariff(name, optionsId);
        return "operator/tariffs";
    }

    @RequestMapping(value = "/dropTariff", method = RequestMethod.POST)
    public String dropTariff(@RequestParam Long tariffId) throws WrongIdException {

        operatorService.dropTariff(tariffId);
        return "operator/tariffs";
    }

    @RequestMapping(value = "/addOption", method = RequestMethod.POST)
    public String addOption(@RequestParam String name, @RequestParam BigDecimal optionPrice,
                            @RequestParam BigDecimal connectionPrice){

        operatorService.addOption(name, optionPrice, connectionPrice);
        return "operator/options";
    }

    @RequestMapping(value = "/dropTariffOption", method = RequestMethod.POST)
    public String dropTariffOption(@RequestParam Long tariffId, @RequestParam Long optionId)
            throws WrongIdException {

        operatorService.dropOption(tariffId, optionId);
        return "operator/tariffs";
    }

    @RequestMapping(value = "/setIncOpt", method = RequestMethod.POST)
    public String setIncompatibleOptions(@RequestParam Long baseOptionId,
                                         @RequestParam Long[] incOptionsId)
            throws IncompatibleOptionException {

        operatorService.setIncompatibleOptions(baseOptionId, incOptionsId);
        return "operator/options";
    }

    @RequestMapping(value = "/setReqOpt", method = RequestMethod.POST)
    public String setRequiredOptions(@RequestParam Long baseOptionId,
                                     @RequestParam Long[] reqOptionsId)
            throws RequiredOptionException {

        operatorService.setRequiredOptions(baseOptionId, reqOptionsId);
        return "operator/options";
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

    @RequestMapping(value = "/getAddRolePage", method = RequestMethod.GET)
    public String getAddRolePage(){
        return "operator/addRolePage";
    }

    @RequestMapping(value = "/getAddTariffPage", method = RequestMethod.GET)
    public String getAddTariffPage(){
        return "operator/addTariffPage";
    }

    @RequestMapping(value = "/getAddOptionPage", method = RequestMethod.GET)
    public String getAddOptionPage(){
        return "operator/addOptionPage";
    }

    @RequestMapping(value = "/getAddIncOptionsPage", method = RequestMethod.GET)
    public String getAddIncOptionsPage(){
        return "operator/addIncOptionsPage";
    }

    @RequestMapping(value = "/getAddReqOptionsPage", method = RequestMethod.GET)
    public String getAddReqOptionsPage(){
        return "operator/addReqOptionsPage";
    }

    @RequestMapping(value = "/getAddContractPage", method = RequestMethod.GET)
    public String getAddContractPage(){
        return "operator/addContractPage";
    }



}
