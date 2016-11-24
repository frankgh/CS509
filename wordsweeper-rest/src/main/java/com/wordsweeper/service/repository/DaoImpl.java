package com.wordsweeper.service.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Base data access object
 *
 * @param <T> the type parameter
 * @author francisco
 */
public abstract class DaoImpl<T> implements Dao<T> {

    /**
     * The Entity manager factory.
     */
    static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("db-manager");

    /**
     * The Internal entity manager.
     */
    EntityManager internalEntityManager;

    /**
     * Gets entity manager.
     *
     * @return the entity manager
     */
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

    /* (non-Javadoc)
     * @see com.wordsweeper.service.repository.Dao<T>#save(T)
	 */
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
