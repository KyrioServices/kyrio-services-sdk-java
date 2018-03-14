package com.kyrio.services.serviceability;

import org.junit.*;

import com.kyrio.services.KyrioAccount;

import static org.junit.Assert.*;

public class ServiceabilityClientTest {
    @Test
    public void testDetermineBusinessServiceabilityMock() throws Exception {
        KyrioAccount account = new KyrioAccount();
        account.setClientId("999999");
        account.setEnableTestLocal(true);

        ServiceabilityClient client = account.createServiceabilityClient();
        ServiceabilityResult[] results = client.determineBusinessServiceability(
            "858 Coal Creek Circle", null, "Louisville", "CO", "80027", "US"
        );

        assertNotNull(results);
    }

    @Test
    public void testDetermineBusinessServiceability() throws Exception {
        KyrioAccount account = new KyrioAccount();
        account.setClientId("999999");
        account.setEnableTestMock(true);
        account.setEnableTestError(false);

        ServiceabilityClient client = account.createServiceabilityClient();
        ServiceabilityResult[] results = client.determineBusinessServiceability(
            "858 Coal Creek Circle", null, "Louisville", "CO", "80027", "US"
        );

        assertNotNull(results);
    }
}
