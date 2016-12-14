package com.wordsweeper.server.model;

import com.wordsweeper.core.xml.Response;

/**
 * Server-side interface to per-client state. Exposes only the methods of ServerThread that are safe.
 *
 * @author heineman
 */
public interface ClientState {

    /**
     * Send the given message to the client on whose behalf this thread is executing and return true
     * on success, false on error.
     *
     * @param response the response
     * @return the boolean
     */
    boolean sendMessage(Response response);

    /**
     * Associate new object data as the user-defined data for the thread and return the old previous value.
     *
     * @param newData the new data
     * @return the data
     */
    Object setData(Object newData);

    /**
     * Retrieve user-defined data associated with this thread.
     *
     * @return the data
     */
    Object getData();

    /**
     * Retrieve our unique id.
     *
     * @return the string
     */
    String id();

}