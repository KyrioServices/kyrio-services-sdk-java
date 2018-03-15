package com.kyrio.services.shared;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
    private String _line1;
    private String _line2;
    private String _city;
    private String _state;
    private String _postalCode;
    private String _countryCode;

    public Address() {}
    
    @JsonProperty("line1")
    public String getLine1() { return _line1; }
    public void setLine1(String value) { _line1 = value; }

    @JsonProperty("line2")
    public String getLine2() { return _line2; }
    public void setLine2(String value) { _line2 = value; }

    @JsonProperty("city")
    public String getCity() { return _city; }
    public void setCity(String value) { _city = value; }

    @JsonProperty("state")
    public String getState() { return _state; }
    public void setState(String value) { _state = value; }

    @JsonProperty("postal_code")
    public String getPostalCode() { return _postalCode; }
    public void setPostalCode(String value) { _postalCode = value; }

    @JsonProperty("country_code")
    public String getCountryCode() { return _countryCode; }
    public void setCountryCode(String value) { _countryCode = value; }
}