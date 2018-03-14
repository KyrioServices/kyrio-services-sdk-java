package com.kyrio.services.serviceability;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum LocationType {
    @JsonProperty("none")
    None,

    @JsonProperty("unknown")
    Unknown,

    @JsonProperty("business")
    Business,

    @JsonProperty("residential")
    Residential
}