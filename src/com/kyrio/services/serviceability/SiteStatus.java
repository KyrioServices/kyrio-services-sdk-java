package com.kyrio.services.serviceability;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Determines service status of a site (location).
 */
public enum SiteStatus {
    /**
     * Indicates that cable services are not available
     */
    @JsonProperty("none")
    None,

    /**
     * Indicates that one or more cable services are currently available at the address
     */
    @JsonProperty("on_net")
    OnNet,

    /**
     * Indicates the MSO has previously surveyed the site and determined it cannot be served
     */
    @JsonProperty("off_net")
    OffNet,

    /**
     * Indicates the address is near existing cable infrastructure and can likely be served at a reasonable cost/effort
     */
    @JsonProperty("near_net")
    NearNet,

    /**
     * Indicates the MSO must conduct additional analysis to determine if the site can be served
     */
    @JsonProperty("survey_req")
    SurveyRequired,

    /**
     * Indicates that the MSO serves the general area such as the 5-digit ZIP code or locations
     * within a configurable distance (such as 60-feet or 200-feet). 
     * he MSO should be contacted for additional information about the serviceability of a location identified as ‘proximity’.
     */
    @JsonProperty("proximity")
    Proximity
}
