package com.wordsweeper.service.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by francisco on 10/6/16.
 */
public abstract class DaoImpl<T> implements Dao<T> {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("db-manager");

    private EntityManager internalEntityManager;

    protected EntityManager getEntityManager() {
        if (internalEntityManager == null) {
            synchronized (GameDaoImpl.class) {
                if (internalEntityManager == null) {
                    internalEntityManager = entityManagerFactory.createEntityManager();
                }
            }
        }
        return internalEntityManager;
    }

    @Override
    public T save(T entity) {

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();

        return entity;
    }
}
