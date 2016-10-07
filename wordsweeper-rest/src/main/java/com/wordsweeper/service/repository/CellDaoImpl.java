package com.wordsweeper.service.repository;

import com.wordsweeper.service.model.Cell;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by francisco on 10/6/16.
 */
public class CellDaoImpl extends DaoImpl<Cell> implements CellDao {

    @Override
    public void saveList(List<Cell> entityList) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        for (Cell entity : entityList) {
            entityManager.persist(entity);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
