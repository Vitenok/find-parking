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
import com.iti.services.AdminService;
import com.iti.services.ParkingPlaceService;
import com.iti.services.StateService;

@WebServlet(name = "AdminController", urlPatterns = { "/admin", "/admin/createAdmin", "/admin/deleteAdmin", "/admin/updateAdmin", "/admin/parking/createParking", "/admin/parking/deleteParking"})
public class AdminController extends HttpServlet {
// TEST
	private static final long serialVersionUID = -8912740472698890863L;

	StateService stateService = new StateService();
	AdminService adminService = new AdminService();
	ParkingPlaceService parkingPlaceService = new ParkingPlaceService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ParkingPlace> parkingViewer = new ParkingPlaceService().findAllParkingPlaces();
		try {
			request.setAttribute("currentParkingViewer", getAllBusySlots());
			request.setAttribute("historicalParkingViewer", getAllUsedSlots());
			request.setAttribute("allParkingPlacesViewer", parkingViewer);
			request.setAttribute("allAdminsViewer", getAllAdmins());
		} catch (Exception e) {
			request.setAttribute("errorMsg1", "Can't get parking data. Please try again later");
		}
		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		if ("/admin/createAdmin".equals(servletPath)) {
			try {
				Admin adminToAdd = new Admin(request.getParameter("login"), request.getParameter("pass"));
				adminService.addAdmin(adminToAdd);
				response.getWriter().write(adminToAdd.getId());
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("Admin with this name already exists");
			}
		} else if ("/admin/deleteAdmin".equals(servletPath)) {
			try {
				int id = Integer.parseInt(request.getParameter("entityId"));
				adminService.deleteAdminById(id);
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("There are no admins with this ID");
			}
		} else if ("/admin/updateAdmin".equals(servletPath)) {
			try {
				int id = Integer.parseInt(request.getParameter("entityId"));
				String newLogin = request.getParameter("login");
				String newPassword = request.getParameter("pass");
				adminService.updateAdminById(id, newLogin, newPassword);
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("Admin with this name already exists");
			}
		} else if ("/admin/parking/createParking".equals(servletPath)) {
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
		} else if ("/admin/parking/deleteParking".equals(servletPath)) {
			try {
				int id = Integer.parseInt(request.getParameter("entityId"));
				adminService.deleteParkingPlaceById(id);
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("There are no parkings with this ID");
			}
		} else if ("/admin/parking/updateParking".equals(servletPath)) {
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
	
	/**
	 * private List<ParkingPlace> getAllParkingPlaces() {
			return parkingPlaceService.findAllParkingPlaces();
	   }
	 **/
}
