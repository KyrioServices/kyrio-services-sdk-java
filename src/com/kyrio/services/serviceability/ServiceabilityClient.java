package com.kyrio.services.serviceability;

import java.util.HashMap;
import java.util.Map;

import com.kyrio.services.KyrioAccount;
import com.kyrio.services.KyrioException;
import com.kyrio.services.shared.KyrioRestClient;
import com.kyrio.services.shared.Provider;
import com.kyrio.services.shared.RandomData;
import com.kyrio.services.shared.Address;

/**
 * Client to access Kyrio Serviceability API
 */
public class ServiceabilityClient extends KyrioRestClient {
    private static final String BASE_ROUTE = "/business/api/v1";

    /**
     * Default client constractor.
     * @param account a Kyrio account associated with this client.
     */
	public ServiceabilityClient(KyrioAccount account) {		
		super(account);
	}

	/**
	 * Determines cable providers that serve location specified by it's postal address.
	 * The method supports incomplete addresses: addressLine1 and postalCode
	 * or addressLine1, city and state.
	 * @param addressLine1 Street number, pre-directional, street name, suffix, post-directional.
	 * @param addressLine2 Secondary address line such as Apt, Suite or Lot.
	 * @param city City or town name.
	 * @param state For US addresses, use the standard 2-character state abbreviation.
	 * @param postalCode For US addresses, use the 5-digit ZIP code.
	 * @param countryCode Use ‘US’ to indicate US addresses.  If the argument is omitted, ‘US’ will be assumed. Refer to ISO 3166 Country Code Standardfor non-US addresses.
	 * @return Array of serviceability results from cable providers.
	 * @throws KyrioException returned by the server.
	 */
    public ServiceabilityResult[] determineBusinessServiceability(
        String addressLine1, String addressLine2, String city,
        String state, String postalCode, String countryCode) throws KyrioException {
        
    	Address address = new Address();
        address.setLine1(addressLine1);
        address.setLine2(addressLine2);
        address.setCity(city);
        address.setState(state);
        address.setPostalCode(postalCode);
        address.setCountryCode(countryCode);

        return determineBusinessServiceabilityForAddress(address);
    }

    /**
     * Determines cable providers that serve location specified by it's postal address.
     * @param address Location postal address.
     * @return Array of serviceability results from cable providers.
     * @throws KyrioException returned by the server.
     */
    public ServiceabilityResult[] determineBusinessServiceabilityForAddress(Address address)
    	throws KyrioException {
    	
        if (address == null)
            throw new NullPointerException("address cannot be null");

        // For local testing return mock without calling server
        if (_account.getEnableTestLocal())
            return mockDetermineBusinessServiceability(address);

        // Prepare invocation parameters
        String route = BASE_ROUTE + "/serviceability";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("address_line1", address.getLine1());
        parameters.put("address_line2", address.getLine2());
    	parameters.put("city", address.getCity());
		parameters.put("state", address.getState());
		parameters.put("postal_code", address.getPostalCode());
		parameters.put("country_code", address.getCountryCode());

        // Invoke operation on the server
        return invoke(ServiceabilityResult[].class, "GET", route, parameters, null);
    }

    /**
     * Generates random test serviceability response.
     * @param address Location postal address.
     * @return Array of serviceability results from cable providers.
     * @throws KyrioException returned by the server.
     */
    private ServiceabilityResult[] mockDetermineBusinessServiceability(Address address)
    	throws KyrioException {

    	// Delay the result for realistic behavior
    	try {
    		Thread.sleep(1500);
    	} catch (InterruptedException ex) {
    		// Ignore...
    	}

        // Simulate random errors
        if (this._account.getEnableTestError() && RandomData.chance(1, 100))
            throw RandomData.nextError();

        // Generate random results
        int resultCount = RandomData.nextInteger(0, 2);
        ServiceabilityResult[] results  = new ServiceabilityResult[resultCount];
        for (int index = 0; index < resultCount; index++) {
            Provider provider = RandomData.nextProvider();
            ServiceabilityResult result = new ServiceabilityResult();
            result.setLocationId("" + RandomData.nextInteger(99999));
            result.setLocationType(RandomData.pick(new LocationType[] {
                LocationType.Unknown, LocationType.Residential, LocationType.Business
            }));
            result.setProviderId(provider.getId());
            result.setProvider(provider.getName());
            result.setSiteStatus(RandomData.pick(new SiteStatus[] {
                SiteStatus.OnNet, SiteStatus.OffNet, SiteStatus.NearNet,
                SiteStatus.SurveyRequired, SiteStatus.Proximity
            }));
            results[index] = result;
        }

        return results;
    }
        
}
