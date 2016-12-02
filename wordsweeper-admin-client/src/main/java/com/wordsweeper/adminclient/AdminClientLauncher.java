package com.wordsweeper.adminclient;

import com.wordsweeper.adminclient.model.AdminClientModel;
import com.wordsweeper.adminclient.controller.AdminClientMessageHandler;
import com.wordsweeper.adminclient.ServerAccess;
import com.wordsweeper.adminclient.view.AdminClientApplication;
import xml.Message;

/**
 * Launch Admin Client for the WordSweeper game
 * 
 * @author Ye
 *
 */
public class AdminClientLauncher {

	// If requested by ClientLauncher (pass in '-server' as argument).
    public static final String serverHost = "cs509.frankgh.com";
    
    public static void main(String[] args) throws Exception {
    	
    	if (!Message.configure("wordsweeper.xsd") && !Message.configure("wordsweeper-admin-client/wordsweeper.xsd")) {
            System.exit(0);
        }

    	// select dedicated server with '-server' options
        String host = "cs509.frankgh.com";
        if (args.length > 0 && args[0].equals("-server")) {
            host = serverHost;
        }
        
        AdminClientModel model = new AdminClientModel();
        AdminClientApplication app = new AdminClientApplication(model);
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
        String xmlString = Message.requestHeader() + "<connectRequest/></request>";
        Message m = new Message(xmlString);
        sa.sendRequest(m);

        // at this point, we need to make app visible, otherwise we would terminate application
        app.setVisible(true);
    }
}
