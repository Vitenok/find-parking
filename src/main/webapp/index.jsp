<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.iti.parking.entity.jpa.ParkingPlace"%>
<%@ page import="com.iti.parking.entity.jpa.ParkingCurrentState"%>
<%@ page import="com.iti.parking.entity.jpa.ParkingHistoricalState"%>
<html>
<head>
<title>Mega Parking page</title>
<link href="css/bootstrap.css" rel="stylesheet">
	<script type="text/javascript">
		<jsp:include page="js/jquery-2.0.3.min.js" />
		<jsp:include page="js/bootstrap.js" />
	</script>

</head>
<body class="text-center" >

 <!-- <jsp:include page="map.jsp" /> -->
<div class="navbar navbar-inverse">
<div class="navbar-inner">
    <div class="container">
    <h1>Home page</h1>
    </div>
   </div> 
</div>
	<table class="table table-bordered">
		<thead>
			<caption><h3>All Parking Places</h3></caption>
			<tr>
				<th>ID</th>
				<th>Parking address</th>
				<th>Parking capacity</th>
				<th>Parking available slots</th>
			</tr>
		</thead>
			<tbody>
				<%
				List<ParkingPlace> allParkingPlacesViewer = (List<ParkingPlace>) request.getAttribute("tableRows");
				if (allParkingPlacesViewer != null) {
					for (ParkingPlace pve : allParkingPlacesViewer) {
				%>
				<tr>
					<td><%=pve.getId()%></td>
					<td><%=pve.getParkingAddress()%></td>
					<td><%=pve.getParkingCapacity()%></td>
					<td><%=pve.getParkingAvailableSlots()%></td>
				</tr>
				<%
					}
				}
				%>
			</tbody>
	</table>
	
 <form method="POST" action="${action}">
   <fieldset>
     <label>Enter car number here to check your data:</label>
     <input type="text" name="carNumber" placeholder="Type here...">
     	<span class="help-block"></span>
     	<button type="submit" class="btn">Submit</button>
   </fieldset>
 </form>
	

	<%
		ParkingCurrentState currentStateForCar = (ParkingCurrentState) request.getAttribute("currentStateForCar");
			if (currentStateForCar != null) {
	%>
	<h3>Current Car State</h3>
	<table class="table table-bordered">
			<tr>
				<th>ID</th>
				<th>Parking ID</th>
				<th>Car Number</th>
				<th>Start Time</th>
				<th >End Time</th>
			</tr>
			
			<tr>
				<td><%=currentStateForCar.getId()%></td>
				<td><%=currentStateForCar.getParkingId()%></td>
				<td><%=currentStateForCar.getParkingUserCarNumber()%></td>
				<td><%=currentStateForCar.getParkingUserStartTime()%></td>
				<td><%=currentStateForCar.getParkingUserEndTime()%></td>
			</tr>
			<%
				}
			%>

		</table>
	
	<%
		List<ParkingHistoricalState> historicalStateForCar = (List<ParkingHistoricalState>) request.getAttribute("historicalStateForCar");
			if (historicalStateForCar != null) {
				for (ParkingHistoricalState pve : historicalStateForCar) {
	%>
	<h3>Archived Car State</h3>
	<table class="table table-bordered">
			<tr>
				<th>ID</th>
				<th>Parking ID</th>
				<th>Car Number</th>
				<th>Start Time</th>
				<th>End Time</th>
			</tr>
			
			<tr>
				<td><%=pve.getId()%></td>
				<td><%=pve.getParkingId()%></td>
				<td><%=pve.getParkingUserCarNumber()%></td>
				<td><%=pve.getParkingUserStartTime()%></td>
				<td><%=pve.getParkingUserEndTime()%></td>
			</tr>
			</table>
			<%
				}
			} 
			%>
	</body>
</html>