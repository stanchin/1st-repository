package com.tsystems.javaschool.controllers;

import com.tsystems.javaschool.entities.Client;
import com.tsystems.javaschool.entities.Role;
import com.tsystems.javaschool.services.ClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Collection;
/**
 * The controller, which allows user to view information on pages,
 * located in '/WEB-INF/pages' folder and allows to get details
 * about client from program.
 */
@Controller
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/addDetails", method = RequestMethod.GET)
    public ModelAndView addDetails(HttpSession session){
        LOGGER.debug("Adding details to user");

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        String email = userDetails.getUsername();
        String password = userDetails.getPassword();
        Client client = clientService.getClient(email, password);

        ModelAndView mav = new ModelAndView("welcome");
        mav.addObject("userName", client.getName());
        session.setAttribute("client", client);
        return mav;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String goHome(){
        LOGGER.debug("Returning index.jsp page");
        return "index";
    }

    @RequestMapping(value = "/goLogin", method = RequestMethod.GET)
    public String goLogin(){
        LOGGER.debug("Returning login.jsp page");
        return "login";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(){
        LOGGER.debug("Returning welcome.jsp page");
        return "welcome";
    }
}
