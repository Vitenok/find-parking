<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.iti.parking.entity.ParkingPlace"%>
<html>
<head>

<link type="text/css" rel="stylesheet" href="stylesheet.css" />

<title>Mega Parking page</title>
</head>
<body>
	<div id="header">
		<h2>Parking places list</h2>
	</div>

	<div id="form">
		<form method="POST" action="client">

			Login: <input type="text" name="login"><br> Password: <input
				type="password" name="password"><br> <input
				type="submit" value="Submit">

		</form>
	</div>

	<div id="table">
		<table >
			<tr>
				<th style="background-color: #E6E6E6; text-align: center;">ID</th>
				<th style="background-color: #E6E6E6; text-align: center;">Address</th>
				<th style="background-color: #E6E6E6; text-align: center;">Capacity</th>
				<th style="background-color: #E6E6E6; text-align: center;">Available
					slots</th>

			</tr>
			<%
				List<ParkingPlace> tableRows = (List<ParkingPlace>) request.getAttribute("tableRows");
				int i = tableRows.size();
				for (int n = 0; n < i; n++) {
					ParkingPlace pve = tableRows.get(n);
			%>
			<tr>
				<td style="text-align: center;"><%=pve.getParkingID()%></td>
				<td style="text-align: center;"><%=pve.getParkingAddress()%></td>
				<td style="text-align: center;"><%=pve.getparkingCapacity()%></td>
				<td style="text-align: center;"><%=pve.getParkingAvailableSlots()%></td>
			</tr>
			<%
				}
			%>

		</table>
	</div>
</body>
</html>