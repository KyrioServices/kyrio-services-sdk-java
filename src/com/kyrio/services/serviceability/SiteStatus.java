package com.kyrio.services.serviceability;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SiteStatus {
    @JsonProperty("none")
    None,

    @JsonProperty("on_net")
    OnNet,

    @JsonProperty("off_net")
    OffNet,

    @JsonProperty("near_net")
    NearNet,

    @JsonProperty("survey_req")
    SurveyRequired,

    @JsonProperty("proximity")
    Proximity
}
