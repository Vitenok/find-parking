<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.iti.parking.entity.ParkingRecord"%>
<html>
<head>

<link type="text/css" rel="stylesheet" href="stylesheet.css" />

<title>Client statistics</title>
</head>
<body>
	<div id="header">
		<h2>Client statistics</h2>
	</div>
	<p>
	<h3 style="text-align: center;">Current Parking State</h3>
	<div id="table">
		<table  border="1">
			<tr>
				<th style="background-color: #E6E6E6; text-align: center;">ID</th>
				<th style="background-color: #E6E6E6; text-align: center;">Parking
					ID</th>
				<th style="background-color: #E6E6E6; text-align: center;">User
					Car Number</th>
				<th style="background-color: #E6E6E6; text-align: center;">Start
					Time</th>
				<th style="background-color: #E6E6E6; text-align: center;">End
					 Time </th>

			</tr>
			<%
				List<ParkingRecord> currentParkingViewer = (List<ParkingRecord>) request.getAttribute("currentParkingViewer");
				int i = currentParkingViewer.size();
				for (int n = 0; n < i; n++) {
					ParkingRecord pve = currentParkingViewer.get(n);
			%>
			<tr>
				<td style="text-align: center;"><%=pve.getId()%></td>
				<td style="text-align: center;"><%=pve.getParkingID()%></td>
				<td style="text-align: center;"><%=pve.getUserCarNumber()%></td>
				<td style="text-align: center;"><%=pve.getStartTime()%></td>
				<td style="text-align: center;"><%=pve.getEndTime()%></td>
			</tr>
			<%
				}
			%>

		</table>
	</div>

	<p>
	<h3 style="text-align: center;">Archived Parking State</h3>
	<div id="table">
		<table border="1">
			<tr>
				<th style="background-color: #E6E6E6; text-align: center;">ID</th>
				<th style="background-color: #E6E6E6; text-align: center;">Parking
					ID</th>
				<th style="background-color: #E6E6E6; text-align: center;">User
					Car Number</th>
				<th style="background-color: #E6E6E6; text-align: center;">Start
					Time</th>
				<th style="background-color: #E6E6E6; text-align: center;">End
					Time</th>

			</tr>
			<%
				List<ParkingRecord> historicalParkingViewer = (List<ParkingRecord>) request.getAttribute("historicalParkingViewer");
				int c = historicalParkingViewer.size();
				for (int n = 0; n < c; n++) {
					ParkingRecord pve = historicalParkingViewer.get(n);
			%>
			<tr>
				<td style="text-align: center;"><%=pve.getId()%></td>
				<td style="text-align: center;"><%=pve.getParkingID()%></td>
				<td style="text-align: center;"><%=pve.getUserCarNumber()%></td>
				<td style="text-align: center;"><%=pve.getStartTime()%></td>
				<td style="text-align: center;"><%=pve.getEndTime()%></td>
			</tr>
			<%
				}
			%>

		</table>
	</div>
</body>
</html>