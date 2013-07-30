<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.iti.parking.entity.jpa.ParkingCurrentState"%>
<%@ page import="com.iti.parking.entity.jpa.ParkingHistoricalState"%>
<%@ page import="com.iti.parking.entity.jpa.ParkingPlace"%>
<%@ page import="com.iti.parking.entity.jpa.Admin"%>
<head>
<title>Admin page</title>
	
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet">

<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=true"></script>
<script type="text/javascript" src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">

	function showErrorMessageFor(element, msg){
		element.parent().parent().addClass('error');
		element.after('<span class=\"help-inline\">'+ msg +'</span>');
	}
	
	function cleanErrorMessages(elements){
		var arrayLength = elements.length;
		element = null;
		for (var i = 0; i < arrayLength; i++){
			element = elements[i];
			element.parent().parent().removeClass('error');
			element.next('span').remove();
		}
	}
	
	//Remove Parking
	function prepareRemoveDialog(action, entityId, entityName1, entityName2) {  //entityName1 == "XXX";  entityName2 == "XXX/"
		$('#dialog-delete').modal();
		$("#dialog-delete-title").text(action.substr(0,1).toUpperCase()+action.substr(1) + " " + entityName1);  
		
	    $('#delete-button').click(function() {
          	  	var request = $.ajax({
				url : 'admin/'+ entityName2 + action + entityName1.substr(0,1).toUpperCase() + entityName1.substr(1),    //'admin/parking/deleteParking'
				type : 'POST',
				data : { entityId: entityId}, 
				dataType : 'text'
			});
			request.done(function(msg) {
			   location.reload();
			});
			request.fail(function(jqXHR, textStatus) {
			});
	    });
	  }	
	

	//Create and update Parking 
	function prepareCreateUpdateParkingDialog(action, entityId, address, capacity, slots) {
		
		$('#dialog-parking').modal();
		
		$('#address').val(address);
		$('#capacity').val(capacity);
		$('#slots').val(slots);
		
		$("#dialog-parking-title").text(action.substr(0,1).toUpperCase()+action.substr(1)+' Parking');
		
		cleanErrorMessages([$('#address'), $('#capacity'), $('#slots')]);
		
	    $('#parking-button').click(function() {
	    	cleanErrorMessages([$('#address'), $('#capacity'), $('#slots')]);
	    	
	    	//check address length
	    	if ($('#address').val().length < 5){
	    		showErrorMessageFor($('#address'), 'Address should be longer than 4');
	    		return;
	    	}
	    	
	    	//check capacity length and is number
	    	if(!$('#capacity').val().length > 0 || isNaN($('#capacity').val())){
	    		showErrorMessageFor($('#capacity'), 'Field should be a number');
	    		return;
	    	}
	    	
	    	//check slots length and is number
	    	if(!$('#slots').val().length > 0 | isNaN($('#slots').val())){
	    		showErrorMessageFor($('#slots'), 'Field should be a number');
	    		return;
	    	}
	    	
	    	//check capacity >= available
	    	if($('#capacity').val() < $('#slots').val()){
	    		showErrorMessageFor($('#slots'), 'Number of available slots should be less or equal to capacity');
	    		return;
	    	}
	    	
	    	$(this).attr('disabled','disabled'); 	
	   	  	var request = $.ajax({
				url : 'admin/parking/'+action+'Parking',
				type : 'POST',
				data : { address: $("#address").val(),  capacity: $("#capacity").val(), slots: $("#slots").val(), entityId: entityId},
				dataType : 'text'
			});
			request.done(function(msg) {
			   location.reload();
			});
			request.fail(function(jqXHR, textStatus) {
				$('#parking-button').removeAttr('disabled');
				showErrorMessageFor($('#address'), 'Parking with this address already exists');
			});
	    });
	}	

	//Create and update Admin
	function prepareCreateUpdateAdminDialog(action, entityId, login, pass, confirmedPass) {
		
		$('#dialog-admin').modal();
			
		$('#login').val(login);
		$('#password').val(pass);
		$('#confirm-password').val(confirmedPass);
							
		$("#dialog-admin-title").text(action.substr(0,1).toUpperCase()+action.substr(1)+' Admin');
		
		//clean error classes and remove spans with messages if they where set from previous check
		cleanErrorMessages([$('#login'), $('#password'), $('#confirm-password')]);

		$('#admin-button').click(function() {
			cleanErrorMessages([$('#login'), $('#password'), $('#confirm-password')]);
			
	    	//login length check
	    	if ($('#login').val().length < 4){
	    		showErrorMessageFor($('#login'), 'Login should be longer than 3');
	    		return;
	    	}
	    	
	    	//password length check
	    	if ($('#password').val().length < 4){
	    		showErrorMessageFor($('#password'), 'Password should be longer than 3');
				return;
	    	}
	    	
	    	//password match check
	    	if ($('#password').val() != $('#confirm-password').val()) {
	    		showErrorMessageFor($('#password'), 'Passwords should match');
	    		showErrorMessageFor($('#confirm-password'), 'Passwords should match');
	    		return;
	 		}
		
   			$(this).attr('disabled','disabled');
   			
			var request = $.ajax({
				url : 'admin/'+action+'Admin',
				type : 'POST',
				data : {login: $("#login").val(),  pass: $("#password").val(), entityId: entityId},
				dataType : 'text'
			});
			request.done(function(msg) {
 				location.reload();
			});
			request.fail(function(jqXHR, textStatus) {
				$('#admin-button').removeAttr('disabled');
				showErrorMessageFor($('#login'), 'Admin with this login already exists');
			});
	    });
     }
	</script>
</head>
<body class="text-center">
	
	<div class="navbar navbar-inverse">
		<div class="navbar-inner">
			<div class="container">
     			<h1>Admin page</h1>
     			<div id="users-contain">
					<form method="POST" action="${pageContext.request.contextPath}/adminLogout" style="text-align:right">
						<input type="submit" value="Logout" class="btn">
					</form>
				</div>
    	    </div>
   		</div> 
   </div>	
   
	<script type="text/javascript">
		<jsp:include page="map.jsp"/>
	</script>
	
	<table class="table table-bordered" >
		<thead>
			<caption><h3>All Parking Places</h3></caption>
			<tr>
				<th>ID</th>
				<th>Parking address</th>
				<th>Parking capacity</th>
				<th>Parking available slots</th>
				<th>Actions</th>
			</tr>
		
			<tbody>
				<%
				List<ParkingPlace> allParkingPlacesViewer = (List<ParkingPlace>) request.getAttribute("allParkingPlacesViewer");
				if (allParkingPlacesViewer != null) {
					for (ParkingPlace pve : allParkingPlacesViewer) {
				%>
				<tr>
					<td><%=pve.getId()%></td>
					<td><%=pve.getParkingAddress()%></td>
					<td><%=pve.getParkingCapacity()%></td>
					<td><%=pve.getParkingAvailableSlots()%></td>
					<td>
						<button id="update-parking" class="btn" value="<%=pve.getId()%>" onclick="prepareCreateUpdateParkingDialog('update','<%=pve.getId()%>', '<%=pve.getParkingAddress()%>', '<%=pve.getParkingCapacity()%>', '<%=pve.getParkingAvailableSlots()%>')">Update</button>
						<button id="delete-parking" class="btn" value="<%=pve.getId()%>" onclick="prepareRemoveDialog('delete', '<%=pve.getId()%>', 'parking', 'parking/')">Delete</button>
					</td>
				</tr>
				<%
					}
				}
				%>
			</tbody>
	</table>
	<button id="create-parking" class="btn"  onclick="prepareCreateUpdateParkingDialog('create','','','','')">Create new parking place</button>
	
	
	
	<h3>Current Parking State</h3>
	<table class="table table-bordered">
		<tr>
			<th>ID</th>
			<th>Parking ID</th>
			<th>Parking address</th>
			<th>User Car Number</th>
			<th>Start Time</th>
			<th>End Time</th>
		</tr>
		<%
			List<ParkingCurrentState> currentParkingViewer = (List<ParkingCurrentState>) request.getAttribute("currentParkingViewer");
			if (currentParkingViewer != null) {
				for (ParkingCurrentState pve : currentParkingViewer) {
		%>
		<tr>
			<td><%=pve.getId()%></td>
			<td><%=pve.getParking().getId()%></td>
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
		
	<h3>Archived Parking State</h3>
	<table class="table table-bordered">
		<tr>
			<th>ID</th>
			<th>Parking ID</th>
			<th>Parking address</th>
			<th>User Car Number</th>
			<th>Start Time</th>
			<th>End Time</th>

		</tr>
		<%
			List<ParkingHistoricalState> historicalParkingViewer = (List<ParkingHistoricalState>) request.getAttribute("historicalParkingViewer");
			if (historicalParkingViewer != null) {
				for (ParkingHistoricalState pve : historicalParkingViewer) {
		%>
		<tr>
			<td><%=pve.getId()%></td>
			<td><%=pve.getParking().getId()%></td>
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
	
	
	<!--  CREATE AND UPDATE PARKING DIALOG FORM HERE -->	
	<div id="dialog-parking" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="doWeNeedIt" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		    	<h3 id="dialog-parking-title"></h3>
		    	<p>All form fields are required</p>
	    	</div>
	    	<div class="modal-body">
	    		<form class="form-horizontal" id="updateParking" name="updateParking">
	    			<div class="control-group">
						<label class="control-label" for="address">Address:</label>
						<div class="controls">
	 						<input type="text" name="address" id="address" class="text ui-widget-content ui-corner-all" >
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="capacity">Capacity:</label>
						<div class="controls">
	 						<input type="text" name="capacity" id="capacity" class="text ui-widget-content ui-corner-all" >
						</div>
					</div>
					<div class="control-group">	
						<label class="control-label" for="slots">Available slots:</label>
						<div class="controls">
	 						<input type="text" name="slots" id="slots" class="text ui-widget-content ui-corner-all">
						</div>
					</div>	
				</form>
			</div>
			<div class="modal-footer">
					<button class="btn" type="submit" id="parking-button">Save</button>	
			</div>
	</div>
	
	<!--  DELETE PARKING DIALOG FORM HERE -->
	<div id="dialog-delete" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="doWeNeedIt" aria-hidden="true">
		<div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		    <h3 id ="dialog-delete-title"></h3>
	    </div>
		<div class="modal-body">
			Are you sure you want to permanently delete this item?
		</div>
		<div class="modal-footer">
   			<button class="btn" id="delete-button">Delete</button>	
		</div>
	</div>
	
	<!--  CREATE AND UPDATE ADMIN DIALOG FORM HERE -->	
	<div id="dialog-admin" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="doWeNeedIt" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="dialog-admin-close">&times;</button>
		    	<h3 id="dialog-admin-title"></h3>
		    	<p>All form fields are required</p>
	    	</div>
	    	<div class="modal-body">
	    		<form class="form-horizontal" id="updateAdmin" name="updateAdmin">
	    			<div class="control-group">
						<label class="control-label" for="login">Login:</label>
						<div class="controls">
	 						<input type="text" name="login" id="login" >
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">Password:</label>
						<div class="controls">
	 						<input type="password" name="password" id="password" >
						</div>
					</div>
					<div class="control-group">	
						<label class="control-label" for="confirm-password">Confirm password:</label>
						<div class="controls">
	 						<input type="password" name="confirm-password" id="confirm-password">
						</div>
					</div>	
				</form>
			</div>
			<div class="modal-footer">
					<button class="btn" type="submit" id="admin-button">Save</button>	
			</div>
	</div>
		
		<h3>All Admins</h3>
		<table class="table table-bordered"> 
				<thead>
					<tr>
						<th>Admin Name</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<%
						List<Admin> allAdminsViewer = (List<Admin>) request.getAttribute("allAdminsViewer");
						if (allAdminsViewer != null) {
							for (Admin pve : allAdminsViewer) {
					%>
	
					<tr>
						<td><%=pve.getName()%></td>
						<td>
							<button id="update-admin" class="btn" value="<%=pve.getId()%>" onclick="prepareCreateUpdateAdminDialog('update','<%=pve.getId()%>', '<%=pve.getName()%>', '', '')" >Update</button>
							<button id="delete-admin" class="btn" value="<%=pve.getId()%>" onclick="prepareRemoveDialog('delete', '<%=pve.getId()%>', 'admin', '')">Delete</button> 
						</td>
					</tr>
					<%
							}
						}
					%>
				</tbody>
		</table>
	
	<button id="create-admin" class="btn" onclick="prepareCreateUpdateAdminDialog('create','','','','')">Create new admin</button>

</body>
</html>