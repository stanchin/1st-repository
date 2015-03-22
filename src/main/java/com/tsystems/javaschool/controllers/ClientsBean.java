package com.tsystems.javaschool.controllers;

import com.tsystems.javaschool.entities.Tariff;
import com.tsystems.javaschool.exceptions.IncompatibleOptionException;
import com.tsystems.javaschool.exceptions.WrongIdException;
import com.tsystems.javaschool.services.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@Scope("session")
public class ClientsBean {

    @Autowired
    private ClientServiceImpl clientService;

    @RequestMapping(value = "/getClientContracts", method = RequestMethod.POST)
    public String getContracts(@RequestParam Long clientId, Model model)
            throws WrongIdException {

        model.addAttribute("contracts", clientService.getContracts(clientId));
        return "contracts";
    }

    @RequestMapping(value = "/getTariffsForClient", method = RequestMethod.POST)
    public String getTariffs(Model model) {
        model.addAttribute("tariffs", clientService.getTariffs());
        return "tariffs";
    }

    @RequestMapping(value = "/getTariffOptions", method = RequestMethod.POST)
    public String getTariffOptions(@RequestParam Long tariffId, Model model){
        Tariff tariff = clientService.getTariff(tariffId);
        model.addAttribute("options", tariff.getOptions());
        return "options";
    }

    @RequestMapping(value = "/changeContractTariff", method = RequestMethod.POST)
    public String changeTariff(@RequestParam Long tariffId, @RequestParam Long contractId)
            throws WrongIdException {

        clientService.changeTariff(contractId, tariffId);
        return "contracts";
    }

    @RequestMapping(value = "/addContractOption", method = RequestMethod.POST)
    public String addContractOption(@RequestParam Long contractId, @RequestParam Long optionId)
            throws WrongIdException, IncompatibleOptionException {

        clientService.setOptions(contractId, optionId);
        return "contracts";
    }

    @RequestMapping(value = "/dropContractOptionByClient", method = RequestMethod.POST)
    public String dropContractOption(@RequestParam Long contractId, @RequestParam Long optionId)
            throws WrongIdException {

        clientService.removeOptions(contractId, optionId);
        return "contracts";
    }

    @RequestMapping(value = "/lockContractByOperator", method = RequestMethod.POST)
    public String lockContract(@RequestParam Long contractId)
            throws WrongIdException {

        clientService.lockNumber(contractId);
        return "contracts";
    }

    @RequestMapping(value = "/unlockContractByClient", method = RequestMethod.POST)
    public String unlockContract(@RequestParam Long contractId)
            throws WrongIdException {

        clientService.unlockNumber(contractId);
        return "contracts";
    }
}
