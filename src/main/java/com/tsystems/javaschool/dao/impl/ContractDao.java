package com.tsystems.javaschool.dao.impl;


import com.tsystems.javaschool.dao.GenericDaoImpl;
import com.tsystems.javaschool.entities.Contract;

import java.util.List;

public class ContractDao extends GenericDaoImpl<Contract> {

    public Contract findByNumber(long number) {
        return (Contract) em.createQuery(
                "SELECT c FROM Contract c WHERE c.number = :number")
                .setParameter("number", number).getSingleResult();
    }
}
