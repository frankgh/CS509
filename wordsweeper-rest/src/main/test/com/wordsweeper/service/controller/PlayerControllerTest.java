package com.wordsweeper.service.controller;

import com.wordsweeper.service.model.Game;
import com.wordsweeper.service.model.Player;
import com.wordsweeper.service.model.RequestError;
import com.wordsweeper.service.repository.GameDao;
import com.wordsweeper.service.repository.GameDaoImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Testing player controller
 * @author george.
 */
public class PlayerControllerTest {
    @Test
    public void constructor() throws Exception {
    }

}
