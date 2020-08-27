import java.util.ArrayList;

public interface IDatabaseService {

	ArrayList<Ship> readDatabase();
	
	void getShipPosition();

	void updateCurrentCoordinatesAndBearingAndNauticalMilage(int shipID, int shipLogID, String coordinates,
			String bearing, int nauticalMilage);

	void updateCurrentRoute(int shipID, int shipLogID, String currentRoute, String destinationCoordinates);
}
