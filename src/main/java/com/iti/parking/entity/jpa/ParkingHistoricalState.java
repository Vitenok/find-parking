package com.iti.parking.entity.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the parking_historical_state database table.
 * 
 */
@Entity
@Table(name = "parking_historical_state")
@NamedQueries(value = { @NamedQuery(name = "ParkingHistoricalState.getUsedSlotsByCarNumber", query = "select p from ParkingHistoricalState p where p.parkingUserCarNumber =:parking_user_car_number"),
                        @NamedQuery(name = "ParkingHistoricalState.getAllUsedSlotsByParkingId", query = "select p from ParkingHistoricalState p where p.parkingId =:parking_id")
})

public class ParkingHistoricalState implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String GET_USED_SLOTS_BY_CAR_NUMBER = "ParkingHistoricalState.getUsedSlotsByCarNumber";
	public static final String GET_USED_SLOTS_BY_PARKING_ID = "ParkingHistoricalState.getAllUsedSlotsByParkingId";
	
	@Id
	@Column(unique = true, nullable = false)
	private int id;

	@Column(name = "parking_id", nullable = false)
	private int parkingId;

	@Column(name = "parking_user_car_number", nullable = false, length = 45)
	private String parkingUserCarNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "parking_user_end_time", nullable = false)
	private Date parkingUserEndTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "parking_user_start_time", nullable = false)
	private Date parkingUserStartTime;

	public ParkingHistoricalState() {
	}

	public ParkingHistoricalState(int id, int parkingId, String carNumber, Date startDate, Date endDate) {
		this.id = id;
		this.parkingId = parkingId;
		this.parkingUserCarNumber = carNumber;
		this.parkingUserStartTime = startDate;
		this.parkingUserEndTime = endDate;
	}

	public ParkingHistoricalState( int parkingId, String carNumber, Date startDate, Date endDate) {
		
		this.parkingId = parkingId;
		this.parkingUserCarNumber = carNumber;
		this.parkingUserStartTime = startDate;
		this.parkingUserEndTime = endDate;
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParkingId() {
		return this.parkingId;
	}

	public void setParkingId(int parkingId) {
		this.parkingId = parkingId;
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
		return "ParkingHistoricalState [id=" + id + ", parkingId=" + parkingId + ", parkingUserCarNumber=" + parkingUserCarNumber + ", parkingUserEndTime=" + parkingUserEndTime + ", parkingUserStartTime=" + parkingUserStartTime + "]";
	}

}