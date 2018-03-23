package com.kyrio.services;

import com.kyrio.services.serviceability.ServiceabilityClient;

/**
 * Account to Kyrio Online Services. It is used to set common connection properties and create clients to access individual services.
 */
public class KyrioAccount {
    private static final String PROD_SERVER_URL = "https://api.kyrioconnectionsuite.com";
    private static final String QA_SERVER_URL = "https://api.qa.kyrioconnectionsuite.com";

    private static final String CLIENT_ID_REGEX = "\\d{6}";
    private static final String SERVER_URL_REGEX = "(https?:\\/\\/)?[\\w -]+(\\.[\\w-]+)+\\.?(:\\d+)?";

    private String _clientId;
    private String _serverUrl;
    private boolean _enableTestLocal = false;
    private boolean _enableTestMock = false;
    private boolean _enableTestError = false;
    private boolean _enableQaEnvironment = false;

    /**
     * Default account constructor
     */
    public KyrioAccount() { }

    /**
     * Gets identifier to confirm client who accesses the API.
     * @returns A unique client ID
     */
    public String getClientId() { return _clientId; }

    /**
     * Sets identifier to confirm client who accesses the API.
     * Usually it is set as 6 digit number.
     * @param value A unique client ID
     */
    public void setClientId(String value) {
        if (value == null || value.length() == 0)
            throw new NullPointerException("ClientId cannot be empty");
        if (!value.matches(CLIENT_ID_REGEX))
            throw new IllegalArgumentException("ClientId must be 6 digits long");

        _clientId = value;
    }

    /**
     * Gets base url to connect to Kyrio servers.
     * @returns A server URL to connect to
     */
    public String getServerUrl() {
        if (_serverUrl != null)
            return _serverUrl;
        else if (_enableQaEnvironment)
            return KyrioAccount.QA_SERVER_URL;
        else
            return KyrioAccount.PROD_SERVER_URL;
    }

    /**
     * Sets base url to connect to Kyrio servers.
     * It is an optional property. It is set automatically based on EnableQaEnvironment.
     * But user is able to override it.
     * @param value A server URL to connect to
     */
    public void setServerUrl(String value) {
        if (value == "")
            throw new IllegalArgumentException("ServerUrl cannot be empty");
        if (!value.matches(SERVER_URL_REGEX))
            throw new IllegalArgumentException("ServerUrl must be set as https://<host>[:<port>]");

        _serverUrl = value;
    }

    /**
     * Checks if enabled local test calls to return random responses.
     * @returns <code>true</code> if local test calls are enabled or <code>false</code> otherwise
     */
    public boolean getEnableTestLocal() { return _enableTestLocal; }

    /**
     * Enables local test calls and returns random responses.
     * This allows to avoid roundtrips to Kyrio servers and incurring changes for API use.
     * The responses are delayed for 1.5 sec to make them more realistic.
     * This property works together with EnableTestError.
     * @param value <code>true</code> if local test calls are enabled or <code>false</code> otherwise
     */
    public void setEnableTestLocal(boolean value) { _enableTestLocal = value; }

    /**
     * Checks if enabled remote test calls to return random responses.
     * @returns <code>true</code> if remote test calls are enabled or <code>false</code> otherwise
     */
    public boolean getEnableTestMock() { return _enableTestMock; }

    /**
     * Enables remote test calls and returns random responses.
     * With this property enabled client makes calls to Kyrio servers without changes for API use.
     * This property works together with EnableTestError.
     * @param value <code>true</code> if remote test calls are enabled or <code>false</code> otherwise
     */
    public void setEnableTestMock(boolean value) { _enableTestMock = value; }

    /**
     * Checks if enabled random errors while making test calls.
     * @returns <code>true</code> if random test errors are enabled or <code>false</code> otherwise
     */
    public boolean getEnableTestError() { return _enableTestError; }

    /**
     * Enables random errors while making test calls.
     * The errors simulate Internal (500) or Timeout (504) responses with 1% probability.
     * This property works together with EnableTestLock and EnableTestMock.
     * @param value <code>true</code> if random test errors are enabled or <code>false</code> otherwise
     */
    public void setEnableTestError(boolean value) { _enableTestError = value; }

    /**
     * Checks if enabled calls to QA environment.
     * @returns <code>true</code> to connect to QA servers or <code>false</code> to connect to Production servers
     */
    public boolean getEnableQaEnvironment() { return _enableQaEnvironment; }

    /**
     * Enables calls to QA environment and sets default ServerUrl to QA servers.
     * In the future this property can be deprecated.
     * @param value <code>true</code> to connect to QA servers or <code>false</code> to connect to Production servers
     */
    public void setEnableQaEnvironment(boolean value) { _enableQaEnvironment = value; }

    /**
     * Creates client to access Kyrio Serviceability API.
     * @returns Client to access Kyrio Serviceability API.
     */
    public ServiceabilityClient createServiceabilityClient() {
        return new ServiceabilityClient(this);
    }    
}
