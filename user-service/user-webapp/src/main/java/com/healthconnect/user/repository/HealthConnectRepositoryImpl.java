package com.healthconnect.user.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HealthConnectRepositoryImpl implements HealthConnectRepository {

    @Autowired
    private EntityManagerFactory em;

    @Override
    public EntityManager getEntityManager() {
        return em.createEntityManager();
    }
}

