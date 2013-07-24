package com.iti.parking.entity.jdbc;

import java.sql.Connection;

import com.iti.parking.util.ConnectionFactory;

public class ParkingPlace {
	Connection connection = ConnectionFactory.getConnection();

	Integer parkingID;
	String parkingAddress;
	Integer parkingCapacity;
	String parkingAvailableSlots;

	public ParkingPlace() {
		super();
	}

	public ParkingPlace(Integer parkingID, String parkingAddress, Integer parkingCapacity, String parkingAvailableSlots) {
		this.parkingID = parkingID;
		this.parkingAddress = parkingAddress;
		this.parkingCapacity = parkingCapacity;
		this.parkingAvailableSlots = parkingAvailableSlots;
	}

	
	public String toString() {
		return " [parking ID=" + parkingID + ", parking address=" + parkingAddress + ", parking capacity=" + parkingCapacity + ", parking available slots=" + parkingAvailableSlots + "]";
	}
	public void setParkingID(Integer parkingID) {
		this.parkingID = parkingID;
	}

	public Integer getParkingID() {
		return parkingID;
	}

	public void setParkingAddress(String parkingAddress) {
		this.parkingAddress = parkingAddress;
	}

	public String getParkingAddress() {
		return parkingAddress;
	}

	public void setParkingCapacity(Integer parkingCapacity) {
		this.parkingCapacity = parkingCapacity;
	}

	public Integer getparkingCapacity() {
		return parkingCapacity;
	}

	public void setParkingAvailableSlots(String parkingAvailableSlots) {
		this.parkingAvailableSlots = parkingAvailableSlots;
	}

	public String getParkingAvailableSlots() {
		return parkingAvailableSlots;
	}

}


