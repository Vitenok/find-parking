package com.iti.parking.ws.server;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.iti.services.StateService;

@WebService
public class ParkingOrderEmulatorWebService {

	@WebMethod
	public String placeOrder(String carNumber, int paringId, String endDate) {

		try {
			// endDate in milliseconds convert to Date
			Date finalDate = new Date(Long.valueOf(endDate));
			StateService stateService = new StateService();
			stateService.registerOrder(carNumber, paringId, finalDate);
			return "Success";
		} catch (Exception e) {
			return "Fail";
		}
	}
}
