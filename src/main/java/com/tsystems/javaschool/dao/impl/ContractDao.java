package com.tsystems.javaschool.dao.impl;


import com.tsystems.javaschool.dao.GenericDaoImpl;
import com.tsystems.javaschool.dto.ClientNumberDTO;
import com.tsystems.javaschool.entities.Contract;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;


@Repository("contractDao")
public class ContractDao extends GenericDaoImpl<Contract> {

    /**
     * This method search concluded contract from database by number.
     * @param number the telephone or contract number
     *               @return Contract entity
     */
    public Contract findByNumber(long number) {
        return em.createQuery(
                "SELECT c FROM Contract c WHERE c.number.number = :number", Contract.class)
                .setParameter("number", number).getSingleResult();
    }

    public List<Contract> getClientsByTariff(long tariffId) {
        TypedQuery<Contract> typedQuery =
                em.createQuery("select c from Contract c where c.tariff.id = :tariffId", Contract.class)
                        .setParameter("tariffId", tariffId);
        return typedQuery.getResultList();
    }
}
