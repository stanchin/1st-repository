package com.tsystems.javaschool.dao.impl;


import com.tsystems.javaschool.dao.GenericDaoImpl;
import com.tsystems.javaschool.entities.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientDao extends GenericDaoImpl<Client>{

    /**
    * Find by number method returns Client from database by number
    * of concluded contract. Method search client in contracts table.
    * @param num the telephone number or contract number
    * @return Client entity
    * */
    public Client findByNumber (long num){
        return em.createQuery("SELECT c.client FROM Contract c " +
                " WHERE c.number.number = :number", Client.class).
                setParameter("number", num).getSingleResult();
    }

    /**
    * Find by email and password method returns Client from database
    * by email and password that client have. Method search client from clients table.
    * @param email the email of user
    * @param password the password of user
    * @return Client entity
    * */
    public Client findByEmailPass (String email, String password){
        return em.createQuery("SELECT c FROM Client c WHERE " +
                "c.email = :email AND c.password = :password", Client.class)
                .setParameter("email", email).setParameter("password", password)
                .getSingleResult();
    }

    /**
    * Find by name and surname method returns Client from database
    * by name and surname of this client. Method search client from clients table.
    * @param name the name of user
    * @param surname the surname of user
    * @return Client entity
    * */
    public Client findByNameSurname (String name, String surname){
        return em.createQuery("SELECT c from Client c WHERE " +
                "c.name = :name AND c.surname = :surname", Client.class)
                .setParameter("name", name).setParameter("surname", surname)
                .getSingleResult();
    }
}
