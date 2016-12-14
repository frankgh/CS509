package com.wordsweeper.adminclient;

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

    // If requested by ClientLauncher (pass in '-server' as argument).
    public static final String serverHost = "cs509.frankgh.com";

    /**
     * If requested by ClientLauncher (pass in '-server' as argument).
     */
    public static void main(String[] args) throws Exception {

        // select dedicated server with '-server' options
        String host = "localhost";
        if (args.length > 0 && args[0].equals("-server")) {
            host = serverHost;
        }

        AdminClientApplication app = new AdminClientApplication();
        ServerAccess sa = new ServerAccess(host, 11425);
        if (!sa.connect(new AdminClientMessageHandler(app))) {
            System.out.println("Unable to connect to server (" + host + "). Exiting.");
            System.exit(0);
        }
        System.out.println("Connected to " + host);


        // Should we on the client ever need to communicate with the server, we need this ServerAccess
        // object.
        app.setServerAccess(sa);

        // send an introductory connect request now that we have created (but not made visible!)
        // the GUI
        Request request = new Request();
        request.setId(UUID.randomUUID().toString());
        request.setConnectRequest(new Object());

        sa.sendRequest(request);

        // at this point, we need to make app visible, otherwise we would terminate application
        app.setVisible(true);
    }
}
