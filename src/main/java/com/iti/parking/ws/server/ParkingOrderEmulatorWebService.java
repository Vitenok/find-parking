package com.iti.parking.ws.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class ParkingOrderEmulatorWebService {

	@WebMethod
	public String placeOrder(String carNumber, int paringId, String endDate) {
		//endDate = miliseconds
		// TODO method stub
		return "Ich LIebe Vitek :*:*:*";
	}

}
