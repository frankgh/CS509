package com.wordsweeper.service.repository;

import com.wordsweeper.service.model.Game;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by afguerrerohernan on 9/27/2016.
 */
public class GameDaoImpl implements GameDao {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("db-manager");

    @Override
    public Game save(Game entity) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();

        return entity;
    }

    @Override
    public Game findById(int id) {

        Query query = entityManagerFactory.createEntityManager()
                .createQuery("FROM Game where uniqueId = ?");


        return null;
    }

    @Override
    public Game update(Game entity) {
        return null;
    }

    @Override
    public Game delete(Game entity) {
        return null;
    }

    @Override
    public List<Game> findAll() {
        return entityManagerFactory.createEntityManager().createQuery("from Game").getResultList();
    }
}
