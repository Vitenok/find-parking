package com.iti.services.jpa;

import java.util.List;

import com.iti.parking.dao.jpa.AdminDAO;
import com.iti.parking.dao.jpa.ParkingPlaceDAO;
import com.iti.parking.entity.jpa.Admin;
import com.iti.parking.entity.jpa.ParkingPlace;

public class AdminService {

	private AdminDAO adminDAO = new AdminDAO();
	private ParkingPlaceDAO parkingPlaceDAO = new ParkingPlaceDAO();
	private ParkingPlaceService parkingPlaceServise = new ParkingPlaceService();

	public Admin isValidLogin(String name, String password) {
		adminDAO.beginTransaction();
		Admin admin = adminDAO.findAdminByNameAndPassword(name, password);
		if (admin == null) {
			return null;
		}
		adminDAO.closeTransaction();
		return admin;
	}

	public Admin findAdminByNameAndPasswword(String name, String password) {
		adminDAO.beginTransaction();
		Admin admin = adminDAO.findAdminByNameAndPassword(name, password);
		adminDAO.closeTransaction();
		return admin;
	}

	public Admin findAdminById(int id) {
		adminDAO.beginTransaction();
		Admin admin = adminDAO.findAdminById(id);
		adminDAO.closeTransaction();
		return admin;
	}

	public void addAdmin(Admin adminToAdd) {
		adminDAO.beginTransaction();
		adminDAO.save(adminToAdd);
		adminDAO.commit();
		adminDAO.closeTransaction();
	}

	public void deleteAdmin(Admin adminToDelete) {
		adminDAO.beginTransaction();
		adminDAO.delete(adminToDelete);
		adminDAO.commit();
		adminDAO.closeTransaction();
	}

	public void deleteAdminById(int id) {
		adminDAO.beginTransaction();
		Admin presistedAdmin = adminDAO.find(id);
		adminDAO.delete(presistedAdmin);
		adminDAO.commit();
		adminDAO.closeTransaction();
	}

	public void updateAdmin(Admin adminToUpdate) {
		adminDAO.beginTransaction();
		Admin presistedAdmin = adminDAO.find(adminToUpdate.getId());
		presistedAdmin.setName(adminToUpdate.getName());
		presistedAdmin.setPassword(adminToUpdate.getPassword());
		adminDAO.update(presistedAdmin);
		adminDAO.commit();
		adminDAO.closeTransaction();
	}

	public void updateAdminById(int id, String newName, String newPassword) {
		adminDAO.beginTransaction();
		Admin presistedAdmin = adminDAO.find(id);
		presistedAdmin.setName(newName);
		presistedAdmin.setPassword(newPassword);
		adminDAO.update(presistedAdmin);
		adminDAO.commit();
		adminDAO.closeTransaction();
	}

	public List<Admin> getAllAdmins() {
		adminDAO.beginTransaction();
		List<Admin> admins = adminDAO.findAll();
		adminDAO.closeTransaction();
		return admins;
	}

	public void addParkingPlace(ParkingPlace parkingPlaceToAdd) {
		parkingPlaceDAO.beginTransaction();
		parkingPlaceDAO.save(parkingPlaceToAdd);
		parkingPlaceDAO.commit();
		adminDAO.closeTransaction();
	}

	public void deleteParkingPlace(ParkingPlace parkingPlaceToDelete) {
		parkingPlaceDAO.beginTransaction();
		parkingPlaceDAO.delete(parkingPlaceToDelete);
		parkingPlaceDAO.commit();
		adminDAO.closeTransaction();
	}

	public void deleteParkingPlaceById(int id) {
		parkingPlaceDAO.beginTransaction();
		ParkingPlace parkingPlaceToDelete = parkingPlaceDAO.find(id);
		parkingPlaceDAO.delete(parkingPlaceToDelete);
		parkingPlaceDAO.commit();
		adminDAO.closeTransaction();
	}

	public void updateParkingPlace(ParkingPlace parkingPlaceToUpdate) {
		parkingPlaceDAO.beginTransaction();
		ParkingPlace persistedParkingPlace = parkingPlaceDAO.findParkingPlaceById(parkingPlaceToUpdate.getId());
		persistedParkingPlace.setParkingAddress(parkingPlaceToUpdate.getParkingAddress());
		persistedParkingPlace.setParkingCapacity(parkingPlaceToUpdate.getParkingCapacity());
		persistedParkingPlace.setParkingAvailableSlots(parkingPlaceToUpdate.getParkingAvailableSlots());
		parkingPlaceDAO.update(parkingPlaceToUpdate);
		parkingPlaceDAO.commit();
		adminDAO.closeTransaction();
	}

	public void updateParkingPlaceById(int id, String newAddress, int newCapacity, int newAvailableSlots) {
		parkingPlaceDAO.beginTransaction();
		ParkingPlace persistedParkingPlace = parkingPlaceDAO.findParkingPlaceById(id);
		persistedParkingPlace.setParkingAddress(newAddress);
		persistedParkingPlace.setParkingCapacity(newCapacity);
		persistedParkingPlace.setParkingAvailableSlots(newAvailableSlots);
		parkingPlaceDAO.update(persistedParkingPlace);
		parkingPlaceDAO.commit();
		adminDAO.closeTransaction();
	}

	public List<ParkingPlace> getAllParkingPlaces() {
		return parkingPlaceServise.findAllParkingPlaces();
	}

	public ParkingPlace findParkingPlaceByAddress(String address) {
		parkingPlaceDAO.beginTransaction();
		ParkingPlace parkingPlace = parkingPlaceDAO.findParkingPlaceByAddress(address);
		parkingPlaceDAO.closeTransaction();
		return parkingPlace;

	}
}
