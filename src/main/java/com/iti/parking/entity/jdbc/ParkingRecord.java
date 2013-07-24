package com.iti.parking.entity.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.iti.parking.util.ConnectionFactory;

public class ParkingRecord {
	Connection connection = ConnectionFactory.getConnection();

	Integer id;
	Integer parkingID;
	String userCarNumber;
	String startTime;
	String endTime;

	public ParkingRecord() {
		super();
	}

	public ParkingRecord(Integer id, Integer parkingID, String userCarNumber, String startTime, String endTime) {
		this.id = id;
		this.parkingID = parkingID;
		this.userCarNumber = userCarNumber;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String toString() {
		return " [ID=" + id + ", parking ID=" + parkingID + ", User Car Number=" + userCarNumber + ", start time=" + startTime + ", end time=" + endTime + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParkingID() {
		return parkingID;
	}

	public void setParkingID(Integer parkingID) {
		this.parkingID = parkingID;
	}

	public String getUserCarNumber() {
		return userCarNumber;
	}

	public void setUserCarNumber(String userCarNumber) {
		this.userCarNumber = userCarNumber;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		String s = dateFormat.format(startTime);
		this.startTime = s;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) throws ParseException {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		String endDate = dateFormat.format(endTime);
		this.endTime = endDate;
	}
}
