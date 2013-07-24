import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.iti.parking.entity.jdbc.ParkingPlace;


public class ParkingViewerTest {
	
	/**
	 * 
	 */
	
	Connection connection;
	Integer parking_ID;
	String parking_address;
	Integer parking_capacity;
	String parking_available_slots;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void connectionDBTest() {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/parking", "root", "root");

			Statement statement = connection.createStatement();
		//	response.setContentType("text/html");

			List parkingViewer = new ArrayList();

			ResultSet resultSet = statement.executeQuery("select * from parking_places");
			while (resultSet.next()) {
				Integer parking_ID = resultSet.getInt("id");

				String parking_address = resultSet.getString("parking_address");
				Integer parking_capacity = resultSet.getInt("parking_capacity");
				String parking_available_slots = resultSet.getString("parking_available_slots");

				ParkingPlace tableRow = new ParkingPlace(parking_ID, parking_address, parking_capacity, parking_available_slots);

				parkingViewer.add(tableRow);

				resultSet.close();
				statement.close();

				System.out.println(parkingViewer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

		
	public static void main(String[] args)  {
		
		ParkingViewerTest pvt = new ParkingViewerTest();
		
		pvt.connectionDBTest();
	}

}
