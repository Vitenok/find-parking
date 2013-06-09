package com.iti.parking.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iti.parking.entity.ParkingPlace;
import com.iti.parking.util.ConnectionFactory;

/**
 * Servlet implementation class ParkingViewer
 */
@WebServlet("")
public class AllPlacesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	static Connection connection;
//	Integer parkingID;
//	String parkingAddress;
//	Integer parkingCapacity;
//	String parkingAvailableSlots;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection connection = ConnectionFactory.getConnection();
			Statement statement = connection.createStatement();

			List<ParkingPlace> parkingViewer = new ArrayList<ParkingPlace>();

			ResultSet resultSet = statement.executeQuery("select * from parking_places");
			while (resultSet.next()) {
				ParkingPlace tableRow = new ParkingPlace();
				tableRow.setParkingID(resultSet.getInt("id"));
				tableRow.setParkingAddress(resultSet.getString("parking_address"));
				tableRow.setParkingCapacity(resultSet.getInt("parking_capacity"));
				tableRow.setParkingAvailableSlots(resultSet.getString("parking_available_slots"));
				parkingViewer.add(tableRow);
			}
			request.setAttribute("tableRows", parkingViewer);
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");

			view.forward(request, response);
			resultSet.close();
			statement.close();
			return;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

