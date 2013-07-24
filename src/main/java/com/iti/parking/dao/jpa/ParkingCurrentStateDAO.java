package com.iti.parking.dao.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iti.parking.entity.jpa.ParkingCurrentState;

public class ParkingCurrentStateDAO extends GenericDAO<ParkingCurrentState> {

	private static final long serialVersionUID = 1L;

	public ParkingCurrentStateDAO() {
		super(ParkingCurrentState.class);
	}

	public ParkingCurrentState getCurrentStateForCar(String carNumber) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("parking_user_car_number", carNumber);
		return super.findOneResult(ParkingCurrentState.GET_STATE_FOR_CAR, parameters);
	}

	public List<ParkingCurrentState> getgetAllBusySlotsByParkingId(int parkingId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("parking_id", parkingId);
		return super.findResultList(ParkingCurrentState.GET_ALL_BUSY_SLOTS_BY_PARKING_ID, parameters);
	}
}
