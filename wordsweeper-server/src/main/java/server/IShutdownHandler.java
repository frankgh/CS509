package server;

/**
 * If you want to be told when a client exits, make sure your ProtocolHandler
 * implements {@link IShutdownHandler}.
 * <p>
 */
public interface IShutdownHandler {

    /**
     * When client terminates connection, this method is invoked, but only if
     * the ProtocolHandler also implements IShutdownHandler.
     * <p>
     * Parameter is the state of the thread being terminated.
     */
    void logout(ClientState state);
}