package com.orbc.syn.menumgmt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="resource")
public class Resource {
	@Id
	@Column(name="resource_id")
	@GeneratedValue
	private int id;
	public int getId() {
		return id;
	}
	@Column(name="resource_name")
	private String name;
	@Column(name="resource_desc")
	private String resourceDesc;
	@Column(name="url")
	private String url;
	@Column(name="integration_type")
	private String type;
	@Column(name="active")
	private String active;
	@Column(name="tag")
	private String tags;
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the resourceDesc
	 */
	public String getResourceDesc() {
		return resourceDesc;
	}
	/**
	 * @param resourceDesc the resourceDesc to set
	 */
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
	public void setId(int id) {
		this.id = id;
	}
	
	
}
