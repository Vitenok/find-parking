<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.iti.parking.entity.ParkingRecord"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="stylesheet.css" />
<title>Mega Parking page</title>
</head>
<body><div id="header">
	<h2>Parking places list for Admin</h2>
</div>
	<p>
	<h3 style="text-align: center;">Current Parking State</h3>

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
			List<ParkingRecord> CurrentParkingViewer = (List<ParkingRecord>) request.getAttribute("CurrentParkingViewer");
			int i = CurrentParkingViewer.size();
			for (int n = 0; n < i; n++) {
				ParkingRecord pve = CurrentParkingViewer.get(n);
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

	<p>
	<h3 style="text-align: center;">Archived Parking State</h3>
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
			List<ParkingRecord> HistoricalParkingViewer = (List<ParkingRecord>) request.getAttribute("HistoricalParkingViewer");
			int c = HistoricalParkingViewer.size();
			for (int n = 0; n < c; n++) {
				ParkingRecord pve = HistoricalParkingViewer.get(n);
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
</body>