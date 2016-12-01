package com.wordsweeper.service.repository;

import com.wordsweeper.service.model.Cell;
import com.wordsweeper.service.model.Game;
import com.wordsweeper.service.model.Player;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * The Game Data Access Object
 *
 * @author francisco
 */
public class GameDaoImpl extends DaoImpl<Game> implements GameDao {

    /* (non-Javadoc)
     * @see com.wordsweeper.service.repository.GameDao#save(com.wordsweeper.service.model.Game)
	 */
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

    /* (non-Javadoc)
     * @see com.wordsweeper.service.repository.GameDao#findByGameId(java.lang.String)
	 */
    @Override
    public Game findByGameId(String gameId) {

        TypedQuery<Game> query = getEntityManager()
                .createQuery("FROM Game WHERE uniqueId = :uniqueId", Game.class);
        query.setParameter("uniqueId", gameId);

        List<Game> gameList = query.getResultList();
        return gameList != null && gameList.size() > 0 ? gameList.get(0) : null;
    }

    /* (non-Javadoc)
     * @see com.wordsweeper.service.repository.GameDao#findAll()
	 */
    @Override
    public List<Game> findAll() {

        TypedQuery<Game> query = getEntityManager()
                .createQuery("FROM Game WHERE status = :status", Game.class);
        query.setParameter("status", Game.STATUS_ACTIVE);

        return query.getResultList();
    }
}
