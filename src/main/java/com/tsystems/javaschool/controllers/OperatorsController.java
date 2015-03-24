package com.tsystems.javaschool.controllers;

import com.tsystems.javaschool.entities.Number;
import com.tsystems.javaschool.exceptions.IncompatibleOptionException;
import com.tsystems.javaschool.exceptions.RequiredOptionException;
import com.tsystems.javaschool.exceptions.WrongIdException;
import com.tsystems.javaschool.services.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Date;

@Controller
public class OperatorsController {

    @Autowired
    private OperatorService operatorService;

    @RequestMapping(value = "/addClient", method = RequestMethod.POST)
    public String addClient(@RequestParam String name, @RequestParam String surname,
                          @RequestParam Date birthday, @RequestParam String address,
                          @RequestParam String email, @RequestParam String password,
                          @RequestParam Long passport, @RequestParam Long roleId){

        operatorService.addClient(name, surname, birthday, address, passport, email, password, roleId);
        return "clients";
    }

    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public String addRole(@RequestParam String description){

        operatorService.addRole(description);
        return "roles";
    }

    @RequestMapping(value = "/concludeContract", method = RequestMethod.POST)
    public String concludeContract(@RequestParam String name, @RequestParam String surname,
                            @RequestParam Long tariffId, @RequestParam Long number, Model model)
            throws WrongIdException {

        operatorService.concludeContract(name, surname, tariffId, number);
        return "contracts";
    }

    @RequestMapping(value = "/generateNumber", method = RequestMethod.POST)
    public String generateNumber(Model model){

        Number number = operatorService.generateUniqueNumber();
        model.addAttribute("number", number);
        return "createContract";
    }

    @RequestMapping(value = "/dropContractOption", method = RequestMethod.POST)
    public void dropContractOption(@RequestParam Long contractId, @RequestParam Long optionId)
            throws WrongIdException, RequiredOptionException {

        operatorService.shutDownContractOption(contractId, optionId);
    }

    @RequestMapping(value = "/getClients", method = RequestMethod.POST)
    public String getClients(Model model){

        model.addAttribute("clients", operatorService.getClients());
        return "clients";
    }

    @RequestMapping(value = "/getContracts", method = RequestMethod.POST)
    public String getContracts(Model model){

        model.addAttribute("contracts", operatorService.getContracts());
        return "contracts";
    }

    @RequestMapping(value = "/getTariffs", method = RequestMethod.POST)
    public String getTariffs(Model model){

        model.addAttribute("tariffs", operatorService.getTariffs());
        return "tariffs";
    }

    @RequestMapping(value = "/lockContract", method = RequestMethod.POST)
    public String lockContract(@RequestParam Long number) throws WrongIdException {

        operatorService.lockNumber(number);
        return "contracts";
    }

    @RequestMapping(value = "/unlockContract", method = RequestMethod.POST)
    public String unlockContract(@RequestParam Long number) throws WrongIdException {

        operatorService.unlockNumber(number);
        return "contracts";
    }

    @RequestMapping(value = "/findClient", method = RequestMethod.POST)
    public String findClient(@RequestParam Long number){

        operatorService.find(number);
        return "clients";
    }

    @RequestMapping(value = "/changeTariff", method = RequestMethod.POST)
    public String changeTariff(@RequestParam Long contractId, @RequestParam Long tariffId)
            throws WrongIdException {

        operatorService.changeTariff(contractId, tariffId);
        return "contracts";
    }

    @RequestMapping(value = "/addTariff", method = RequestMethod.POST)
    public String addTariff(@RequestParam String name, @RequestParam Long[] optionsId)
            throws WrongIdException {

        operatorService.addTariff(name, optionsId);
        return "tariffs";
    }

    @RequestMapping(value = "/dropTariff", method = RequestMethod.POST)
    public String dropTariff(@RequestParam Long tariffId) throws WrongIdException {

        operatorService.dropTariff(tariffId);
        return "tariffs";
    }

    @RequestMapping(value = "/addOption", method = RequestMethod.POST)
    public String addOption(@RequestParam String name, @RequestParam BigDecimal optionPrice,
                            @RequestParam BigDecimal connectionPrice){

        operatorService.addOption(name, optionPrice, connectionPrice);
        return "options";
    }

    @RequestMapping(value = "/dropTariffOption", method = RequestMethod.POST)
    public String dropTariffOption(@RequestParam Long tariffId, @RequestParam Long optionId)
            throws WrongIdException {

        operatorService.dropOption(tariffId, optionId);
        return "tariff";
    }

    @RequestMapping(value = "/setIncOpt", method = RequestMethod.POST)
    public String setIncompatibleOptions(@RequestParam Long baseOptionId,
                                         @RequestParam Long[] incOptionsId)
            throws IncompatibleOptionException {

        operatorService.setIncompatibleOptions(baseOptionId, incOptionsId);
        return "option";
    }

    @RequestMapping(value = "/setReqOpt", method = RequestMethod.POST)
    public String setRequiredOptions(@RequestParam Long baseOptionId,
                                     @RequestParam Long[] reqOptionsId)
            throws RequiredOptionException {

        operatorService.setRequiredOptions(baseOptionId, reqOptionsId);
        return "option";
    }

    @RequestMapping(value = "/getOptions", method = RequestMethod.POST)
    public String getOptions(Model model){

        model.addAttribute("options", operatorService.getOptions());
        return "options";
    }

}
