package com.iti.parking.dao.jpa;

import java.util.HashMap;
import java.util.Map;

import com.iti.parking.entity.jpa.Admin;

public class AdminDAO extends GenericDAO<Admin> {

	private static final long serialVersionUID = 1L;

	public AdminDAO() {
		super(Admin.class);
	}

	public Admin findAdminById(int id) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);

		return super.findOneResult(Admin.FIND_ADMIN_BY_ID, parameters);
	}

	public Admin findAdminByNameAndPassword(String name, String password) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", name);
		parameters.put("password", password);
		return super.findOneResult(Admin.FIND_ADMIN_BY_MAME_AND_PASSWORD, parameters);
	}
}
