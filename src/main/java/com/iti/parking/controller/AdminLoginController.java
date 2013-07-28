package com.iti.parking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iti.parking.entity.jpa.Admin;
import com.iti.services.jpa.AdminService;

/**
 * Servlet implementation class AdminLoginController
 */
@WebServlet("/adminLogin")
public class AdminLoginController extends HttpServlet {

	private static final long serialVersionUID = -8664013221862595080L;

	AdminService adminService = new AdminService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter("login");
		String password = request.getParameter("password");

		HttpSession session = request.getSession();

		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
		} else {
			Admin admin = adminService.isValidLogin(login, password);
			if (admin != null) {
				session.setAttribute("adminId", admin.getId());
				response.sendRedirect("/find-parking/admin");
			} else {
				request.setAttribute("errorMsg", "Invalid login or password");
				request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
			}
		}
	}

}
