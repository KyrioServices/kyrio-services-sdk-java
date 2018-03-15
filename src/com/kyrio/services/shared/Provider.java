package com.kyrio.services.shared;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Cable service provider (also known as MSO)
 */
public class Provider {
	private String _id;
	private String _name;
	
	/**
	 * Default constructor
	 */
	public Provider() {}
	
	/**
	 * Constructs provider instance and sets initial values
	 * @param id a provider unique ID.
	 * @param name a provider company name.
	 */
	public Provider(String id, String name) {
		_id = id;
		_name = name;
	}
	
	/**
	 * Gets a 4-digit identifier will be returned for each provider. 
	 * @return a provider unique ID.
	 */
	@JsonProperty("id")
    public String getId() { return _id; }
	
	/**
	 * Sets a 4-digit identifier will be returned for each provider. 
	 * @param value a provider unique ID.
	 */
    public void setId(String value) { _id = value; }

    /**
     * Gets a company name associated with the provider id.
     * @return a provider company name.
     */
    @JsonProperty("name")
    public String getName() { return _name; }
    
    /**
     * Sets a company name associated with the provider id.
     * @param value a provider company name.
     */
    public void setName(String value) { _name = value; }
}
