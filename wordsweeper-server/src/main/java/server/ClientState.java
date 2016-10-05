package server;

import com.wordsweeper.server.model.Response;

/**
 * Server-side interface to per-client state. Exposes only the methods of ServerThread that are safe.
 */
public interface ClientState {

    /**
     * Send the given message to the client on whose behalf this thread is executing and return true
     * on success, false on error.
     */
    boolean sendMessage(Response response);

    /**
     * Associate new object data as the user-defined data for the thread and return the old previous value.
     */
    Object setData(Object newData);

    /**
     * Retrieve user-defined data associated with this thread.
     */
    Object getData();

    /**
     * Retrieve our unique id.
     */
    String id();

}