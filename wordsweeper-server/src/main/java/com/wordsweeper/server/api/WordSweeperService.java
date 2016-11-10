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
 * @author Celia
 */
public interface WordSweeperService {

    /**
     * CreateGame API call.
     *
     * @param playerName the player name
     * @return the updated game status
     */
    @GET("/wordsweeper/rest/game/create/{playerName}")
    Call<Game> createGame(@Path("playerName") String playerName);

    /**
     * CreateGame with password API call.
     *
     * @param playerName the player name
     * @param password   the password
     * @return the updated game status
     */
    @GET("/wordsweeper/rest/game/create/{playerName}/password/{password}")
    Call<Game> createGameWithPassword(@Path("playerName") String playerName, @Path(value = "password") String password);

    /**
     * JoinGame API call.
     *
     * @param gameId     the game id
     * @param playerName the player name
     * @return the updated game status
     */
    @GET("/wordsweeper/rest/game/join/{gameId}/{playerName}")
    Call<Game> joinGame(@Path("gameId") String gameId, @Path("playerName") String playerName);

    /**
     * JoinGame with password API call.
     *
     * @param gameId     the game id
     * @param playerName the player name
     * @param password   the password
     * @return the call
     */
    @GET("/wordsweeper/rest/game/join/{gameId}/{playerName}/password/{password}")
    Call<Game> joinGameWithPassword(@Path("gameId") String gameId, @Path("playerName") String playerName, @Path("password") String password);

    /**
     * LockGame API call.
     *
     * @param gameId     the game id
     * @param playerName the player name
     * @return the updated game status
     */
    @GET("/wordsweeper/rest/game/lock/{gameId}/{playerName}")
    Call<Game> lockGame(@Path("gameId") String gameId, @Path("playerName") String playerName);

    /**
     * ExitGame API call.
     *
     * @param gameId     the game id
     * @param playerName the player name
     * @return the updated game status
     */
    @GET("/wordsweeper/rest/game/exit/{gameId}/{playerName}")
    Call<Game> exitGame(@Path("gameId") String gameId, @Path("playerName") String playerName);

    /**
     * ResetGame API call.
     *
     * @param gameId     the game id
     * @param playerName the player name
     * @return the updated game status
     */
    @GET("/wordsweeper/rest/game/reset/{gameId}/{playerName}")
    Call<Game> resetGame(@Path("gameId") String gameId, @Path("playerName") String playerName);

    /**
     * RepositionBoard API call.
     *
     * @param gameId       the game id
     * @param playerName   the player name
     * @param rowChange    the row change
     * @param columnChange the column change
     * @return the updated game status
     */
    @GET("/wordsweeper/rest/player/reposition/{gameId}/{playerName}/{rowChange}/{columnChange}")
    Call<Game> repositionBoard(@Path("gameId") String gameId, @Path("playerName") String playerName,
                               @Path("rowChange") int rowChange, @Path("columnChange") int columnChange);

    /**
     * ListGames API call
     *
     * @return the list of games
     */
    @GET("/wordsweeper/rest/admin/game/list")
    Call<List<Game>> listGames();

    /**
     * ShowGameState API call.
     *
     * @param gameId the game id
     * @return the current game status
     */
    @GET("/wordsweeper/rest/admin/game/{gameId}")
    Call<Game> showGameState(@Path("gameId") String gameId);
}

