package com.tsystems.javaschool.dao.impl;


import com.tsystems.javaschool.dao.GenericDaoImpl;
import com.tsystems.javaschool.entities.Number;
import org.springframework.stereotype.Repository;


@Repository
public class NumberDao extends GenericDaoImpl<Number>{
    @Override
    public Number getById(Long id) {
        throw new UnsupportedOperationException("The entity Number don't have an id");
    }

    public Number getByNumber (Long num){
        return em.find(Number.class, num);
    }
}
