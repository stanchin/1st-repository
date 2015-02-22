package com.tsystems.javaschool;

import com.tsystems.javaschool.services.OperatorServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class AppTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MobileOperatorSystem");
        EntityManager em = emf.createEntityManager();
        OperatorServiceImpl operatorService = new OperatorServiceImpl(em);
        try {
            em.getTransaction().begin();
                operatorService.setRequiredOptions(1, 2);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
