package com.wordsweeper.server;

import com.beust.jcommander.Parameter;

/**
 * The set up for command line arguments. Creates host options.
 */
public class ServerCommandOptions {

    /**
     * The port.
     */
    @Parameter(names = {"--port"}, description = "DB host port", required = false)
    private int port;

    /**
     * Gets the port.
     *
     * @return the port
     */
    public int getPort() {
        if (port <= 80) {
            return 11425;
        }
        return port;
    }
}
