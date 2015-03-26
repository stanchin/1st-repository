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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/addDetails", method = RequestMethod.GET)
    public ModelAndView addDetails(){
        Collection<GrantedAuthority> grantedAuthorities =
                (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication()
                        .getAuthorities();
        String authority = null;
        for (GrantedAuthority ga : grantedAuthorities){
            authority = ga.getAuthority();
        }
        Role role = clientService.getRole(authority);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        String email = userDetails.getUsername();
        String password = userDetails.getPassword();
        Client client = clientService.getClient(email, password);

        ModelAndView mav = new ModelAndView("welcome");
        mav.addObject("userName", client.getName());
        mav.addObject("role", role);
        return mav;
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
