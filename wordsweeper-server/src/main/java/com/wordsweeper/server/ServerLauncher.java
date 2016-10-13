package com.wordsweeper.server;


import com.wordsweeper.server.controller.WordSweeperProtocolHandler;
import com.wordsweeper.server.model.ServerModel;
import server.Server;

import java.io.IOException;

/**
 * Code to launch Server  from the command line.
 */
public class ServerLauncher {

    /**
     * Execute the Server using the default port.
     */
    public static void main(String[] args) {

        // Server-side model contains everything you need on the server.
        ServerModel serverModel = new ServerModel();

        // Start server and have ProtocolHandler be responsible for all XML messages.
        Server server = new Server(new WordSweeperProtocolHandler(serverModel), 11425);

        try {
            server.bind();
        } catch (IOException ioe) {
            System.err.println("Unable to launch server:" + ioe.getMessage());
            System.exit(-1);
        }

        // process all requests and exit.
        System.out.println("Server awaiting client connections");
        try {
            server.process();
            System.out.println("Server shutting down.");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
