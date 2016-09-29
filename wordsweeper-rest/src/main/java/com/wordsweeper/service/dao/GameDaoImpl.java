package com.wordsweeper.service.dao;

import com.google.inject.Provider;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by afguerrerohernan on 9/27/2016.
 */
public class GameDaoImpl {

    private final Provider<EntityManager>  entityManager;

    @Inject
    public GameDaoImpl(final Provider<EntityManager> entityManager) {
        this.entityManager=entityManager;
    }
}
