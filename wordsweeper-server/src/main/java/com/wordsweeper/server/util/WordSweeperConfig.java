package com.wordsweeper.server.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * Reads Word Sweeper configurations defined in the application
 * POM, or overwritten by compilation parameters
 *
 * @author francisco
 */
public class WordSweeperConfig {

    private static final String WORDSWEEPER_CONFIG_FILE = "my.properties";

    private static final String wordsweeperRestServerUrl;

    static {
        InputStream asStream = WordSweeperConfig.class.getResourceAsStream(WORDSWEEPER_CONFIG_FILE);
        Properties properties = new Properties();
        wordsweeperRestServerUrl = properties.getProperty("wordsweeper.rest.server.url");
    }

}
