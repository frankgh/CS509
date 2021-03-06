package com.wordsweeper.server;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.wordsweeper.server.controller.*;
import com.wordsweeper.server.model.ServerModel;

import java.io.IOException;

/**
 * Code to launch Server  from the command line.
 *
 * @author francisco
 */
public class ServerLauncher {

    /**
     * Execute the Server using the default port.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        ServerCommandOptions settings = new ServerCommandOptions();
        try {
            new JCommander(settings, args);
        } catch (ParameterException e) {
            System.exit(-1);
        }

        // Server-side model contains everything you need on the server.
        ServerModel serverModel = new ServerModel();

        WordSweeperProtocolHandler handler = new WordSweeperProtocolHandler(serverModel);
        // Register Admin handlers
        handler.registerHandler(new ListGamesRequestController(serverModel));
        handler.registerHandler(new ShowGameStateRequestController(serverModel));
        // Register Client handlers
        handler.registerHandler(new ExitGameRequestController(serverModel));
        handler.registerHandler(new LockGameRequestController(serverModel));
        handler.registerHandler(new ResetGameRequestController(serverModel));
        handler.registerHandler(new RepositionBoardRequestController(serverModel));
        handler.registerHandler(new FindWordRequestController(serverModel));
        handler.registerHandler(new JoinGameRequestController(serverModel));
        handler.registerHandler(new CreateGameRequestController(serverModel));

        // Start server and have ProtocolHandler be responsible for all XML messages.
        //Server server = new Server(new WordSweeperProtocolHandler(serverModel), 11425);
        Server server = new Server(handler, settings.getPort());

        try {
            server.bind();
        } catch (IOException ioe) {
            System.err.println("Unable to launch server:" + ioe.getMessage());
            System.exit(-1);
        }

        // process all requests and exit.
        System.out.println("Server awaiting client connections on port " + settings.getPort());
        try {
            server.process();
            System.out.println("Server shutting down.");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
