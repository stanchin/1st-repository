package com.tsystems.javaschool.dao.impl;


import com.tsystems.javaschool.dao.GenericDaoImpl;
import com.tsystems.javaschool.entities.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientDao extends GenericDaoImpl<Client>{

    public Client findByNumber (long num){
        return em.createQuery("SELECT c.client FROM Contract c " +
                " WHERE c.number = :number", Client.class).
                setParameter("number", num).getSingleResult();
    }

    public Client findByEmailPass (String email, String password){
        return em.createQuery("SELECT c FROM Client c WHERE " +
                "c.email = :email AND c.password = :password", Client.class)
                .setParameter("email", email).setParameter("password", password)
                .getSingleResult();
    }

    public Client findByNameSurname (String name, String surname){
        return em.createQuery("SELECT c from Client c WHERE " +
                "c.name = :name AND c.surname = :surname", Client.class)
                .setParameter("name", name).setParameter("surname", surname)
                .getSingleResult();
    }
}
