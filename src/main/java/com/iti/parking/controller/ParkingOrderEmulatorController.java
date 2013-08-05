package com.iti.parking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iti.parking.ws.client.ParkingOrderEmulatorClient;

@WebServlet(name = "ParkingOrderEmulatorController", urlPatterns = { "/emulator" })
public class ParkingOrderEmulatorController extends HttpServlet {

	private static final long serialVersionUID = -2237313024873406340L;

	// returns status ParkingOrderEmulatorClient.isActive
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String activateString = request.getParameter("activate");

		if (activateString != null && !activateString.trim().isEmpty()) {
			
			Boolean activate = Boolean.valueOf(activateString);

			if (activate != null && activate && !ParkingOrderEmulatorClient.isActive) {
				ParkingOrderEmulatorClient.isActive = true;
				new ParkingOrderEmulatorClient().startEmulation();
			} else {
				ParkingOrderEmulatorClient.isActive = false;
			}
		}

		response.getWriter().write(String.valueOf(ParkingOrderEmulatorClient.isActive));
	}

}
