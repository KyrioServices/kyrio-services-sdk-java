package com.kyrio.services;

/**
 * Unique codes that define Kyrio error types.
 */
public  class ErrorCode {
    /**
     * No connection to Kyrio servers.
     */
    public static final String NO_CONNECTION = "NO_CONNECTION";

    /**
     * Kyrio was not able to authorize client by provided ID.
     */
    public static final String UNAUTHORIZED = "UNAUTHORIZED";

    /**
     * Request contained invalid parameters.
     */
    public static final String BAD_REQUEST = "BAD_REQUEST";

    /**
     * Cause of error was unknown.
     */
    public static final String UNKNOWN = "UNKNOWN";

    /**
     * Internal server error.
     */
    public static final String INTERNAL = "INTERNAL";

    /**
     * Response to server or MSOs failed after timeout.
     */
    public static final String TIMEOUT = "TIMEOUT";

    /**
     * Server is temporary unavailable due to maintenance.
     */
    public static final String MAINTENANCE = "MAINTENANCE";
}
