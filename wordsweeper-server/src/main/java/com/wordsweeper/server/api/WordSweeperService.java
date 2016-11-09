package com.wordsweeper.server.api;

import com.wordsweeper.server.api.model.Game;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * The interface Word sweeper service.
 *
 * @author francisco
 */
public interface WordSweeperService {

    /**
     * Create game call.
     *
     * @param playerName the player name
     * @return the call
     */
    @GET("/wordsweeper/rest/game/create/{playerName}")
    Call<Game> createGame(@Path("playerName") String playerName);

    /**
     * Create game with password call.
     *
     * @param playerName the player name
     * @param password   the password
     * @return the call
     */
    @GET("/wordsweeper/rest/game/create/{playerName}/password/{password}")
    Call<Game> createGameWithPassword(@Path("playerName") String playerName, @Path(value = "password") String password);

    /**
     * Join game call.
     *
     * @param gameId     the game id
     * @param playerName the player name
     * @return the call
     */
    @GET("/wordsweeper/rest/game/join/{gameId}/{playerName}")
    Call<Game> joinGame(@Path("gameId") String gameId, @Path("playerName") String playerName);

    /**
     * Join game with password call.
     *
     * @param gameId     the game id
     * @param playerName the player name
     * @param password   the password
     * @return the call
     */
    @GET("/wordsweeper/rest/game/join/{gameId}/{playerName}/password/{password}")
    Call<Game> joinGameWithPassword(@Path("gameId") String gameId, @Path("playerName") String playerName, @Path("password") String password);

    /**
     * Lock game call.
     *
     * @param gameId     the game id
     * @param playerName the player name
     * @return the call
     */
    @GET("/wordsweeper/rest/game/lock/{gameId}/{playerName}")
    Call<Game> lockGame(@Path("gameId") String gameId, @Path("playerName") String playerName);

    /**
     * Exit game call.
     *
     * @param gameId     the game id
     * @param playerName the player name
     * @return the call
     */
    @GET("/wordsweeper/rest/game/exit/{gameId}/{playerName}")
    Call<Game> exitGame(@Path("gameId") String gameId, @Path("playerName") String playerName);

    /**
     * Reset game call.
     *
     * @param gameId     the game id
     * @param playerName the player name
     * @return the call
     */
    @GET("/wordsweeper/rest/game/reset/{gameId}/{playerName}")
    Call<Game> resetGame(@Path("gameId") String gameId, @Path("playerName") String playerName);

    /**
     * Reposition board call.
     *
     * @param gameId       the game id
     * @param playerName   the player name
     * @param rowChange    the row change
     * @param columnChange the column change
     * @return the call
     */
    @GET("/wordsweeper/rest/player/reposition/{gameId}/{playerName}/{rowChange}/{columnChange}")
    Call<Game> repositionBoard(@Path("gameId") String gameId, @Path("playerName") String playerName,
                               @Path("rowChange") int rowChange, @Path("columnChange") int columnChange);

    @GET("/wordsweeper/rest/admin/game/list")
    Call<List<Game>> listGames();
}

