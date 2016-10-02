package com.wordsweeper.service.repository;

import java.util.List;

/**
 * Created by francisco on 9/29/16.
 */
public interface Dao<T> {

    T save(T entity);

    T update(T entity);

    T delete(T entity);

    T findById(int id);

    List<T> findAll();
}
