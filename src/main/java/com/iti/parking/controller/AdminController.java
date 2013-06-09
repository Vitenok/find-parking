package com.iti.parking.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iti.parking.entity.ParkingRecord;
import com.iti.parking.users.Admin;
import com.iti.parking.util.ConnectionFactory;
import com.iti.parking.util.adminDAO;

/**
 * Servlet implementation class ParkingViewerAdmin
 */
@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO:
		// check if admih is in the session

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter("login");
		String password = request.getParameter("password");

		Admin admin = null;
		try {
			admin = adminDAO.findAdmin(login, password);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			// HANDLE ME PROPERLY
			e1.printStackTrace();
		}

		if (admin == null) {
			// TODO:
			// return login-failed-message
			// request.setParameter("errorMsg","Wrong username or password");
			response.sendRedirect("adminLogin.jsp");
			return;
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("admin", login);

			try {
				request.setAttribute("HistoricalParkingViewer", getHistoricalParkingData());
				request.setAttribute("CurrentParkingViewer", getCurrentParkingData());
			} catch (Exception e) {
				request.setAttribute("errorMsg", "Can't get parking data. Please try again later");
			}
			// if logged in
			RequestDispatcher view = request.getRequestDispatcher("admin.jsp");
			view.forward(request, response);
		}

	}// if not logged in- go to login.jsp-->POST-->AdminController servlet-(Is login/pass correct???)

	private List<ParkingRecord> getHistoricalParkingData() throws Exception {
		return getParkingData("parking_historical_state");
	}

	private List<ParkingRecord> getCurrentParkingData() throws Exception {
		return getParkingData("parking_current_state");
	}

	private List<ParkingRecord> getParkingData(String tableName) throws Exception {
		List<ParkingRecord> parkingData = new ArrayList<ParkingRecord>();

		Connection connection = ConnectionFactory.getConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from " + tableName);
		while (rs.next()) {
			ParkingRecord tableRowHistorical = new ParkingRecord();
			tableRowHistorical.setId(rs.getInt("id"));
			tableRowHistorical.setParkingID(rs.getInt("parking_id"));
			tableRowHistorical.setUserCarNumber(rs.getString("parking_user_car_number"));
			tableRowHistorical.setStartTime(rs.getDate("parking_user_start_time"));

			tableRowHistorical.setEndTime(rs.getDate("parking_user_end_time"));

			parkingData.add(tableRowHistorical);

		}
		rs.close();
		statement.close();
		return parkingData;
	}

}
