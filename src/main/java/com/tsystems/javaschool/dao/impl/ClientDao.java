package com.tsystems.javaschool.dao.impl;


import com.tsystems.javaschool.dao.GenericDaoImpl;
import com.tsystems.javaschool.entities.Client;

public class ClientDao extends GenericDaoImpl<Client>{

    public Client findByNumber (long num){
        return em.createQuery("SELECT c.client FROM Contract c " +
                " WHERE c.number = :number", Client.class).
                setParameter("number", num).getSingleResult();
    }
}
