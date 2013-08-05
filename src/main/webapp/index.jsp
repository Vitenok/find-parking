<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.iti.parking.entity.jpa.ParkingCurrentState"%>
<%@ page import="com.iti.parking.entity.jpa.ParkingHistoricalState"%>
<html>
<head>
<title>Parking page</title>
<style type="text/css">
	#car-form{
		margin: auto;
		clear: both;
		text-align: center;
	}
</style>

<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet">

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=true"></script>
<script type="text/javascript" src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
 
</head>
<body class="text-center" >
<div class="navbar navbar-inverse">
<div class="navbar-inner">
    <div class="container">
    <h1>Home page</h1>
    	<div id="users-contain">
			<form method="POST"
				action="${pageContext.request.contextPath}/admin"
				style="text-align: right">
				<input type="submit" value="Admin login" class="btn">
			</form>
		</div>
    </div>
   </div> 
</div>



<script type="text/javascript">
	<jsp:include page="map.jsp"/>
</script>

	
	 <form method="POST" action="${action}" id="car-form">
	   <fieldset>
	     <label id ="button-label">Enter car number here to check your data:</label>
	     <input type="text" name="carNumber" >
	     	<span class="help-block"></span>
	     	<button type="submit" class="btn" id="button">Submit</button>
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
				<th>Parking address</th>
				<th>Car Number</th>
				<th>Start Time</th>
				<th >End Time</th>
			</tr>
			
			<tr>
				<td><%=currentStateForCar.getId()%></td>
				<td><%=currentStateForCar.getParking().getParkingAddress()%></td>
				<td><%=currentStateForCar.getParkingUserCarNumber()%></td>
				<td><%=currentStateForCar.getParkingUserStartTime()%></td>
				<td><%=currentStateForCar.getParkingUserEndTime()%></td>
			</tr>
		</table>
		<%
			}
		%>

		<%
			List<ParkingHistoricalState> historicalStateForCar = (List<ParkingHistoricalState>) request.getAttribute("historicalStateForCar");
				if (historicalStateForCar != null) {
		%>
		<h3>Archived Car State</h3>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>Parking address</th>
					<th>Car Number</th>
					<th>Start Time</th>
					<th>End Time</th>
				</tr>
			<thead>
			<tbody>
		<%
					for (ParkingHistoricalState pve : historicalStateForCar) {
		%>
				<tr>
					<td><%=pve.getId()%></td>
					<td><%=pve.getParking().getParkingAddress()%></td>
					<td><%=pve.getParkingUserCarNumber()%></td>
					<td><%=pve.getParkingUserStartTime()%></td>
					<td><%=pve.getParkingUserEndTime()%></td>
				</tr>
		
				<%
					}
			 	} 
				%>
		  </table>
	</body>
</html>