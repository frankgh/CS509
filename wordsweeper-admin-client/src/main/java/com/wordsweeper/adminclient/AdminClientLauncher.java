package com.wordsweeper.adminclient;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.wordsweeper.adminclient.controller.AdminClientMessageHandler;
import com.wordsweeper.adminclient.view.AdminClientApplication;
import com.wordsweeper.core.xml.Request;

import java.util.UUID;

/**
 * Launch as an admin client for the WordSweeper game
 *
 * @author Ye
 */
public class AdminClientLauncher {

    /**
     * If requested by ClientLauncher (pass in '--host' and/or '--port' as argument).
     */
    public static void main(String[] args) throws Exception {

        AdminClientCommandOptions settings = new AdminClientCommandOptions();
        try {
            new JCommander(settings, args);
        } catch (ParameterException e) {
            System.exit(-1);
        }

        AdminClientApplication app = new AdminClientApplication();
        ServerAccess sa = new ServerAccess(settings.getHost(), settings.getPort());
        if (!sa.connect(new AdminClientMessageHandler(app))) {
            System.out.println("Unable to connect to server (" + settings.getHost() + ":" + settings.getPort() + "). Exiting.");
            System.exit(0);
        }
        System.out.println("Connected to " + settings.getHost() + ":" + settings.getPort());


        // Should we on the client ever need to communicate with the server, we need this ServerAccess
        // object.
        app.setServerAccess(sa);

        // send an introductory connect request now that we have created (but not made visible!)
        // the GUI
        Request request = new Request();
        request.setId(UUID.randomUUID().toString());
        request.setConnectRequest("");

        sa.sendRequest(request);

        // at this point, we need to make app visible, otherwise we would terminate application
        app.setVisible(true);
    }
}
