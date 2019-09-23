package com.rmi.server.entity;

import java.util.Date;


public class RoomInfoFlowIdEntity implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id; 

    private String guid;  

    private String processInstanceId;

	private String openId;

	private String currentOpenId;

	private String type;

	private String applicationUser;

	private Integer result;

	private String address;

	private Date currentDate;

	private Date date;

	private Integer state;
	
    public Integer getId() {  
        return id;  
    }

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getApplicationUser() {
		return applicationUser;
	}

	public void setApplicationUser(String applicationUser) {
		this.applicationUser = applicationUser;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCurrentOpenId() {
		return currentOpenId;
	}

	public void setCurrentOpenId(String currentOpenId) {
		this.currentOpenId = currentOpenId;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
}