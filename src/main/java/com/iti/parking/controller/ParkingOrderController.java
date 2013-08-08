package com.iti.parking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iti.parking.ws.client.generated.ParkingOrderEmulatorWebService;
import com.iti.parking.ws.client.generated.ParkingOrderEmulatorWebServiceService;

@WebServlet(name = "ParkingOrderController", urlPatterns = { "/makeOrder" })
public class ParkingOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ParkingOrderEmulatorWebServiceService service = new ParkingOrderEmulatorWebServiceService();
	private ParkingOrderEmulatorWebService client = service.getParkingOrderEmulatorWebServicePort();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String carNumber = request.getParameter("carNumber");
		int parkingID = Integer.parseInt(request.getParameter("parkingId"));
		String date = request.getParameter("endTime");

		client.placeOrder(carNumber, parkingID, date);
	}

}
