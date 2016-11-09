package com.wordsweeper.service.model;

import org.junit.Test;

import com.wordsweeper.server.model.GameState;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by francisco on 10/4/16.
 */
public class GameStateTest {
    @Test
    public void constructor() throws Exception {
        GameState state = new GameState("random123game456id789", "11111");
        assertEquals("random123game456id789", state.getGameID());
        
        List<String> serverThreadIDs = new ArrayList<String>();
        serverThreadIDs.add("11111");
        assertEquals(state.getServerThreadIDs(), serverThreadIDs);
        
        state.addPlayer("22222");
        serverThreadIDs.add("22222");
        assertEquals(state.getServerThreadIDs(), serverThreadIDs);
    }

}