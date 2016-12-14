package com.wordsweeper.adminclient;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Test the AdminClientCommandOptions
 *
 * @author francisco
 */
public class AdminClientCommandOptionsTest {
    @Test
    public void getHost() throws Exception {

        AdminClientCommandOptions settings = new AdminClientCommandOptions();
        try {
            new JCommander(settings, new String[]{"--host", "localhost"});
        } catch (ParameterException e) {
            System.exit(-1);
        }

        assertEquals("localhost", settings.getHost());
    }


    @Test
    public void getPort() throws Exception {
        AdminClientCommandOptions settings = new AdminClientCommandOptions();
        try {
            new JCommander(settings, new String[]{"--port", "8080"});
        } catch (ParameterException e) {
            System.exit(-1);
        }

        assertEquals(8080, settings.getPort());
    }

}