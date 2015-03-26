package com.tsystems.javaschool.dao.impl;

import com.tsystems.javaschool.dao.GenericDaoImpl;
import com.tsystems.javaschool.entities.Role;
import org.springframework.stereotype.Component;


@Component
public class RoleDao extends GenericDaoImpl<Role> {

    public Role getByDescription(String authority) {
        return em.createQuery("select r from Role r where r.role = :role", Role.class)
                .setParameter("role", authority)
                .getSingleResult();
    }
}
