package com.wordsweeper.service;

import com.wordsweeper.service.model.Board;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by francisco on 9/15/16.
 */
@Path("/board")
public class BoardService {

    @GET
    @Path("/reset/{boardId}")
    public Response reset(@PathParam("boardId") String boardId) {

        // TODO: actually load the board here
        Board board = new Board(0); /* Load board with id = boardId */

        board.reset(); /* reset board */

        // TODO: persist board here


        return Response
                .ok(board)
                .build(); /* Return response */
    }
}
