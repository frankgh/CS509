package com.wordsweeper.service.repository;

import com.wordsweeper.service.model.Cell;

import java.util.List;

/**
 * Created by francisco on 10/6/16.
 */
public interface CellDao extends Dao<Cell> {

    void saveList(List<Cell> entityList);
}
