find-parking
============

Study project to practice in web development.

Shows parking slots available in the city.
Allows user to see available parkings, their free capacity
and to get direction from user's current location.
User can order time on specific parking for specified 
time period starting from now.
Order placing is done by calling web service. So it's
possible to call it from external systems (for ex. mobile apps). 

In admin module (login:admin, password:admin) it is possible
to manage parking places and admins. Available detailed information
about current load of parkings with cars on them and their 
staying period. Also available historical view of parkings use.
It is possible to simulate user activity by lunching emulator from
admin page. Emulator creates orders in random order and time.
Can be stopped from the same admin page. 

Technologies stack:
DB: MySQL 5.1, 
Servlet Container: Apache Tomcat 7.
Platforms/frameworks:
JPA, Servlets 3.0, JAX-WS 2.2, JSP,
JQuery 2, Twitter Bootstrap 3, Google Maps API


Project URL:
http://parking-iti.rhcloud.com/
