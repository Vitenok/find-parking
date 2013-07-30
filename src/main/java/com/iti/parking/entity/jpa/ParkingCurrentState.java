package com.iti.parking.entity.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the parking_current_state database table.
 * 
 */
@Entity
@Table(name = "parking_current_state")
@NamedQueries(value = { 
        @NamedQuery (name = "ParkingCurrentState.getAllBusySlotsByParkingId", query = "select p from ParkingCurrentState as p where p.parking.id =:parking_id"),
		@NamedQuery(name = "ParkingCurrentState.getStateForCar", query = "select p from ParkingCurrentState p where p.parkingUserCarNumber =:parking_user_car_number")
})

public class ParkingCurrentState implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String GET_STATE_FOR_CAR = "ParkingCurrentState.getStateForCar";
	public static final String GET_ALL_BUSY_SLOTS_BY_PARKING_ID = "ParkingCurrentState.getAllBusySlotsByParkingId";

	@Id
	@Column(unique = true, nullable = false)
	private int id;

	@ManyToOne
	//@JoinColumn(name = "parking_id", referencedColumnName="id")
	@JoinColumn(name = "parking_id")
	private ParkingPlace parking;

	@Column(name = "parking_user_car_number", nullable = false, length = 45)
	private String parkingUserCarNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "parking_user_end_time", nullable = false)
	private Date parkingUserEndTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "parking_user_start_time", nullable = false)
	private Date parkingUserStartTime;

	public ParkingCurrentState() {
	}

	public ParkingCurrentState(int id, ParkingPlace parking, String userCarNumber, Date startDate, Date endDate) {
		this.id = id;
		this.parking = parking;
		this.parkingUserCarNumber = userCarNumber;
		this.parkingUserStartTime = startDate;
		this.parkingUserEndTime = endDate;
	}

	public ParkingCurrentState( ParkingPlace parking, String userCarNumber, Date startDate, Date endDate) {
		
		this.parking = parking;
		this.parkingUserCarNumber = userCarNumber;
		this.parkingUserStartTime = startDate;
		this.parkingUserEndTime = endDate;
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ParkingPlace getParking() {
		return this.parking;
	}

	public void setParking(ParkingPlace parking) {
		this.parking = parking;
	}

	public String getParkingUserCarNumber() {
		return this.parkingUserCarNumber;
	}

	public void setParkingUserCarNumber(String parkingUserCarNumber) {
		this.parkingUserCarNumber = parkingUserCarNumber;
	}

	public Date getParkingUserEndTime() {
		return this.parkingUserEndTime;
	}

	public void setParkingUserEndTime(Date parkingUserEndTime) {
		this.parkingUserEndTime = parkingUserEndTime;
	}

	public Date getParkingUserStartTime() {
		return this.parkingUserStartTime;
	}

	public void setParkingUserStartTime(Date parkingUserStartTime) {
		this.parkingUserStartTime = parkingUserStartTime;
	}

	@Override
	public String toString() {
		return "ParkingCurrentState [id=" + id + ", parkingId=" + parking + ", parkingUserCarNumber=" + parkingUserCarNumber + ", parkingUserEndTime=" + parkingUserEndTime + ", parkingUserStartTime=" + parkingUserStartTime + "]";
	}
}