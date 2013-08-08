<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.iti.parking.entity.jpa.ParkingCurrentState"%>
<%@ page import="com.iti.parking.entity.jpa.ParkingHistoricalState"%>
<%@ page import="com.iti.parking.entity.jpa.ParkingPlace"%>
<!DOCTYPE html>
<html>

<head>
<title>Parking page</title>
<style type="text/css">
body { 
	padding-bottom: 70px; 
	padding-top: 0px;
}

	#car-form{
		margin: auto;
		clear: both;
		font-size: 3px;
	}
	
	#car-form ::-webkit-input-placeholder {
 		font-size: 12px;
	}
</style>

<link href="css/bootstrap.min.css" rel="stylesheet">

<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=true"></script>
<script type="js/bootstrap.js"></script>

<script type="text/javascript">

//Create an order 
//
	function placeOrder(){

		var time = parseInt($('#time-value').val());
		var timeUnit = $('#time-units').val();
		var currentTimeMilis = new Date().getTime();
		
		
		if(timeUnit == 'minutes') {
			currentTimeMilis += time * 60*1000;
		} else if (timeUnit == 'hours') {
			currentTimeMilis += time * 60*60*1000;
		} else if (timeUnit == 'days') {
			currentTimeMilis += time * 60*60*24*1000;
	    }	
		
		
		var request = $.ajax({
				    	type: 'POST',
				        url: 'makeOrder', 
				        data: { parkingId: $('#parking-id').val(),  endTime: currentTimeMilis, carNumber: $('#car-number-order').val() }
				    });
	    request.done(function(msg) {
	    	   $('#dialog-confirm').modal();
	    	   ///$('#parking-button').
	    	   //location.reload();
			   
	    });
		request.fail(function(jqXHR, textStatus) {
		});
	};
	
	function reloadPage(){
		location.reload();
	}
</script>
 
</head>

<body  >

<div class="navbar">
<div class="navbar-inner">
    <div class="container">
    	<div id="users-contain">
    		<h3>Parkings</h3>
			<form method="POST"
				action="${pageContext.request.contextPath}/admin"
				style="text-align: right">
				<input type="submit" value="Admin login" class="btn">
			</form>
		</div>
    </div>
   </div> 
</div>  

<div class="navbar ">
	<div class="navbar-inner">
   		<div class="container">
    		<h1> </h1>
    		<div id="users-contain">
    			<div class="pull-right">
					<div class="input-append">
	 					<form method="POST" action="${action}" id="car-form" >
	     					<input type="text" class="input-xlarge" name="carNumber"  placeholder="Enter car number here to check your car state" font-size="11">
	     					<button type="submit" class="btn" id="button">Submit</button>
	 					</form>
	 				</div>
				</div>
	
				<div class="pull-left" >
					<div class="input-append">
					<%
						List<ParkingPlace> allParkingPlacesViewer = (List<ParkingPlace>) request.getAttribute("allParkingPlacesViewer");
							if (allParkingPlacesViewer != null) {
								
					%>
						<select id="parking-id" name="parking-id">
					<%
								for (ParkingPlace pve : allParkingPlacesViewer) {
					%>
	       					  		<script type="text/javascript"> 
										document.write('<option value="'+<%=pve.getId()%>+ '">'+'<%=pve.getParkingAddress()%>'+'</option>');
							  </script>
					 <%
								}
					 %>
                        </select>
                   
					 <% 
							}
					 %>      
 		                <select class="span1" id="time-value" name="time-value">
 		                   <script type="text/javascript"> 
 		                   		for (var i = 1; i < 61; i++) {
									document.write('<option value="' + i + '">'+i+'</option>');
									}
							</script>
 		                 </select>
 		          
 		                 <select class="span2" id="time-units" name="time-units">
 			             	<option>minutes</option>
		    				<option>hours</option>
							<option>days</option>
 						</select>
		                <input class="span2" id="car-number-order" name="car-number-order" type="text" placeholder="Your car number..." font-size="11">
		                
		                <button class="btn" id="make-order-button" type="button" onclick="placeOrder();">Make order</button>
					</div>
				</div>
			</div>
    	</div>
   </div> 
</div>
  
<script type="text/javascript">
	<jsp:include page="map.jsp"/>
</script><br><br>


		<%
			ParkingCurrentState currentStateForCar = (ParkingCurrentState) request.getAttribute("currentStateForCar");
				if (currentStateForCar != null) {
		%>
		<h4>Current Car State</h4>
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
				if (historicalStateForCar != null && !historicalStateForCar.isEmpty()) {
		%>
		<h4>Archived Car State</h4>
		<table class="table table-bordered">
				<tr>
					<th>ID</th>
					<th>Parking address</th>
					<th>Car Number</th>
					<th>Start Time</th>
					<th>End Time</th>
				</tr>
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
		  
	<!--  CREATE CONFIRM ORDER DIALOG FORM HERE -->
	<div id="dialog-confirm" class="modal hide fade" tabindex="-1" role="dialog">
		<div class="modal-header">
			<button type="button" class="close"  data-dismiss="modal"aria-hidden="true" >&times;</button>
			<h3 id="">Confirming order</h3>
		</div>
		<div class="modal-body">
			<p>Your order was successfully added</p>
		</div>
		<div class="modal-footer">
			<button class="btn" type="submit" id="confirm-button" onclick="reloadPage();" >Ok</button>
        </div>
		</div>
	</body>
</html>