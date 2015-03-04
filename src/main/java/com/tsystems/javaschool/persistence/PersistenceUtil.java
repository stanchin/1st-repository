package com.tsystems.javaschool.persistence;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {
    private static final String PERSISTENCE_UNIT_NAME = "MobileOperatorSystem";
    private static final EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (ExceptionInInitializerError e) {
            System.err.println("Initializing EntityManagerFactory failed:" + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}
