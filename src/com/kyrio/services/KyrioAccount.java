package com.kyrio.services;

import com.kyrio.services.serviceability.ServiceabilityClient;

public class KyrioAccount {
    private static final String PROD_SERVER_URL = "https://api.kyrioconnectionsuite.com";
    private static final String QA_SERVER_URL = "https://api.qa.kyrioconnectionsuite.com";

    private static final String CLIENT_ID_REGEX = "\\d{6}";
    private static final String SERVER_URL_REGEX = "(https?:\\/\\/)?[\\w -]+(\\.[\\w-]+)+\\.?(:\\d+)?";

    private String _clientId;
    private String _serverUrl = "http://localhost:7277";
    private boolean _enableTestLocal = false;
    private boolean _enableTestMock = false;
    private boolean _enableTestError = false;
    private boolean _enableQaEnvironment = false;

    public KyrioAccount() { }

    public String getClientId() { return _clientId; }

    public void setClientId(String value) {
        if (value == null || value.length() == 0)
            throw new NullPointerException("ClientId cannot be empty");
        if (!value.matches(CLIENT_ID_REGEX))
            throw new IllegalArgumentException("ClientId must be 6 digits long");

        _clientId = value;
    }

    public String getServerUrl() {
        if (_serverUrl != null)
            return _serverUrl;
        else if (_enableQaEnvironment)
            return KyrioAccount.QA_SERVER_URL;
        else
            return KyrioAccount.PROD_SERVER_URL;
    }

    public void setServerUrl(String value) {
        if (value == "")
            throw new IllegalArgumentException("ServerUrl cannot be empty");
        if (!value.matches(SERVER_URL_REGEX))
            throw new IllegalArgumentException("ServerUrl must be set as https://<host>[:<port>]");

        _serverUrl = value;
    }

    public boolean getEnableTestLocal() { return _enableTestLocal; }
    public void setEnableTestLocal(boolean value) { _enableTestLocal = value; }

    public boolean getEnableTestMock() { return _enableTestMock; }
    public void setEnableTestMock(boolean value) { _enableTestMock = value; }

    public boolean getEnableTestError() { return _enableTestError; }
    public void setEnableTestError(boolean value) { _enableTestError = value; }

    public boolean getEnableQaEnvironment() { return _enableQaEnvironment; }
    public void setEnableQaEnvironment(boolean value) { _enableQaEnvironment = value; }

    public ServiceabilityClient createServiceabilityClient() {
        return new ServiceabilityClient(this);
    }    
}
