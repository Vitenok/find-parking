<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.iti.parking.entity.jpa.ParkingPlace"%>
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet">

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=true"></script>
<script type="text/javascript" src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>

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
#directionsPanel{
	float: right; 
	width: 28%; 
	height: 70%; 
	overflow: auto
}
</style>

<script type="text/javascript">

	var rendererOptions = {
		draggable: false
	};
	
	var directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);;
	var directionsService = new google.maps.DirectionsService();

	function initialize() {
        var mapOptions = {
          zoom: 12,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
        
        directionsDisplay.setMap(map);
        directionsDisplay.setPanel(document.getElementById('directionsPanel'));

        google.maps.event.addListener(directionsDisplay, 'directions_changed', function() {
          computeTotalDistance(directionsDisplay.directions);
        });
        
        var infowindow = new google.maps.InfoWindow({
        });
		
        //show my current location
        showMyLocation(map);
     	
		//show all parkings
		showAllParkings(map,infowindow);
		 
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
    
    function showAllParkings(map, infowindow){
     	// Showing all parkings on the map
     	geocoder = new google.maps.Geocoder();
     	//current location
     	navigator.geolocation.getCurrentPosition(function(position) {
        	pos = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
     	});
     	<%
			List<ParkingPlace> allParkingPlacesViewer = (List<ParkingPlace>) request.getAttribute("allParkingPlacesViewer");
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
             
              if ('<%=pve.getParkingAvailableSlots()%>' >= 10){
              	var parkingAvailableSlotsMarker = new google.maps.Marker({
                 	 map: map, 
                  	 position: results[0].geometry.location,
                  	 title: "Number of available slots on" + "\n" + "<%=pve.getParkingAddress()%>",
                  	 icon: 'http://google-maps-icons.googlecode.com/files/black' + '<%=pve.getParkingAvailableSlots()%>' + '.png'
                  		 
                  		
                  
                });
              } else {
            	  var parkingAvailableSlotsMarker = new google.maps.Marker({
                  	 map: map, 
                   	 position: results[0].geometry.location,
                     title: "Number of available slots on" + "\n" + "<%=pve.getParkingAddress()%>",
                   	 icon: 'http://google-maps-icons.googlecode.com/files/black' + 0 + '<%=pve.getParkingAvailableSlots()%>' + '.png'
                 });
              }
              
             
              
              google.maps.event.addListener(parkingAvailableSlotsMarker, 'click', function() {
            	  infowindow.setContent(contentString);
            	  infowindow.open(map, parkingAvailableSlotsMarker);
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
	<div id="map-canvas" ></div>
	<div id="directionsPanel">
		<p>
			Total Distance: <span id="total"></span>
		</p>
	</div>
	<br>
	<p>
</body>
</html>