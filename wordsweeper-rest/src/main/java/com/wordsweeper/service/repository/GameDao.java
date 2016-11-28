package com.wordsweeper.service.repository;

import com.wordsweeper.service.model.Game;

import java.util.List;

/**
 * Interface for the Game Data Access Object
 *
 * @author francisco
 */
public interface GameDao {

    /**
     * Save a game in the persistence storage
     *
     * @param entity the game entity
     * @return the saved game entity
     */
    Game save(Game entity);

    /**
     * Find game by the unique's game ID
     *
     * @param gameId the unique game ID
     * @return the game if it exists, null otherwise
     */
    Game findByGameId(String gameId);

    /**
     * Find all the active games
     *
     * @return a list of all the active games
     */
    List<Game> findAll();

}
