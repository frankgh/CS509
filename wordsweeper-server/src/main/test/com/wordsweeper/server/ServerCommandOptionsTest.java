package com.wordsweeper.server;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Test the ServerCommandOptions
 *
 * @author francisco
 */
public class ServerCommandOptionsTest {
    @Test
    public void getPort() throws Exception {

        ServerCommandOptions settings = new ServerCommandOptions();
        try {
            new JCommander(settings, new String[]{"--port", "8080"});
        } catch (ParameterException e) {
            System.exit(-1);
        }

        assertEquals(8080, settings.getPort());
    }

}