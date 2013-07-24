package com.iti.parking.dao.jpa;

import com.iti.parking.entity.jpa.ParkingHistoricalState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingHistoricalStateDAO extends GenericDAO<ParkingHistoricalState> {

	private static final long serialVersionUID = 1L;

	public ParkingHistoricalStateDAO() {
		super(ParkingHistoricalState.class);
	}

	public List<ParkingHistoricalState> getHistoricalStateForCar(String carNumber) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("parking_user_car_number", carNumber);
		return super.findResultList(ParkingHistoricalState.GET_USED_SLOTS_BY_CAR_NUMBER, parameters);
	}

	public List<ParkingHistoricalState> getAllUsedSlotsByParkingId(int parkingId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("parking_id", parkingId);
		return super.findResultList(ParkingHistoricalState.GET_USED_SLOTS_BY_PARKING_ID, parameters);
	}
}
