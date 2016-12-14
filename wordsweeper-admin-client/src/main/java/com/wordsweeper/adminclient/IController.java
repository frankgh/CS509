package com.wordsweeper.adminclient;

import com.wordsweeper.core.xml.Request;
import com.wordsweeper.core.xml.Response;

/**
 * Specialized client-side controller that is asked to respond to a specific response Message
 * received from the server in response to one of our client's request Message
 * <p>
 * A response is detected by comparing the IDs of a request sent by the client with the ID of
 * a response received from the server.
 */

public interface IController {

    /**
     * Process the server response given the original request.
     *
     * @param request  The message that this client had originally sent
     * @param response The response received in response to the request.
     */
    void process(Request request, Response response);

}
