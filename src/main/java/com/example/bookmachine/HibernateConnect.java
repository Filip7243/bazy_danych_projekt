package com.example.bookmachine;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateConnect {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;

    static {
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("library-db");
    }

    private HibernateConnect() {
    }

    public static EntityManager openSession() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }
}
