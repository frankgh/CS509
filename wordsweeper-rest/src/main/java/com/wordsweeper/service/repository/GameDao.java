package com.wordsweeper.service.repository;

import com.wordsweeper.service.model.Game;

import java.util.List;

/**
 * Created by francisco on 9/29/16.
 */
public interface GameDao {

    Game save(Game entity);

    Game findByGameId(String gameId);

    List<Game> findAll();

}
