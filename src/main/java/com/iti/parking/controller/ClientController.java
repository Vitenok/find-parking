package com.iti.parking.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iti.parking.entity.ParkingRecord;
import com.iti.parking.util.ConnectionFactory;

@WebServlet("/client")
public class ClientController extends HttpServlet {
	// check if login/password is valid; if true-- go to client.jsp with staristics

	private static final long serialVersionUID = 515052634651362837L;
//	static Connection connection;
//	Integer id;
//	Integer parkingID;
//	String userCarNumber;
//	SimpleDateFormat startTime;

	Map<String, String> loginsPasswords = new HashMap<String, String>();

	public boolean checkLoginPassword(String login, String password) {
		if (login.isEmpty() || login.equals(null)) {
			return false;

		} else if (!loginsPasswords.containsKey(login) && !password.isEmpty()) {
			loginsPasswords.put(login, password);
			return true;
			// go to result.jsp
		} else {
			String passwordToCeck = loginsPasswords.get(login);
			if (!password.isEmpty() && passwordToCeck.equals(password)) {
				return true;
				// go to result.jsp
			}
			return false;
			// go to error page
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//  String message = "Invalid login or password! Please try again!";
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		ClientController cc = new ClientController();

		if (cc.checkLoginPassword(login, password)) {
			try {
				Connection connection = ConnectionFactory.getConnection();
				Statement statement = connection.createStatement();

				List<ParkingRecord> currentParkingViewer = new ArrayList<ParkingRecord>();
				List<ParkingRecord> historicalParkingViewer = new ArrayList<ParkingRecord>();

				ResultSet resultSetCurrent = statement.executeQuery("select * from parking_current_state where parking_user_car_number=\"" + login + "\"");
				while (resultSetCurrent.next()) {
					ParkingRecord tableRow = new ParkingRecord();
					tableRow.setId(resultSetCurrent.getInt("id"));
					tableRow.setParkingID(resultSetCurrent.getInt("parking_id"));
					tableRow.setUserCarNumber(resultSetCurrent.getString("parking_user_car_number"));
					tableRow.setStartTime(resultSetCurrent.getDate("parking_user_start_time"));
					tableRow.setEndTime(resultSetCurrent.getDate("parking_user_end_time"));
					currentParkingViewer.add(tableRow);

				}

				ResultSet resultSetHistorical = statement.executeQuery("select * from parking_historical_state where parking_user_car_number=\"" + login + "\"");

				while (resultSetHistorical.next()) {
					ParkingRecord tableRowHistorical = new ParkingRecord();
					tableRowHistorical.setId(resultSetHistorical.getInt("id"));
					tableRowHistorical.setParkingID(resultSetHistorical.getInt("parking_id"));
					tableRowHistorical.setUserCarNumber(resultSetHistorical.getString("parking_user_car_number"));
					tableRowHistorical.setStartTime(resultSetHistorical.getDate("parking_user_start_time"));

					tableRowHistorical.setEndTime(resultSetHistorical.getDate("parking_user_end_time"));
					historicalParkingViewer.add(tableRowHistorical);
				}
				request.setAttribute("historicalParkingViewer", historicalParkingViewer);
				request.setAttribute("currentParkingViewer", currentParkingViewer);
				RequestDispatcher view = request.getRequestDispatcher("client.jsp");

				resultSetCurrent.close();
				resultSetHistorical.close();
				statement.close();
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("AllPlacesController.do");
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
