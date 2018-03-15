package com.kyrio.services.shared;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Location postal address.
 */
public class Address {
    private String _line1;
    private String _line2;
    private String _city;
    private String _state;
    private String _postalCode;
    private String _countryCode;

    /**
     * Default constructor
     */
    public Address() {}
    
    /**
     * Gets a street number, pre-directional, street name, suffix, post-directional
     * @return a street name and number.
     */
    @JsonProperty("line1")
    public String getLine1() { return _line1; }
    
    /**
     * Sets a street number, pre-directional, street name, suffix, post-directional
     * @param value a street name and number.
     */
    public void setLine1(String value) { _line1 = value; }

    /**
     * Gets a secondary address line such as Apt, Suite or Lot
     * @return an apartment, suite or lot number.
     */
    @JsonProperty("line2")
    public String getLine2() { return _line2; }
    
    /**
     * Sets a secondary address line such as Apt, Suite or Lot
     * @param value an apartment, suite or lot number.
     */
    public void setLine2(String value) { _line2 = value; }

    /**
     * Gets a city or town name.
     * @return a city or town name.
     */
    @JsonProperty("city")
    public String getCity() { return _city; }
    
    /**
     * Sets a city or town name.
     * @param value a city or town name.
     */
    public void setCity(String value) { _city = value; }

    /**
     * Gets a state abbreviation or name.
     * @return a state abbreviation of name.
     */
    @JsonProperty("state")
    public String getState() { return _state; }
    
    /**
     * Sets a state abbreviation or name.
     * For US addresses, use the standard 2-character state abbreviation.
     * @param value a state abbreviation or name.
     */
    public void setState(String value) { _state = value; }

    /**
     * Gets postal or zip code.
     * @return a postal or zip code.
     */
    @JsonProperty("postal_code")
    public String getPostalCode() { return _postalCode; }
    
    /**
     * Sets a postal or zip code.
     * For US addresses, use the 5-digit ZIP code.
     * @param value a postal or zip code.
     */
    public void setPostalCode(String value) { _postalCode = value; }

    /**
     * Gets a country code.
     * @return a country code.
     */
    @JsonProperty("country_code")
    public String getCountryCode() { return _countryCode; }
    
    /**
     * Sets a country code.
     * Use ‘US’ to indicate US addresses.  If the argument is omitted, ‘US’ will be assumed.
     * Refer to ISO 3166 Country Code Standard for non-US addresses.
     * @param value a country code.
     */
    public void setCountryCode(String value) { _countryCode = value; }
}