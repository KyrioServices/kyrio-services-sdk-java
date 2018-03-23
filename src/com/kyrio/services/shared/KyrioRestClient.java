package com.kyrio.services.shared;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.security.KeyManagementException;
import java.security.cert.X509Certificate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.kyrio.services.ErrorCode;
import com.kyrio.services.KyrioAccount;
import com.kyrio.services.KyrioException;

/**
 * Abstract implementation of REST clients to call Kyrio services.
 */
public abstract class KyrioRestClient {
	private static ObjectMapper _mapper = new ObjectMapper();
	//private static TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {};
	
	static {
		_mapper.findAndRegisterModules();
		//_mapper.registerModule(new JavaTimeModule());
		_mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}

	/**
	 * Kyrio account associated with this client
	 */
	protected KyrioAccount _account;

	/**
	 * Constructs this client and sets initial values.
	 * @param account A Kyrio account associated with his client.
	 */
    public KyrioRestClient(KyrioAccount account) {
        if (account == null)
            throw new NullPointerException("account cannot be null");

        _account = account;
    }
    
    private static SSLContext createSSLContext() {
   	 	try {
    	 TrustManager[] dummyTrustManager = new TrustManager[] { new X509TrustManager() {
    	      public java.security.cert.X509Certificate[] getAcceptedIssuers() {
    	        return null;
    	      }

    	      public void checkClientTrusted(X509Certificate[] certs, String authType) {
    	      }

    	      public void checkServerTrusted(X509Certificate[] certs, String authType) {
    	      }
    	    }
    	 };
    	 
    	 	SSLContext context = SSLContext.getInstance("SSL");
    	 	context.init(null, dummyTrustManager, new java.security.SecureRandom());
    	 	return context;
    	} catch (Exception ex) {
    		// This is never expected to happen
    		return null;
    	}
    }
    
    private static HostnameVerifier createHostnameVerifier() {
    	HostnameVerifier verifier = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        return verifier;
    }
    
    /**
     * Creates HTTP client and sets default headers for all REST calls.
     * @param url request URL
     * @param method Operation method: GET, POST, PUT or DELETE
     * @return Created HTTP client
     * @throws IOException when something goes wrong.
     */
    private HttpURLConnection createClient(URL url, String method)
		throws IOException {

    	// Disable SSL certificate
    	if (url.getProtocol().equals("https")) {
    		SSLContext context = createSSLContext();
    		if (context != null)
    			HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
    		
    		HostnameVerifier verifier = createHostnameVerifier();
    		HttpsURLConnection.setDefaultHostnameVerifier(verifier);    	
    	}
    	
    	HttpURLConnection client = (HttpURLConnection) url.openConnection();
        
    	method = method.toUpperCase();
    	client.setRequestMethod(method);
    	
    	client.setRequestProperty("accept", "application/json");
        client.setRequestProperty("client-id", _account.getClientId());
        client.setRequestProperty("enable-test-mock", _account.getEnableTestMock() ? "true" : "false");
        client.setRequestProperty("enable-test-error", _account.getEnableTestError() ? "true" : "false");
        
        return client;
    }

    /**
     * Composes service URL based on server URL, operation route and parameters.
     * @param route Base operation route
     * @param parameters Operation query parameters
     * @return Operation URI
     * @throws IOException when something goes wrong
     */
    private URL composeRequestUri(String route, Map<String, Object> parameters)
    	throws IOException {
        StringBuilder builder = new StringBuilder(_account.getServerUrl());

        if (!route.startsWith("/"))
            builder.append("/");

        builder.append(route);

        String queryParams = composeQueryParams(parameters);
        builder.append(queryParams);

        String uri = builder.toString();
        return new URL(uri);
    }

    /**
     * Composes query parameters into encoded string.
     * @param parameters Operation query parameters
     * @return Encoded query parameter string
     * @throws UnsupportedEncodingException should never happen.
     */
    private String composeQueryParams(Map<String, Object> parameters)
    	throws UnsupportedEncodingException {
    	
        if (parameters == null) return "";

        StringBuilder builder = new StringBuilder();

        for (String key : parameters.keySet()) {
            if (builder.length() == 0)
                builder.append('?');
            else builder.append('&');

            builder.append(key);
            builder.append('=');

            String value = URLEncoder.encode("" + parameters.get(key), "UTF-8");
            builder.append(value);
        }

        return builder.toString();
    }

    /**
     * Writes request content object with JSON representation of send data.
     * @param client HTTP client
     * @param value Value to be passed in request body.
     * @throws IOException when communication fails.
     */
    private void writeRequestContent(HttpURLConnection client, Object value)
		throws IOException {
        
    	if (value == null) {
    		client.setDoOutput(false);
    		return;
    	}

		client.setDoOutput(true);
        OutputStream stream = client.getOutputStream();
        try {
        	String content = _mapper.writeValueAsString(value);
        	stream.write(content.getBytes());
        	stream.flush();
        } finally {
        	stream.close();
        }
    }

    /**
     * Reads JSON response string from response body.
     * @param client HTTP client.
     * @return a JSON string with response body.
     * @throws IOException when communication fails.
     */
    private String readResponseContent(HttpURLConnection client)
    	throws IOException {
    	    	
    	// Hack to force HttpURLConnection to run the request
    	// Otherwise getErrorStream always returns null
    	InputStream stream = client.getErrorStream();
    	if (stream == null)
    		stream = client.getInputStream();
    	
    	BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
    	
    	try {
    		StringBuilder builder = new StringBuilder();
    		String line = null;
    		while ((line = reader.readLine()) != null) {
    			builder.append(line);
    		}
    		return builder.toString();
    	} finally {
    		stream.close();
    		reader.close();
    	}
    }
    
    /**
     * Parses response body into specified object type
     * @param type Expected type of response object
     * @param client HTTP client
     * @return Parsed response object of specified type or <code>null</code>
     * @throws KyrioException returned by Kyrio server
     * @throws IOException when communication fails
     */
    private <T> T parseResponseContent(Class<T> type, HttpURLConnection client)
    	throws KyrioException, IOException {        
        try {
            String content = readResponseContent(client);
            if (content == null || content.length() == 0) return null;

            T result = _mapper.readValue(content, type);

            return result;
        } catch (Exception ex) {
            throw new KyrioException(ErrorCode.UNKNOWN, client.getResponseCode(), ex.getMessage(), ex);
        }
    }

    /**
     * Parses response body into error message string
     * @param client HTTP client
     * @return Parsed response error message or <code>null</code>
     * @throws KyrioException returned by Kyrio server
     * @throws IOException when communication fails
     */
    private String parseResponseError(HttpURLConnection client)
    	throws KyrioException, IOException {        
        try {
            String content = readResponseContent(client);
            if (content == null || content.length() == 0) return null;

            String result = _mapper.readValue(content, String.class);

            return result;
        } catch (Exception ex) {
            throw new KyrioException(ErrorCode.UNKNOWN, client.getResponseCode(), ex.getMessage(), ex);
        }
    }
    
    /**
     * Invokes REST operation on the server and handles the response.
     * @param type Expected type of response object.
     * @param method Operation method: GET, POST, PUT or DELETE
     * @param route Operation base route.
     * @param parameters Operation query parameter.
     * @param body Value to be sent in request body.
     * @return Value returned by the server of expected type.
     * @throws KyrioException returned by Kyrio server.
     */
    protected <T> T invoke(Class<T> type, String method, String route,
		Map<String, Object> parameters, Object body) throws KyrioException {

        if (_account.getClientId() == null || _account.getClientId().length() == 0)
            throw new IllegalArgumentException("ClientId is not set");
        if (_account.getServerUrl() == null || _account.getServerUrl().length() == 0)
            throw new IllegalArgumentException("ServerUrl is not set");
        if (method == null || method.length() == 0)
            throw new NullPointerException("method cannot be null");
        if (route == null || route.length() == 0)
            throw new NullPointerException("route cannot be null");

        try {
	        URL requestUri = composeRequestUri(route, parameters);
	        HttpURLConnection client = createClient(requestUri, method);
	        
	        writeRequestContent(client, body);
		
            switch (client.getResponseCode()) {
                case HttpURLConnection.HTTP_OK:
                case HttpURLConnection.HTTP_CREATED:
                    return parseResponseContent(type, client);
                case HttpURLConnection.HTTP_NO_CONTENT:
                    return null;
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    throw new KyrioException(ErrorCode.BAD_REQUEST, client.getResponseCode(), parseResponseError(client));
                case HttpURLConnection.HTTP_UNAUTHORIZED:
                    throw new KyrioException(ErrorCode.UNAUTHORIZED, client.getResponseCode(), parseResponseError(client));
                case HttpURLConnection.HTTP_INTERNAL_ERROR:
                    throw new KyrioException(ErrorCode.INTERNAL, client.getResponseCode(), parseResponseError(client));
                case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
                    throw new KyrioException(ErrorCode.TIMEOUT, client.getResponseCode(), parseResponseError(client));
                default:
                    throw new KyrioException(ErrorCode.UNKNOWN, client.getResponseCode(), readResponseContent(client));
            }
        } catch (IOException ex) {
            throw new KyrioException(ErrorCode.NO_CONNECTION, 0, ex.getMessage(), ex);        	
        }
    }
}
