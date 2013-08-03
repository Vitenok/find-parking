package com.iti.parking.ws.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class ParkingOrderEmulatorWebService {

	@WebMethod
	public String placeOrder(int carNumber, int paringId, String endDate) {
		// TODO method stub
		return "Ich LIebe Vitek :*:*:*";
	}

}
