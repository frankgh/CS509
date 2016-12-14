package com.wordsweeper.adminclient;

import com.beust.jcommander.Parameter;

/**
 * The set up for command line arguments. Creates host, port.
 *
 * @author francisco
 */
public class AdminClientCommandOptions {

    /**
     * The host.
     */
    @Parameter(names = {"--host"}, description = "DB host", required = false)
    private String host;

    /**
     * The port.
     */
    @Parameter(names = {"--port"}, description = "DB host port", required = false)
    private int port;

    /**
     * Gets the host.
     *
     * @return the host
     */
    public String getHost() {
        if (host == null || host.length() == 0) {
            return "cs509.frankgh.com";
        }
        return host;
    }

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
