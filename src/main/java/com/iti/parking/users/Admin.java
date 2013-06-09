package com.iti.parking.users;

import java.sql.SQLException;

public class Admin {
	String login;
	String password;

	public Admin() {
	}

	public Admin(String login, String password) throws SQLException {
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		Admin admin = (Admin) obj;

		if (admin.getLogin() == login || (login.equals(admin.getLogin())) && admin.getPassword() == password || (password.equals(admin.getPassword()))) {
			return true;
		}

		return false;

	}

//	@Override
//	public int hashCode() {
//		final int hash = 31;
//		int result = 1;
//		result = hash * result + ((login == null) ? 0 : login.hashCode());
//		result = hash * result;
//		result = hash * result + ((password == null) ? 0 : password.hashCode());
//		return result;
//	}

}