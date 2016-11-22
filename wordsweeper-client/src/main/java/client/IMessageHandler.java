package client;

import xml.Message;

/**
 * This connect with message handler interface.
 */
public interface IMessageHandler {
	
  /** Process the protocol response*/
  void process(Message response);
}