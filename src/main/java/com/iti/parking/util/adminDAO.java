package com.iti.parking.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.iti.parking.users.Admin;

public class adminDAO {

	public adminDAO() {
	}

	public static Admin findAdmin(String login, String password) throws SQLException {
		
		Admin adminToCheck = new Admin(login, password);

		Connection connection = ConnectionFactory.getConnection();
		Statement statement = connection.createStatement();

		ResultSet resultSet = statement.executeQuery("select * from admins");
		while (resultSet.next()) {
			Admin adminFromDB = new Admin(resultSet.getString("name"), resultSet.getString("password"));
						
			if (adminFromDB.equals(adminToCheck)) {
				return adminToCheck;
			}
		}
		return null;
	}
}
