package com.wordsweeper.adminclient;

import com.wordsweeper.core.xml.Response;

/**
 * This interface create message handler.
 */
public interface IMessageHandler {

    void process(Response response);

}
