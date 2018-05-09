package com.orbc.synergy.menumgmt.dto;

import java.util.List;

public class Resource {

	private int id;
	private String name;
	private String resourceDesc;
	private String url;
	private String type;
	private int active;
	private List<String> tags;
	
	private String version;
	private List<String> accessPoints;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResourceDesc() {
		return resourceDesc;
	}

	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<String> getAccessPoints() {
		return accessPoints;
	}

	public void setAccessPoints(List<String> accessPoints) {
		this.accessPoints = accessPoints;
	}
	
}
