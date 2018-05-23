package com.orbc.syn.menumgmt.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenInfo {
	
	private long userId;
	private String userName;
	private String issued;
	private String expiry;
	
	
	@JsonProperty("usr_id")
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	@JsonProperty("usr_nm")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIssued() {
		return issued;
	}
	public void setIssued(String issued) {
		this.issued = issued;
	}
	public String getExpiry() {
		return expiry;
	}
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
	

}
