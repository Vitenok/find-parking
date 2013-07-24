package com.iti.parking.dao.jpa;

import java.util.HashMap;
import java.util.Map;

import com.iti.parking.entity.jpa.ParkingPlace;

public class ParkingPlaceDAO extends GenericDAO<ParkingPlace> {

	private static final long serialVersionUID = 1L;

	public ParkingPlaceDAO() {
		super(ParkingPlace.class);
	}

	public ParkingPlace findParkingPlaceById(int id) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		return super.findOneResult(ParkingPlace.FIND_PARKING_PLACE_BY_ID, parameters);
	}

	public ParkingPlace findParkingPlaceByAddress(String address) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("parking_address", address);
		return super.findOneResult(ParkingPlace.FIND_PARKING_PLACE_BY_ADDRESS, parameters);

	}
}
