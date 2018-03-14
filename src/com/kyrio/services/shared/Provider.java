package com.kyrio.services.shared;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Provider {
	private String _id;
	private String _name;
	
	public Provider() {}
	
	public Provider(String id, String name) {
		_id = id;
		_name = name;
	}
	
	@JsonProperty("id")
    public String getId() { return _id; }
    public void setId(String value) { _id = value; }

    @JsonProperty("name")
    public String getName() { return _name; }
    public void setName(String value) { _name = value; }
}
