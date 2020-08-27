import java.util.ArrayList;

public interface IDatabaseService {

	// CRUD
	void updateDatabase();

	ArrayList<Ship> readDatabase();
	
	void getShipPosition();

	void updateCurrentCoordinatesAndBearingAndNauticalMilage(int shipID, int shipLogID, String coordinates,
			String bearing, int nauticalMilage);

	void updateDestinationStartCoordinates(int shipID, int shipLogID, String destination,
			String start);

	void updateCurrentRoute(int shipID, int shipLogID, String currentRoute, String destinationCoordinates);
}
