package com.kyrio.services;

import org.junit.*;

import com.kyrio.services.serviceability.ServiceabilityClient;

import static org.junit.Assert.*;

public class KyrioAccountTest {
    @Test
    public void testSetServerUrl() {
        KyrioAccount account = new KyrioAccount();
        account.setServerUrl("https://api.kyrio.com:8080");
        assertEquals("https://api.kyrio.com:8080", account.getServerUrl());

        try {
            account.setServerUrl("xyz");
            fail("Must validate serverUrl");
        } catch (Exception ex) {
            // Expected exception
        }

        try {
            account.setServerUrl(null);
            fail("Must validate serverUrl");
        } catch (Exception ex) {
            // Expected exception
        }
    }

    @Test
    public void testSetClientId() {
        KyrioAccount account = new KyrioAccount();
        account.setServerUrl("https://api.kyrio.com:8080");
        assertEquals("https://api.kyrio.com:8080", account.getServerUrl());

        try {
            account.setServerUrl("xyz");
            fail("Must validate serverUrl");
        } catch (Exception ex) {
            // Expected exception
        }

        try {
            account.setServerUrl(null);
            fail("Must validate serverUrl");
        } catch (Exception ex) {
            // Expected exception
        }
    }

    @Test
    public void testSetTestProperties() {
        KyrioAccount account = new KyrioAccount();
        account.setClientId("123456");

        account.setEnableTestError(true);
        assertTrue(account.getEnableTestError());

        account.setEnableTestMock(true);
        assertTrue(account.getEnableTestMock());

        account.setEnableTestLocal(true);
        assertTrue(account.getEnableTestLocal());
    }

    @Test
    public void testCreateServiceabilityClient() {
        KyrioAccount account = new KyrioAccount();
        account.setClientId("123456");

        ServiceabilityClient client = account.createServiceabilityClient();
        assertNotNull(client);
    }
}
