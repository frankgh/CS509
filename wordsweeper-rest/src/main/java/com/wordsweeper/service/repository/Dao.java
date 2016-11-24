package com.wordsweeper.service.repository;

/**
 * Interface for the base Data Access Object
 *
 * @author francisco
 */
public interface Dao<T> {

    /**
     * Save entity to the persistence layer
     *
     * @param entity the entity
     * @return the saved entity
     */
    T save(T entity);

}
