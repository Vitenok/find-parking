<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.iti.parking.entity.jpa.ParkingPlace"%>
<%@ page import="com.iti.parking.entity.jpa.ParkingCurrentState"%>
<%@ page import="com.iti.parking.entity.jpa.ParkingHistoricalState"%>
<%@ page import="com.iti.services.jpa.ParkingPlaceService"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link href="css/bootstrap.css" rel="stylesheet">

<script type="text/javascript">
		<jsp:include page="js/jquery-2.0.3.min.js" />
		<jsp:include page="js/bootstrap.js" />
	</script>

<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<meta charset="utf-8">
<style type="text/css">
#map-canvas {
	margin: 5;
	padding: 5;
	width: 70%;
	height: 70%;
	float: left;
	max-width: none;
}

img[src*="gstatic.com/"],img[src*="googleapis.com/"] {
	max-width: none;
}

#button {
	margin: auto;
	clear: both;
	text-align: center;
}
</style>

<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=true">
	</script>

<script type="text/javascript">

	var rendererOptions = {
		draggable: false
	};
	
	var directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);;
	var directionsService = new google.maps.DirectionsService();

	function initialize() {
        var mapOptions = {
          zoom: 11,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
        
        directionsDisplay.setMap(map);
        directionsDisplay.setPanel(document.getElementById('directionsPanel'));

        google.maps.event.addListener(directionsDisplay, 'directions_changed', function() {
          computeTotalDistance(directionsDisplay.directions);
        });
		
        //show my current location
        showMyLocation(map);
     	
		//show all parkings
		showAllParkings(map);
		 
      } //end initialize
      
	function showMyLocation(map){
     	// Geolocation
        if(navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function(position) {
          pos = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
            
          var marker = new google.maps.Marker({
                position: pos,
                map: map,
                title: 'Your current location',
                icon: 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png'
            });
        
            map.setCenter(pos);
          }, function() {
            handleNoGeolocation(true);
          });
        } else {
          // if browser doesn't support Geolocation
         handleNoGeolocation(false);
        
      	}
    }
    
    function showAllParkings(map){
     	// Showing all parkings on the map
     	geocoder = new google.maps.Geocoder();
     	//current location
     	navigator.geolocation.getCurrentPosition(function(position) {
        	pos = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
     	});
     	<%
			List<ParkingPlace> allParkingPlacesViewer = (List<ParkingPlace>) request.getAttribute("tableRows");
				if (allParkingPlacesViewer != null) {
					for (ParkingPlace pve : allParkingPlacesViewer) {
		%>
		var address = '<%=pve.getParkingAddress()%>';
        geocoder.geocode( { 'address': address}, function(results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
            	
              map.setCenter(results[0].geometry.location);
              
            
              var contentString = "<br>" 
              +"<h5>"
              +"<%=pve.getParkingAddress()%>"
              +"</h5>"
              + "Capacity is: " 
              + "<%=pve.getParkingCapacity()%>" 
              + "<br>"
              + "Available slots: " 
              + "<%=pve.getParkingAvailableSlots()%>"
              +"<div id='get-direction-button'><br>"
              +"<input type='button' id='get-direction-button' value='Get direction' class='btn' onclick='calulateRoute( pos, \"<%=pve.getParkingAddress()%>\")'  >"  
              +"</div>";
              
              var infowindow = new google.maps.InfoWindow({
                  content: contentString,
              });
              
              var parkingMarker = new google.maps.Marker({
                  map: map, 
                  position: results[0].geometry.location,
                  title: '<%=pve.getParkingAddress()%>'
              });
     
              google.maps.event.addListener(parkingMarker, 'click', function() {
            	  infowindow.open(map, parkingMarker);
  			  		
  			  });
              
            } else {
              alert("Geocode was not successful for the following reason: " + status);
            }
            
          }); 
          <%
					}
				}
		  %>
    }
    
	function calulateRoute(origin, destination){
		  var request = {
		    origin: origin,
		    destination: destination,
		    travelMode: google.maps.DirectionsTravelMode.DRIVING
		  };
		  directionsService.route(request, function(response, status) {
		    if (status == google.maps.DirectionsStatus.OK) {
		      directionsDisplay.setDirections(response);
		    }
		  });
		
	}
	
    function computeTotalDistance(result) {
		  var total = 0;
		  var myroute = result.routes[0];
		  for (var i = 0; i < myroute.legs.length; i++) {
		    total += myroute.legs[i].distance.value;
		  }
		  total = total / 1000;
		  document.getElementById('total').innerHTML = total + ' km';
		}

    function disableButton(element){
    	document.getElementById(element).disabled=true;
    }
      google.maps.event.addDomListener(window, 'load', initialize);
    </script>
</head>
<body class="text-center">
	<div class="navbar navbar-inverse">
		<div class="navbar-inner">
			<div class="container">
				<h1>Home page</h1>
			</div>
		</div>
	</div>

	<div id="map-canvas" ></div>
	<div id="directionsPanel" style="float: right; width: 28%; height: 50%">
		<p>
			Total Distance: <span id="total"></span>
		</p>
	</div>
	<br>
	<p>
	<div id="button">
		<form method="POST" action="${action}">
			<fieldset>
				<label>Enter car number here to check your data:</label> <input
					type="text" name="carNumber" placeholder="Type here..."> <span
					class="help-block"></span>
				<button type="submit" class="btn">Submit</button>
			</fieldset>
		</form>
	</div>
 	
</body>
</html>