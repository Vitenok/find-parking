package com.iti.parking.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iti.parking.entity.jpa.ParkingCurrentState;
import com.iti.parking.entity.jpa.ParkingHistoricalState;
import com.iti.parking.entity.jpa.ParkingPlace;
import com.iti.services.jpa.ParkingPlaceService;
import com.iti.services.jpa.StateService;

;

@WebServlet(name = "AllPlacesController", urlPatterns = { "" })
public class AllPlacesController extends HttpServlet {

	private static final long serialVersionUID = 7155634063731711969L;

	StateService stateService = new StateService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<ParkingPlace> parkingViewer = new ParkingPlaceService().findAllParkingPlaces();
		request.setAttribute("tableRows", parkingViewer);
		request.getRequestDispatcher("map.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		List<ParkingPlace> parkingViewer = new ParkingPlaceService().findAllParkingPlaces();
		request.setAttribute("tableRows", parkingViewer);

		String userCarNumber = request.getParameter("carNumber");
		if (userCarNumber != null && !userCarNumber.isEmpty()) {
			// request.setAttribute("action", "");
			ParkingCurrentState parkingCurrentState = stateService.getStateForCar(userCarNumber);
			List<ParkingHistoricalState> parkingHistoricalState = stateService.getUsedSlotsByCar(userCarNumber);
			request.setAttribute("currentStateForCar", parkingCurrentState);
			request.setAttribute("historicalStateForCar", parkingHistoricalState);

		}
		request.getRequestDispatcher("map.jsp").forward(request, response);
	}

}
