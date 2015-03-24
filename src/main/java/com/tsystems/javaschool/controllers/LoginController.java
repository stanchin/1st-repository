package com.tsystems.javaschool.controllers;

import com.tsystems.javaschool.entities.Client;
import com.tsystems.javaschool.services.ClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam String email, @RequestParam String password){
        LOGGER.debug("Start login user");
        Client client = clientService.getClient(email, password);
        ModelAndView mav = new ModelAndView();
        mav.addObject("client", client);
        String role = client.getRole().getRole();
        switch (role){
            case "admin":
                LOGGER.debug("User is administrator");
                mav.setViewName("operator/operator");
                return mav;
            case "client":
                LOGGER.debug("User is client");
                mav.setViewName("client/client");
                return mav;
            default:
                LOGGER.debug("User is unknown");
                mav.setViewName("login");
                return mav;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(Model model){
        //todo: add logout body
        return "login";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String goHome(){
        return "index";
    }

    @RequestMapping(value = "/goLogin", method = RequestMethod.GET)
    public String goLogin(){
        return "login";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(){
        return "welcome";
    }
}
