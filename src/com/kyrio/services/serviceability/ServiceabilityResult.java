package com.kyrio.services.serviceability;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response from cable provider with service status for requested location.
 */
public class ServiceabilityResult {
	private String _locationId;
	private LocationType _locationType;
	private String _providerId;
	private String _provider;
	private SiteStatus _siteStatus;
	
	/**
	 * Default constructor
	 */
	public ServiceabilityResult() {}
	
	/**
	 * Gets unique location identifier (aka housekey) for the address.
	 * @return unique location ID
	 */
    @JsonProperty("location_id")
    public String getLocationId() { return _locationId; }
    
    /**
     * Sets unique location identifier (aka housekey) for the address.
     * @param value unique location ID
     */
    public void setLocationId(String value) { _locationId = value; }

    /**
     * Gets a location type that contain the value ‘residential’ or ‘business’
     * if the provider characterizes the location.
     * Otherwise a value of ‘unknown’ will be returned.
     * @return a location type
     */
    @JsonProperty("location_type")
    public LocationType getLocationType() { return _locationType; }
    
    /**
     * Sets a location type.
     * @param value a location type.
     */
    public void setLocationType(LocationType value) { _locationType = value; }

    /**
     * Gets a 4-digit identifier will be returned for each provider.
     * @return a provider unique ID
     */
    @JsonProperty("provider_id")
    public String getProviderId() { return _providerId; }
    
    /**
     * Sets a 4-digit identifier will be returned for each provider.
     * @param value a provider unique ID
     */
    public void setProviderId(String value) { _providerId = value; }

    /**
     * Gets company name associated with the provider id.
     * @return a provider company name
     */
    @JsonProperty("provider")
    public String getProvider() { return _provider; }
    
    /**
     * Sets a company name associated with the provider id.
     * @param value a provider company name
     */
    public void setProvider(String value) { _provider = value; }

    /**
     * Gets a service status for the location (site).
     * @return a site status
     */
    @JsonProperty("site_status")
    public SiteStatus getSiteStatus() { return _siteStatus; }
    
    /**
     * Sets a service status for the location (site).
     * @param value a site status
     */
    public void setSiteStatus(SiteStatus value) { _siteStatus = value; }
}
