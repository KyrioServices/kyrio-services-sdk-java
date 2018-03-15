package com.kyrio.services;

/**
 * Exception that represents error responses from Kyrio Online services
 */
public class KyrioException extends Exception {
	private static final long serialVersionUID = 7275907419883122229L;

	private String _code;
	private int _status;
	
	/**
	 * Creates Kyrio exception with message.
	 * @param message Textual description of the error.
	 */
    public KyrioException(String message) { 
    	super(message);
    }

    /**
     * Creates Kyrio exception with detail information.
     * @param code Unique code that defines error type.
     * @param status HTTP response code returned by server.
     * @param message Textual descriotion of the error.
     */
    public KyrioException(String code, int status, String message) {
    	super(message);
    	
        _code = code;
        _status = status;
    }

    /**
     * Creates Kyrio exception with detail information and original exception.
     * @param code Unique code that defines error type.
     * @param status HTTP response code returned by server.
     * @param message Textual descriotion of the error.
     * @param cause Original exception.
     */
    public KyrioException(String code, int status, String message, Throwable cause) {
    	super(message, cause);
    	
        _code = code;
        _status = status;
    }

    /**
     * Gets a unique code that defines error type.
     * @return an error code.
     */
    public String getCode() { return _code; } 
    
    /**
     * Gets HTTP response code returned by server.
     * @return HTTP status code.
     */
    public int getStatus() { return _status; }
}
