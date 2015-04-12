package com.tsystems.javaschool.controllers;

import com.tsystems.javaschool.entities.Client;
import com.tsystems.javaschool.entities.Contract;
import com.tsystems.javaschool.entities.Option;
import com.tsystems.javaschool.entities.Tariff;
import com.tsystems.javaschool.exceptions.IncompatibleOptionException;
import com.tsystems.javaschool.exceptions.RequiredOptionException;
import com.tsystems.javaschool.exceptions.WrongIdException;
import com.tsystems.javaschool.services.ClientService;
import com.tsystems.javaschool.services.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The client controller, which allows client to view information on pages,
 * located in '/WEB-INF/pages/client' folder.
 */
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
    public String changeTariff(@RequestParam Long tariffId, @RequestParam Long contractId, Model model)
            throws WrongIdException {

        clientService.changeTariff(contractId, tariffId);
        model.addAttribute("success", "Tariff changed");
        return "client/client";
    }

    @RequestMapping(value = "/addContractOptions", method = RequestMethod.POST)
    public String addContractOption(Model model, @RequestParam long contractId, @RequestParam long... optionsId)
            throws WrongIdException, IncompatibleOptionException, RequiredOptionException {

        clientService.setOptions(contractId, optionsId);
        model.addAttribute("success", "Options added on");
        return "client/client";
    }

    @RequestMapping(value = "/dropContractOptionByClient", method = RequestMethod.POST)
    public String dropContractOption(Model model, @RequestParam Long contractId, @RequestParam Long optionId)
            throws WrongIdException {

        clientService.removeOption(contractId, optionId);
        model.addAttribute("success", "Option removed");
        return "client/client";
    }

    @RequestMapping(value = "/lockContractByClient", method = RequestMethod.POST)
    public String lockContract(@RequestParam long contractId, Model model)
            throws WrongIdException {

        clientService.lockNumber(contractId);
        model.addAttribute("success", "Contract locked");
        return "client/client";
    }

    @RequestMapping(value = "/unlockContractByClient", method = RequestMethod.POST)
    public String unlockContract(@RequestParam long contractId, Model model)
            throws WrongIdException {

        clientService.unlockNumber(contractId);
        model.addAttribute("success", "Contract unlocked");
        return "client/client";
    }

    @RequestMapping(value = "/goToClientPage", method = RequestMethod.GET)
    public String goToClientPage(){
        return "client/client";
    }

    @RequestMapping(value = "/getAddOptionsForm", method = RequestMethod.GET)
    public String getAddOptionsForm(@RequestParam long contractId, @RequestParam long tariffId,
                                    Model model){

        Tariff tariff = clientService.getTariff(tariffId);
        Contract contract = clientService.getContract(contractId);

        List<Option> options = operatorService.getOptions();
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

        options.removeAll(tariffOptions);
        options.removeAll(incTariffOptionsSet);
        options.removeAll(contractOptions);
        options.removeAll(incContractOptionsSet);

        model.addAttribute("contractId", contractId);
        model.addAttribute("options", options);

        return "client/addOptions";
    }

    @RequestMapping(value = "/getChangeContractTariffForm", method = RequestMethod.GET)
    public String getChangeContractTariffForm(@RequestParam Long contractId, Model model){

        Contract contract = clientService.getContract(contractId);
        List<Tariff> tariffList = clientService.getTariffs();

        model.addAttribute("tariffs", tariffList);
        model.addAttribute("contract", contract);

        return "client/changeContractTariff";
    }

    @RequestMapping(value = "/getContractOptionsPageByClient", method = RequestMethod.GET)
    public String getContractOptionsPage(@RequestParam long contractId, Model model){

        Contract contract = operatorService.getContract(contractId);
        model.addAttribute("contractOptions", contract.getOptions());
        model.addAttribute("contractId", contractId);
        return "client/contractOptionsPage";
    }
}
