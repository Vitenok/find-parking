package com.iti.parking.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iti.parking.entity.jpa.Admin;
import com.iti.parking.entity.jpa.ParkingCurrentState;
import com.iti.parking.entity.jpa.ParkingHistoricalState;
import com.iti.parking.entity.jpa.ParkingPlace;
import com.iti.services.jpa.AdminService;
import com.iti.services.jpa.StateService;
import com.iti.services.jpa.ParkingPlaceService;

@WebServlet(name = "AdminController", urlPatterns = { "/admin", "/admin/createAdmin", "/admin/deleteAdmin", "/admin/updateAdmin", "/admin/parking/createParking", "/admin/parking/deleteParking", "/admin/parking/updateParking" })
public class AdminController extends HttpServlet {

	private static final long serialVersionUID = -8912740472698890863L;

	StateService stateService = new StateService();
	AdminService adminService = new AdminService();
	ParkingPlaceService parkingPlaceService = new ParkingPlaceService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("CurrentParkingViewer", getAllBusySlots());
			request.setAttribute("HistoricalParkingViewer", getAllUsedSlots());
			request.setAttribute("AllParkingPlacesViewer", getAllParkingPlaces());
			request.setAttribute("AllAdminsViewer", getAllAdmins());
		} catch (Exception e) {
			request.setAttribute("errorMsg1", "Can't get parking data. Please try again later");
		}
		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		switch (servletPath) {

		case "/admin/createAdmin":
			try {
				Admin adminToAdd = new Admin(request.getParameter("login"), request.getParameter("pass"));
				adminService.addAdmin(adminToAdd);
				response.getWriter().write(adminToAdd.getId());
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("Admin with this name already exists");
			}
			break;

		case "/admin/deleteAdmin":
			try {
				int id = Integer.parseInt(request.getParameter("entityId"));
				adminService.deleteAdminById(id);
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("There are no admins with this ID");
			}
			break;

		case "/admin/updateAdmin":
			try {
				int id = Integer.parseInt(request.getParameter("entityId"));
				String newLogin = request.getParameter("login");
				String newPassword = request.getParameter("pass");
				adminService.updateAdminById(id, newLogin, newPassword);
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("Admin with this name already exists");
			}
			break;

		case "/admin/parking/createParking":
			try {
				String address = request.getParameter("address");
				int capacity = Integer.parseInt(request.getParameter("capacity"));
				int availableSlots = Integer.parseInt(request.getParameter("slots"));
				ParkingPlace parkingToAdd = new ParkingPlace(address, capacity, availableSlots);
				adminService.addParkingPlace(parkingToAdd);
				response.getWriter().write(parkingToAdd.getId());
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("Parking with this address already exists");
			}
			break;

		case "/admin/parking/deleteParking":
			try {
				int id = Integer.parseInt(request.getParameter("entityId"));
				adminService.deleteParkingPlaceById(id);
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("There are no parkings with this ID");
			}
			break;

		case "/admin/parking/updateParking":
			try {
				int id = Integer.parseInt(request.getParameter("entityId"));
				String newAddress = request.getParameter("address");
				int newCapacity = Integer.parseInt(request.getParameter("capacity"));
				int newAvailableSlots = Integer.parseInt(request.getParameter("slots"));
				adminService.updateParkingPlaceById(id, newAddress, newCapacity, newAvailableSlots);
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("Parking with this name already exists");
			}
			break;
		}
	}

	private List<ParkingCurrentState> getAllBusySlots() throws Exception {
		return stateService.getAllBusySlots();
	}

	private List<ParkingHistoricalState> getAllUsedSlots() throws Exception {
		return stateService.getAllUsedSlots();
	}

	private List<Admin> getAllAdmins() {
		return adminService.getAllAdmins();
	}

	private List<ParkingPlace> getAllParkingPlaces() {
		return parkingPlaceService.findAllParkingPlaces();
	}
}
