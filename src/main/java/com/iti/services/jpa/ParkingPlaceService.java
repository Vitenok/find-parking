package com.iti.services.jpa;

import java.util.List;

import com.iti.parking.dao.jpa.ParkingPlaceDAO;
import com.iti.parking.entity.jpa.ParkingPlace;

public class ParkingPlaceService {
	private  ParkingPlaceDAO parkingPlaceDAO = new ParkingPlaceDAO();

	public List<ParkingPlace> findAllParkingPlaces() {
		parkingPlaceDAO.beginTransaction();
		List<ParkingPlace> parkingPlaces = parkingPlaceDAO.findAll();
		parkingPlaceDAO.closeTransaction();
		return parkingPlaces;
	}
	public ParkingPlace findParkingPlaceById(int id){
		parkingPlaceDAO.beginTransaction();
		ParkingPlace parkingPlace = parkingPlaceDAO.findParkingPlaceById(id);
		parkingPlaceDAO.closeTransaction();
		return parkingPlace;
	}

}
