package com.kyrio.services.samples;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyrio.services.KyrioAccount;
import com.kyrio.services.serviceability.ServiceabilityClient;
import com.kyrio.services.serviceability.ServiceabilityResult;

public class ServiceabilitySample {

	public static void main(String[] args) {
        KyrioAccount account = new KyrioAccount();
        account.setClientId("999999");

        ServiceabilityClient client = account.createServiceabilityClient();
        try
        {
            ServiceabilityResult[] result = client.determineBusinessServiceability(
                "858 Coal Creek Circle", null, "Louisville", "CO", "80027", "US"
            );

            String json = new ObjectMapper().writeValueAsString(result);
            System.out.println(json);
        } catch (Exception ex) {
            System.err.println("Failed to call serviceability API");
            System.err.println(ex);
        }		
	}
	
}
