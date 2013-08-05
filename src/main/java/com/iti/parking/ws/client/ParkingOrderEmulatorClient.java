package com.iti.parking.ws.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.iti.parking.entity.jpa.ParkingPlace;
import com.iti.parking.ws.client.generated.ParkingOrderEmulatorWebService;
import com.iti.parking.ws.client.generated.ParkingOrderEmulatorWebServiceService;
import com.iti.services.ParkingPlaceService;

public class ParkingOrderEmulatorClient implements Runnable {
	public static boolean isActive;

	private static List<String> carNumbers = new ArrayList<String>();
	private static List<Integer> availableTimePeriods = new ArrayList<Integer>();

	private static ParkingPlaceService parkingPlaceService = new ParkingPlaceService();

	// Creasting WebService method
	private static ParkingOrderEmulatorWebServiceService service = new ParkingOrderEmulatorWebServiceService();
	private static ParkingOrderEmulatorWebService client = service.getParkingOrderEmulatorWebServicePort();

	static {
		carNumbers.add("one");
		carNumbers.add("two");
		carNumbers.add("three");
		carNumbers.add("four");
		carNumbers.add("five");
		carNumbers.add("six");
		carNumbers.add("seven");
		carNumbers.add("eight");
		carNumbers.add("nine");
		carNumbers.add("ten");

		availableTimePeriods.add(15 * 60000);
		availableTimePeriods.add(30 * 60000);
		availableTimePeriods.add(45 * 60000);
		availableTimePeriods.add(60 * 60000);
		availableTimePeriods.add(240 * 60000);
	}

	public void startEmulation() {
		new Thread(new ParkingOrderEmulatorClient()).start();
	}

	@Override
	public void run() {
		while (isActive) {

			// picking random car number
			Random randomCar = new Random();
			int rangeCarNumbers = carNumbers.size();
			int randomCarNumberIndex = randomCar.nextInt(rangeCarNumbers);
			String randomCarNumber = carNumbers.get(randomCarNumberIndex);

			// picking random parkingId from parking places
			List<ParkingPlace> parkingPlaces = parkingPlaceService.findAllParkingPlaces();
			Random rn = new Random();
			int rangeParkingPlaces = parkingPlaces.size();
			int randomParkingIndex = rn.nextInt(rangeParkingPlaces);
			int randomParkingId = parkingPlaces.get(randomParkingIndex).getId();

			// picking random time interval
			Random randomTimer = new Random();
			int rangeTime = availableTimePeriods.size();
			int randomTimeIndex = randomTimer.nextInt(rangeTime);
			int randomTimePeriod = availableTimePeriods.get(randomTimeIndex);
			long randomTimeLong = System.currentTimeMillis() + randomTimePeriod;
			String randomTime = String.valueOf(randomTimeLong);

			String result = client.placeOrder(randomCarNumber, randomParkingId, randomTime);

			System.out.println("Placing order for car nr. " + randomCarNumber + ", on parking " + randomParkingId + " for " + randomTimePeriod / 60000 + " minutes");

			// just to see in console
			System.out.println(result);
			try {
				double nextRunIn = Math.random() * 900000;
				System.out.println("Next run in " + nextRunIn / 60000 + " minutes");
				Thread.sleep((long) nextRunIn); // sleeps 0 to 15 minutes

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}