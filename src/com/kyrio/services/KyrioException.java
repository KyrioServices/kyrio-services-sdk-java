package com.kyrio.services;

public class KyrioException extends Exception {
	private static final long serialVersionUID = 7275907419883122229L;

	private String _code;
	private int _status;
	
    public KyrioException(String message) { 
    	super(message);
    }

    public KyrioException(String code, int status, String message) {
    	super(message);
    	
        _code = code;
        _status = status;
    }

    public KyrioException(String code, int status, String message, Throwable cause) {
    	super(message, cause);
    	
        _code = code;
        _status = status;
    }

    public String getCode() { return _code; }    
    public int getStatus() { return _status; }
}
