package com.portfolio.fxexpensetrack.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DAO {
    private static final EntityManagerFactory factory;

    static {
        try {
            factory = Persistence.createEntityManagerFactory("connection");
        } catch (Throwable ex) {

            System.err.println("Initial EntityManagerFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
