package com.iti.parking.ws.publisher;

import javax.xml.ws.Endpoint;

import com.iti.parking.ws.server.ParkingOrderEmulatorWebService;



public class EmulatorPublisher {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/ws/emulator", new ParkingOrderEmulatorWebService());

	}

}
