package com.tsystems.javaschool.controllers;

import com.tsystems.javaschool.entities.Client;
import com.tsystems.javaschool.entities.Contract;
import com.tsystems.javaschool.entities.Option;
import com.tsystems.javaschool.entities.Tariff;
import com.tsystems.javaschool.exceptions.IncompatibleOptionException;
import com.tsystems.javaschool.exceptions.WrongIdException;
import com.tsystems.javaschool.services.ClientService;
import com.tsystems.javaschool.services.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@SessionAttributes("client")
public class ClientsController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private OperatorService operatorService;

    @RequestMapping(value = "/getClientContracts", method = RequestMethod.GET)
    public String getContracts(@ModelAttribute Client client, Model model)
            throws WrongIdException {

        model.addAttribute("contracts", clientService.getContracts(client.getId()));
        return "client/contracts";
    }

    @RequestMapping(value = "/getTariffsForClient", method = RequestMethod.GET)
    public String getTariffs(Model model) {
        model.addAttribute("tariffs", clientService.getTariffs());
        return "operator/tariffs";
    }

    @RequestMapping(value = "/getTariffOptions", method = RequestMethod.GET)
    public String getTariffOptions(@RequestParam Long tariffId, Model model){
        Tariff tariff = clientService.getTariff(tariffId);
        model.addAttribute("options", tariff.getOptions());
        model.addAttribute("tariffName", tariff.getName());
        return "client/options";
    }

    @RequestMapping(value = "/changeContractTariff", method = RequestMethod.POST)
    public String changeTariff(@RequestParam Long tariffId, @RequestParam Long contractId)
            throws WrongIdException {

        clientService.changeTariff(contractId, tariffId);
        return "client/contracts";
    }

    @RequestMapping(value = "/addContractOption", method = RequestMethod.POST)
    public String addContractOption(@RequestParam Long contractId, @RequestParam Long optionId)
            throws WrongIdException, IncompatibleOptionException {

        clientService.setOptions(contractId, optionId);
        return "client/client";
    }

    @RequestMapping(value = "/dropContractOptionByClient", method = RequestMethod.POST)
    public String dropContractOption(@RequestParam Long contractId, @RequestParam Long optionId)
            throws WrongIdException {

        clientService.removeOptions(contractId, optionId);
        return "client/contracts";
    }

    @RequestMapping(value = "/lockContractByClient", method = RequestMethod.POST)
    public String lockContract(@RequestParam Long contractId)
            throws WrongIdException {

        clientService.lockNumber(contractId);
        return "client/contracts";
    }

    @RequestMapping(value = "/unlockContractByClient", method = RequestMethod.POST)
    public String unlockContract(@RequestParam Long contractId)
            throws WrongIdException {

        clientService.unlockNumber(contractId);
        return "client/contracts";
    }

    @RequestMapping(value = "/goToClientPage", method = RequestMethod.GET)
    public String goToClientPage(){
        return "client/client";
    }

    @RequestMapping(value = "/getAddOptionsForm", method = RequestMethod.GET)
    public String getAddOptionsForm(@RequestParam Long contractId, Model model){
        List<Option> options = operatorService.getOptions();
        model.addAttribute("contractId", contractId);
        model.addAttribute("options", options);
        return "client/addOptions";
    }

    @RequestMapping(value = "/getChangeContractTariffForm", method = RequestMethod.GET)
    public String getChangeContractTariffForm(@RequestParam Long contractId, Model model){
        Contract contract = clientService.getContract(contractId);
        model.addAttribute("contract", contract);
        return "client/changeContractTariff";
    }
}
