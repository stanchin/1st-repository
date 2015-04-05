package com.tsystems.javaschool.dao.impl;

import com.tsystems.javaschool.dao.GenericDaoImpl;
import com.tsystems.javaschool.entities.Role;
import org.springframework.stereotype.Component;


@Component
public class RoleDao extends GenericDaoImpl<Role> {

    /**
     *This method search the Role entity by the name of this entity.
     * @param authority the authority that system user have
     *                  @return Role entity
     */
    public Role getByDescription(String authority) {
        return em.createQuery("select r from Role r where r.role = :role", Role.class)
                .setParameter("role", authority)
                .getSingleResult();
    }
}
