package com.iti.parking.entity.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the parking_places database table. parking_places
 */
@Entity
@Table(name = "parking_places")
@NamedQueries(value = { @NamedQuery(name = "ParkingPlace.findAllParkingPlaces", query = "select p from ParkingPlace p"),
						@NamedQuery(name = "ParkingPlace.findParkingPlaceById", query = "select p from ParkingPlace p where p.id =:id"), 
						@NamedQuery(name = "ParkingPlace.findParkingPlaceByAddress", query = "select p from ParkingPlace p where p.parkingAddress =:parking_address") })
public class ParkingPlace implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL_PARKING_PLACES = "ParkingPlace.findAllParkingPlaces";
	public static final String FIND_PARKING_PLACE_BY_ID = "ParkingPlace.findParkingPlaceById";
	public static final String FIND_PARKING_PLACE_BY_ADDRESS = "ParkingPlace.findParkingPlaceByAddress";

	@Id
	@Column(unique = true, nullable = false)
	private int id;

	@Column(name = "parking_address", nullable = false, length = 255)
	private String parkingAddress;

	@Column(name = "parking_available_slots", nullable = false, length = 45)
	private int parkingAvailableSlots;

	@Column(name = "parking_capacity", nullable = false)
	private int parkingCapacity;

	public ParkingPlace() {
	}

	public ParkingPlace(String parkingAddress, int parkingCapacity, int parkingAvailableSlots) {
		this.parkingAddress = parkingAddress;
		this.parkingCapacity = parkingCapacity;
		this.parkingAvailableSlots = parkingAvailableSlots;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParkingAddress() {
		return this.parkingAddress;
	}

	public void setParkingAddress(String parkingAddress) {
		this.parkingAddress = parkingAddress;
	}

	public int getParkingAvailableSlots() {
		return this.parkingAvailableSlots;
	}

	public void setParkingAvailableSlots(int parkingAvailableSlots) {
		this.parkingAvailableSlots = parkingAvailableSlots;
	}

	public int getParkingCapacity() {
		return this.parkingCapacity;
	}

	public void setParkingCapacity(int parkingCapacity) {
		this.parkingCapacity = parkingCapacity;
	}

	@Override
	public String toString() {
		return "ParkingPlace [id=" + id + ", parkingAddress=" + parkingAddress + ", parkingAvailableSlots=" + parkingAvailableSlots + ", parkingCapacity=" + parkingCapacity + "]";
	}

}