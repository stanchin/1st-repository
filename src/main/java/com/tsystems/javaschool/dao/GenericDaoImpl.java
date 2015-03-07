package com.tsystems.javaschool.dao;


import com.tsystems.javaschool.persistence.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {
    protected Class entityClass;
    protected static final EntityManager em = PersistenceUtil.getEntityManager();

    public GenericDaoImpl(){
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class) genericSuperclass
                .getActualTypeArguments()[0];
    }

    @Override
    public T create(T t) {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        return t;
    }

    @Override
    public T update(T t) {
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
        return t;
    }

    @Override
    public void remove(T t) {
        em.getTransaction().begin();
        t = em.merge(t);
        em.remove(t);
        em.getTransaction().commit();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getById(Long id) {
        return (T) em.find(entityClass, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        TypedQuery<T> query = em.createQuery("from " + entityClass.getName(), entityClass);
        return query.getResultList();
    }
}
