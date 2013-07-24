package com.iti.parking.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iti.parking.entity.jpa.Admin;

/**
 * Servlet implementation class AdminLogoutController
 */
@WebServlet("/adminLogout")
public class AdminLogoutController extends HttpServlet {
	private static final long serialVersionUID = -771275339581596171L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Admin admin = (Admin) session.getAttribute("admin");
			if (admin != null) {
				session.removeAttribute("admin");
				System.out.println("admin was removed from session:" + admin);
			}
			request.getRequestDispatcher("").forward(request, response);
		}
	}

}
