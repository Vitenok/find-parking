package com.iti.parking.ws.client;

import com.iti.parking.ws.client.generated.ParkingOrderEmulatorWebService;
import com.iti.parking.ws.client.generated.ParkingOrderEmulatorWebServiceService;


public class ParkingOrderEmulatorClient {

	public static void main(String[] args) {
		ParkingOrderEmulatorWebServiceService service = new ParkingOrderEmulatorWebServiceService();
		ParkingOrderEmulatorWebService emulator = service.getParkingOrderEmulatorWebServicePort();

		System.out.println(emulator.placeOrder("1", 1, "1"));
	}
}
