package com.wordsweeper.service.model;

/**
 * Wrapper class for Request Errors
 * <p>
 * Created by francisco on 11/7/16.
 *
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
     * The Error code.
     */
    int errorCode;

    /**
     * The Error description.
     */
    String errorDescription;

    /**
     * Instantiates a new Request error.
     *
     * @param errorCode        the error code
     * @param errorDescription the error description
     */
    public RequestError(int errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    /**
     * Gets error code.
     *
     * @return the error code
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Gets error description.
     *
     * @return the error description
     */
    public String getErrorDescription() {
        return errorDescription;
    }
}
