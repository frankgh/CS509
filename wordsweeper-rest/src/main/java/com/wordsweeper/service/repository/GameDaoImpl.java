package com.wordsweeper.service.repository;

import com.wordsweeper.service.model.Cell;
import com.wordsweeper.service.model.Game;
import com.wordsweeper.service.model.Player;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Francisco on 9/27/2016.
 */
public class GameDaoImpl extends DaoImpl<Game> implements GameDao {

    @Override
    public Game save(Game entity) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        for (Cell cell : entity.getBoard().getCellList()) {
            entityManager.persist(cell);
        }

        for (Player player : entity.getPlayerList()) {
            entityManager.persist(player); /* persist the player */
        }

        entityManager.persist(entity.getBoard()); /* persist the board */
        entityManager.persist(entity);

        entityManager.getTransaction().commit();
        entityManager.close();

        return entity;
    }

    @Override
    public Game findByGameId(String gameId) {

        TypedQuery<Game> query = getEntityManager()
                .createQuery("FROM Game WHERE uniqueId = :uniqueId", Game.class);
        query.setParameter("uniqueId", gameId);

        List<Game> gameList = query.getResultList();

        return gameList != null && gameList.size() > 0 ? gameList.get(0) : null;
    }

    @Override
    public List<Game> findAll() {
        return getEntityManager().createQuery("from Game", Game.class).getResultList();
    }
}
