package com.kyrio.services.serviceability;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Defines types for servicable locations
 */
public enum LocationType {
	/**
	 * Location does not exist or not listed
	 */
    @JsonProperty("none")
    None,

    /**
     * Location type is unknown
     */
    @JsonProperty("unknown")
    Unknown,

    /**
     * Location is a business property
     */
    @JsonProperty("business")
    Business,

    /**
     * Location is a residential property
     */
    @JsonProperty("residential")
    Residential
}