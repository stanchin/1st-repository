package com.tsystems.javaschool.controllers;

import com.tsystems.javaschool.dto.ClientNumberDTO;
import com.tsystems.javaschool.dto.TariffDTO;
import com.tsystems.javaschool.services.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * RESTful controller. Provides web services of application.
 */

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private OperatorService operatorService;

    @RequestMapping(value = "/tariffs", method = RequestMethod.GET)
    public ResponseEntity<List<TariffDTO>> getTariffs(){

        return new ResponseEntity<>(operatorService.getTariffDTOs(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getClientsByTariff", method = RequestMethod.GET)
    public ResponseEntity<List<ClientNumberDTO>> getClientsByTariff(@RequestParam long tariffId){

        List<ClientNumberDTO> clientNumberDTOList = operatorService.getClientsByTariff(tariffId);
        return new ResponseEntity<>(clientNumberDTOList, HttpStatus.OK);
    }
}
