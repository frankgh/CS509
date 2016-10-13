package com.wordsweeper.server.api;

import com.wordsweeper.server.model.Game;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WordSweeperService {

    @GET("/wordsweeper/rest/game/create/{playerName}")
    Call<Game> createGame(@Path("playerName") String playerName);

    @GET("/wordsweeper/rest/game/create/{playerName}/password/{password}")
    Call<Game> createGameWithPassword(@Path("playerName") String playerName, @Path(value = "password") String password);

    @GET("/wordsweeper/rest/game/join/{gameId}/{playerName}/password/{password:([^/]+)?}")
    Call<Game> joinGame(@Path("gameId") String gameId, @Path("playerName") String playerName, @Path("password") String password);

    @GET("/wordsweeper/rest/game/lock/{gameId}/{playerName}")
    Call<Game> lockGame(@Path("gameId") String gameId, @Path("playerName") String playerName);

    @GET("/wordsweeper/rest/game/exit/{gameId}/{playerName}")
    Call<Game> exitGame(@Path("gameId") String gameId, @Path("playerName") String playerName);

    @GET("/wordsweeper/rest/game/reset/{gameId}/{playerName}")
    Call<Game> resetGame(@Path("gameId") String gameId, @Path("playerName") String playerName);
}
