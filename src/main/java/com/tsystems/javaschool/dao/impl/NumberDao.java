package com.tsystems.javaschool.dao.impl;


import com.tsystems.javaschool.dao.GenericDaoImpl;
import com.tsystems.javaschool.entities.Number;
import org.springframework.stereotype.Component;


@Component
public class NumberDao extends GenericDaoImpl<Number>{

    /**
     * Override method that is not supported in this program by Number entity.
     * @param id number id that didn't exists in program
     *           @throws java.lang.UnsupportedOperationException
     */
    @Override
    public Number getById(Long id) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("The entity Number don't have an id");
    }

    /**
     * Method search Number entity in database by telephone number.
     * @param num the telephone number
     *            @return Number entity
     */
    public Number getByNumber (Long num){
        return em.find(Number.class, num);
    }
}
