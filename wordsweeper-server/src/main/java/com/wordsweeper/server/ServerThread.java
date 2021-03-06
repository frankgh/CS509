package com.wordsweeper.server;

import com.wordsweeper.core.util.JAXBUtil;
import com.wordsweeper.core.xml.ObjectFactory;
import com.wordsweeper.core.xml.Request;
import com.wordsweeper.core.xml.Response;
import com.wordsweeper.server.controller.IProtocolHandler;
import com.wordsweeper.server.controller.IShutdownHandler;
import com.wordsweeper.server.model.ClientState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

/**
 * Thread to handle individual requests from a client.
 *
 * @author heineman
 */
public class ServerThread extends Thread implements ClientState {
    /**
     * The Client.
     */
    final Socket client;             // Socket used by thread to communicate with client.
    /**
     * The From client.
     */
    final BufferedReader fromClient; // Used to process strings from client.
    /**
     * The To client.
     */
    final PrintWriter toClient;      // Used to send strings to client.
    /**
     * The Handler.
     */
    final IProtocolHandler handler;  // Handler to process protocol.
    /**
     * The Server.
     */
    final Server server;             // Server for which we are operating.
    /**
     * The Id.
     */
    final String id;                 // Associated unique ID for this thread.
    /**
     * The Data.
     */
    Object data;                     // User-defined object associated with each thread.

    /**
     * Create objects to handle input/output to client.
     *
     * @param srv the srv
     * @param s   the s
     * @param h   the h
     * @throws IOException the io exception
     */
    ServerThread(Server srv, Socket s, IProtocolHandler h) throws IOException {
        server = srv;
        fromClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
        toClient = new PrintWriter(s.getOutputStream(), true);
        client = s;
        handler = h;
        id = UUID.randomUUID().toString();
    }

    /**
     * Thread receives strings from the client, processes them and returns
     * result back to the client.
     * <p>
     * Each client connection is assigned a unique identifier by which one can reference
     * it in the future.
     * <p>
     * If protocol is violated, then immediately close connection to client.
     */
    public void run() {
        // Initial connect request comes in
        Request request = getRequest();

        if (request == null) {
            closeConnection();
            return;
        }

        if (request.getConnectRequest() == null) {
            closeConnection();
            System.err.println("Received invalid initial request from Remote Client.");
            return;
        }

        ObjectFactory objectFactory = new ObjectFactory();
        Response responseWrapper = objectFactory.createResponse();
        responseWrapper.setId(request.getId());
        responseWrapper.setSuccess(true);
        responseWrapper.setConnectResponse(objectFactory.createConnectResponse());
        responseWrapper.getConnectResponse().setId(id);

        // Return connect response with our (statistically) unique ID.
        if (!sendMessage(responseWrapper)) {
            closeConnection();
            System.err.println("Unable to respond to connect Request from remote Client.");
            return;
        }

        // register our thread with the server
        Server.register(id, this);

        // have handler manage the protocol until it decides it is done.
        while ((request = getRequest()) != null) {

            Response response = handler.process(this, request);

            if (response == null) {
                continue;
            }

            if (!sendMessage(response)) {
                break;
            }
        }

        // client is done so thread can be de-registered
        if (handler instanceof IShutdownHandler) {
            ((IShutdownHandler) handler).logout(Server.getState(id));
        }
        Server.unregister(id);

        // close communication to client.
        closeConnection();
    }

    /**
     * Return (statistically) unique id.
     */
    public String id() {
        return id;
    }

    /**
     * Associate new object data as the user-defined data for the thread and return the old previous value.
     */
    public synchronized Object setData(Object newData) {
        Object old = data;
        data = newData;
        return old;
    }

    /**
     * Retrieve user-defined data associated with this thread.
     */
    public synchronized Object getData() {
        return data;
    }

    /**
     * Get the remote socket address
     *
     * @return the remote socket address
     */
    public String getRemoteSocketAddress() {
        return client.getRemoteSocketAddress().toString();
    }

    /**
     * Send a response object to the client.
     *
     * @param response the response
     * @return true on success, otherwise false
     */
    public boolean sendMessage(Response response) {
        String messageString = JAXBUtil.serialize(response);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        System.out.println(dateFormat.format(cal.getTime()) + " Sending Response to " + id +
                " at " + getRemoteSocketAddress() + ":");
        JAXBUtil.prettyPrintln(response);
        toClient.println(messageString);
        return !toClient.checkError();
    }

    /**
     * Deserialize the request from the stream
     *
     * @return the request
     */
    public Request getRequest() {
        String xml = JAXBUtil.getXmlStringFromReader(fromClient, "</request>");

        if (xml == null) {
            return null;
        }

        return JAXBUtil.deserialize(xml, Request.class);
    }

    /**
     * Close the connection to the client
     */
    private void closeConnection() {
        try {
            fromClient.close();
            toClient.close();
            client.close();
        } catch (IOException e) {
            System.err.println("Unable to close connection:" + e.getMessage());
        }
    }
}
