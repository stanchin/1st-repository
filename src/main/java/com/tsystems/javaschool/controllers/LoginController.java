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

@Controller
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/addDetails", method = RequestMethod.GET)
    public ModelAndView addDetails(HttpSession session){
        Collection<GrantedAuthority> grantedAuthorities =
                (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication()
                        .getAuthorities();
        String authority = null;
        for (GrantedAuthority ga : grantedAuthorities){
            if ("admin".equals(ga.getAuthority())) {
                authority = ga.getAuthority();
                break;
            } else {
                authority = ga.getAuthority();
                break;
            }
        }
        Role role = clientService.getRole(authority);

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
