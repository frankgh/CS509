package server;

import com.wordsweeper.server.xml.Request;
import com.wordsweeper.server.xml.Response;

/**
 * ServerThread out-sources processing of protocol to implementors
 * of this interface. Implementor is only concerned with request and response messages.
 */
public interface IProtocolHandler {

    /**
     * Process the protocol given a Request received on behalf of the designated
     * thread. Return Response as the response to the request or null to indicate
     * that the connection to that client should be terminated.
     * <p>
     * ClientState object represents the client on whose behalf the request was received.
     * Use this object to write back a response.
     * <p>
     * Classes that implement this interface must make sure that the
     * {@link #process(ClientState, Request)} method is synchronized,
     * otherwise
     */
    Response process(ClientState state, Request request);
}