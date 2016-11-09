package com.wordsweeper.service.repository;

/**
 * Created by francisco on 9/29/16.
 */
public interface Dao<T> {

    T save(T entity);

}
