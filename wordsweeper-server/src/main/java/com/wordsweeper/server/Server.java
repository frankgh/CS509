package com.wordsweeper.server;

import com.wordsweeper.server.controller.IProtocolHandler;
import com.wordsweeper.server.model.ClientState;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Hashtable;

/**
 * Generic Server in a Client/Server communication.
 *
 * @author heineman
 */
public class Server {
    /**
     * The State.
     */
    int state = 0;                       /* Server state. 0=inactive, 1=accepting */
    /**
     * The Server port.
     */
    final int serverPort;                /* Default server port. */
    /**
     * The Server socket.
     */
    ServerSocket serverSocket = null;    /* ServerSocket to which we bind */
    /**
     * The Protocol handler.
     */
    IProtocolHandler protocolHandler;    /* Handler for protocol */

    /**
     * Hold onto references to all threads actively managed by server.
     */
    static Hashtable<String, ClientState> ids = new Hashtable<String, ClientState>();

    /**
     * Instantiates a new Server.
     *
     * @param ph   the ph
     * @param port the port
     */
    public Server(IProtocolHandler ph, int port) {
        this.serverPort = port;
        this.protocolHandler = ph;
    }

    /**
     * Bind Server to default port.
     *
     * @throws IOException the io exception
     */
    public void bind() throws IOException {
        serverSocket = new ServerSocket(serverPort);
        state = 1;
    }

    /**
     * Execute main server loop which receives client connection requests
     * and spawns threads to execute each one. Process will handle all
     * requests while state is 1 (accepting). Once no longer
     * accepting requests, this method will shutdown the server.
     *
     * @throws IOException the io exception
     */
    public void process() throws IOException {
        while (state == 1) {
            Socket client = serverSocket.accept();
            new ServerThread(this, client, protocolHandler).start();
        }

        shutdown();
    }

    /**
     * Shutdown the server.
     *
     * @throws IOException the io exception
     */
    public void shutdown() throws IOException {
        if (serverSocket != null) {
            serverSocket.close();
            serverSocket = null;
            state = 0;
        }
    }

    /**
     * Register thread in server.
     *
     * @param id    the id
     * @param state the state
     * @return the boolean
     */
    public static boolean register(String id, ClientState state) {
        if (ids.containsKey(id)) {
            return false;
        }

        ids.put(id, state);
        return true;
    }

    /**
     * Unregister thread
     *
     * @param id the id of the thread
     */
    public static void unregister(String id) {
        ids.remove(id);
    }

    /**
     * Return the clientState associated with this ID.
     *
     * @param id the id
     * @return the state
     */
    public static ClientState getState(String id) {
        return ids.get(id);
    }

    /**
     * Return the unique IDs for all connected clients.
     *
     * @return the collection
     */
    public static Collection<String> ids() {
        return ids.keySet();
    }
}
