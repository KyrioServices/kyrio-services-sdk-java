package com.kyrio.services.serviceability;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceabilityResult {
	private String _locationId;
	private LocationType _locationType;
	private String _providerId;
	private String _provider;
	private SiteStatus _siteStatus;
	
	public ServiceabilityResult() {}
	
    @JsonProperty("location_id")
    public String getLocationId() { return _locationId; }
    public void setLocationId(String value) { _locationId = value; }

    @JsonProperty("location_type")
    public LocationType getLocationType() { return _locationType; }
    public void setLocationType(LocationType value) { _locationType = value; }

    @JsonProperty("provider_id")
    public String getProviderId() { return _providerId; }
    public void setProviderId(String value) { _providerId = value; }

    @JsonProperty("provider")
    public String getProvider() { return _provider; }
    public void setProvider(String value) { _provider = value; }

    @JsonProperty("site_status")
    public SiteStatus getSiteStatus() { return _siteStatus; }
    public void setSiteStatus(SiteStatus value) { _siteStatus = value; }
}
