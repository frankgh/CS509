package com.wordsweeper.service.model;

/**
 * Wrapper class for Request Errors
 * <p>
 * @author francisco
 */
public class RequestError {

    /**
     * The constant NO_SUCH_GAME_EXISTS.
     */
    public final static int NO_SUCH_GAME_EXISTS = 404;

    /**
     * The constant GAME_IS_LOCKED.
     */
    public final static int GAME_IS_LOCKED = 403;

    /**
     * The constant INVALID_PASSWORD.
     */
    public final static int INVALID_PASSWORD = 403;

    /**
     * The constant PLAYER_ALREADY_EXISTS.
     */
    public final static int PLAYER_ALREADY_EXISTS = 400;

    /**
     * The constant NO_SUCH_PLAYER_EXISTS.
     */
    public final static int NO_SUCH_PLAYER_EXISTS = 400;

    /**
     * The constant UNAUTHORIZED.
     */
    public final static int UNAUTHORIZED = 401;

    /**
     * The constant INVALID_WORD.
     */
    public final static int INVALID_WORD = 405;

    /**
     * The constant BOARD_POSITION_NOT_MODIFIED.
     */
    public final static int BOARD_POSITION_NOT_MODIFIED = 304;

    /**
     * The Error code.
     */
    int statusCode;

    /**
     * The Error description.
     */
    String message;

    /**
     * Instantiates a new Request error.
     *
     * @param statusCode the error code
     * @param message    the message
     */
    public RequestError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    /**
     * Gets status code.
     *
     * @return the status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
