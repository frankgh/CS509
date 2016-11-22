package com.wordsweeper.server.util;

import java.util.Properties;

/**
 * Reads Word Sweeper configurations defined in the application
 * POM, or overwritten by compilation parameters
 *
 * @author francisco
 */
public class WordSweeperConfig {

    private static final String WORDSWEEPER_CONFIG_FILE = "ws.properties";

    private static String wordsweeperRestServerUrl;

    static {
        Properties properties = PropertiesUtil.getProperties(null, WORDSWEEPER_CONFIG_FILE);
        wordsweeperRestServerUrl = properties.getProperty("wordsweeper.rest.server.url");
    }

    public static String getWordsweeperRestServerUrl() {
        return wordsweeperRestServerUrl;
    }
}
