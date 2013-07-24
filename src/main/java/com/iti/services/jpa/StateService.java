package com.iti.services.jpa;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.iti.parking.entity.jpa.ParkingCurrentState;
import com.iti.parking.entity.jpa.ParkingHistoricalState;
import com.iti.parking.entity.jpa.ParkingPlace;
import com.iti.parking.dao.jpa.ParkingCurrentStateDAO;
import com.iti.parking.dao.jpa.ParkingHistoricalStateDAO;
import com.iti.parking.dao.jpa.ParkingPlaceDAO;

public class StateService {

	private ParkingCurrentStateDAO parkingCurrentStateDAO = new ParkingCurrentStateDAO();
	private ParkingHistoricalStateDAO parkingHistoricalStateDAO = new ParkingHistoricalStateDAO();
	private ParkingPlaceDAO parkingPlaceDAO = new ParkingPlaceDAO();

	public List<ParkingCurrentState> getAllBusySlotsByParkingId(int parkingId) {
		parkingCurrentStateDAO.beginTransaction();
		List<ParkingCurrentState> busySlotsByParkingId = parkingCurrentStateDAO.getgetAllBusySlotsByParkingId(parkingId);
		parkingCurrentStateDAO.closeTransaction();
		return busySlotsByParkingId;
	}

	public List<ParkingCurrentState> getAllBusySlots() {
		parkingCurrentStateDAO.beginTransaction();
		List<ParkingCurrentState> parkingCurrentState = parkingCurrentStateDAO.findAll();
		parkingCurrentStateDAO.closeTransaction();
		return parkingCurrentState;
	}

	public ParkingCurrentState getStateForCar(String userCarNumber) {
		parkingCurrentStateDAO.beginTransaction();
		ParkingCurrentState currentStateForCar = parkingCurrentStateDAO.getCurrentStateForCar(userCarNumber);
		parkingCurrentStateDAO.closeTransaction();
		return currentStateForCar;
	}

	public ParkingCurrentState findParkingCurrentStateById(int id) {
		parkingCurrentStateDAO.beginTransaction();
		ParkingCurrentState parkingCurrentState = parkingCurrentStateDAO.find(id);
		parkingCurrentStateDAO.closeTransaction();
		return parkingCurrentState;
	}

	public void addParkingCurrentState(ParkingCurrentState parkingCurrentStateToAdd) {
		parkingCurrentStateDAO.beginTransaction();
		parkingCurrentStateDAO.save(parkingCurrentStateToAdd);
		parkingCurrentStateDAO.commitAndCloseTransaction();
	}

	public void deleteParkingCurrentStateForCar(String CarNumber) {

		parkingCurrentStateDAO.beginTransaction();

		ParkingCurrentState persistedParkingCurrentState = parkingCurrentStateDAO.getCurrentStateForCar(CarNumber);
		if (persistedParkingCurrentState != null) {
			int parkingId = persistedParkingCurrentState.getParkingId();
			String carNumber = persistedParkingCurrentState.getParkingUserCarNumber();
			Date startDate = persistedParkingCurrentState.getParkingUserStartTime();
			Date endDate = persistedParkingCurrentState.getParkingUserEndTime();

			ParkingHistoricalState deleted = new ParkingHistoricalState(parkingId, carNumber, startDate, endDate);

			parkingCurrentStateDAO.delete(persistedParkingCurrentState);

			// adding to ParkingHistoricalState;
			parkingHistoricalStateDAO.save(deleted);

			// update parking places available slots
			ParkingPlace parkingPlace = parkingPlaceDAO.findParkingPlaceById(parkingId);
			parkingPlace.setParkingAvailableSlots(parkingPlace.getParkingAvailableSlots() + 1);
			parkingPlaceDAO.save(parkingPlace);

		}
		parkingCurrentStateDAO.commitAndCloseTransaction();
	}

	public void updateParkingCurrentState(ParkingCurrentState parkingCurrentStateToUpdate) {
		parkingCurrentStateDAO.beginTransaction();
		parkingCurrentStateDAO.update(parkingCurrentStateToUpdate);
		parkingCurrentStateDAO.closeTransaction();
	}

	public List<ParkingHistoricalState> getAllUsedSlots() {
		parkingHistoricalStateDAO.beginTransaction();
		List<ParkingHistoricalState> parkingHistoricalState = parkingHistoricalStateDAO.findAll();
		parkingHistoricalStateDAO.closeTransaction();
		return parkingHistoricalState;
	}

	public List<ParkingHistoricalState> getAllUsedSlotsByParkingId(int parkingId) {
		parkingHistoricalStateDAO.beginTransaction();
		List<ParkingHistoricalState> usedSlotsByParkingId = parkingHistoricalStateDAO.getAllUsedSlotsByParkingId(parkingId);
		parkingHistoricalStateDAO.closeTransaction();
		return usedSlotsByParkingId;
	}

	public List<ParkingHistoricalState> getUsedSlotsByCar(String carNumber) {
		parkingHistoricalStateDAO.beginTransaction();
		List<ParkingHistoricalState> slotsUsedByCar = parkingHistoricalStateDAO.getHistoricalStateForCar(carNumber);
		parkingHistoricalStateDAO.closeTransaction();
		return slotsUsedByCar;
	}

	public ParkingHistoricalState findParkingHistoricalStateById(int id) {
		parkingHistoricalStateDAO.beginTransaction();
		ParkingHistoricalState parkingHistoricalState = parkingHistoricalStateDAO.find(id);
		parkingHistoricalStateDAO.closeTransaction();
		return parkingHistoricalState;
	}

	public void addParkingHistoricalState(ParkingHistoricalState parkingPlaceToAdd) {
		parkingHistoricalStateDAO.beginTransaction();
		parkingHistoricalStateDAO.save(parkingPlaceToAdd);
		parkingHistoricalStateDAO.commitAndCloseTransaction();
	}

	public void deleteParkingHistoricalState(ParkingHistoricalState parkingPlaceToDelete) {
		parkingHistoricalStateDAO.beginTransaction();
		ParkingHistoricalState persistedParkingPlace = parkingHistoricalStateDAO.find(parkingPlaceToDelete.getId());
		parkingHistoricalStateDAO.delete(persistedParkingPlace);
		parkingHistoricalStateDAO.commitAndCloseTransaction();
	}

	public void updateParkingHistoricalState(ParkingHistoricalState parkingHistoricalStateToUpdate) {
		parkingHistoricalStateDAO.beginTransaction();
		parkingHistoricalStateDAO.update(parkingHistoricalStateToUpdate);
		parkingHistoricalStateDAO.commitAndCloseTransaction();
	}

	public void registerOrder(final String carNumber, int parkingId, final Date untilDate) throws ParseException {
		parkingCurrentStateDAO.beginTransaction();

		Date startDate = new Date();

		if (parkingCurrentStateDAO.getCurrentStateForCar(carNumber) != null) {
			ParkingCurrentState parkingCurrentState = parkingCurrentStateDAO.getCurrentStateForCar(carNumber);
			parkingCurrentState.setParkingUserEndTime(untilDate);
			parkingCurrentStateDAO.save(parkingCurrentState);
		} else {

			ParkingCurrentState newParkingCurrentState = new ParkingCurrentState(parkingId, carNumber, startDate, untilDate);

			parkingCurrentStateDAO.save(newParkingCurrentState);

			ParkingPlace parkingPlace = parkingPlaceDAO.findParkingPlaceById(parkingId);
			parkingPlace.setParkingAvailableSlots(parkingPlace.getParkingAvailableSlots() - 1);
			parkingPlaceDAO.update(parkingPlace);
		}

		// current state remover timer call
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (System.currentTimeMillis() > untilDate.getTime()) {
					StateService remover = new StateService();
					System.out.println("added to history, updated slots");
					remover.deleteParkingCurrentStateForCar(carNumber);
				}
			}
		}, untilDate.getTime() - startDate.getTime() + 30000);

		parkingCurrentStateDAO.commitAndCloseTransaction();
	}

	public void registerOrderTest(final String carNumber, int parkingId, long delay) throws ParseException {
		// parkingCurrentStateDAO.beginTransaction();

		// DateFormat dateFormat = new SimpleDateFormat("yyyyy-MM-dd HH:mm:ss");
		// Date beginDate = new Date();
		// String stringDate = dateFormat.format(beginDate);
		//
		// Date startDate = dateFormat.parse(stringDate);
		//
		// ParkingCurrentState newParkingCurrentState = new ParkingCurrentState(parkingId, carNumber, startDate, startDate);
		//
		// parkingCurrentStateDAO.save(newParkingCurrentState);
		//
		// ParkingPlace parkingPlace = parkingPlaceDAO.findParkingPlaceById(parkingId);
		// parkingPlace.setParkingAvailableSlots(parkingPlace.getParkingAvailableSlots() - 1);
		// parkingPlaceDAO.save(parkingPlace);

		// current state remover timer call

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				StateService remover = new StateService();
				System.out.println("hello");
				remover.deleteParkingCurrentStateForCar(carNumber);
			}
		}, delay);

		// parkingCurrentStateDAO.commitAndCloseTransaction();

	}
}
