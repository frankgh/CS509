package com.wordsweeper.server.api.model;

/**
 * This class specifies API errors.
 * @author francisco
 */
public class APIError {
    /**
     * The Status code.
     */
    int statusCode;
    /**
     * The Message.
     */
    String message;

    /**
     * Instantiates a new Api error.
     */
    public APIError() {
    }

    /**
     * Status int.
     *
     * @return the int
     */
    public int status() {
        return statusCode;
    }

    /**
     * Message string.
     *
     * @return the string
     */
    public String message() {
        return message;
    }
}
