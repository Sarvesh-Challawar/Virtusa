package com.mvc.bean;

import java.util.Date;

public class MoveBean {

	private String location;
	
	private int id;
	
	private int movecount;
	
	private Date expirydate;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMovecount() {
		return movecount;
	}

	public void setMovecount(int movecount) {
		this.movecount = movecount;
	}

	public Date getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
	}
	
}
