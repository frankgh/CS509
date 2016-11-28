package com.wordsweeper.adminclient;

import xml.Message;

/**
 * This interface create message handler.
 */
public interface IMessageHandler {
	
	void process(Message response);

}
