import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseService implements IDatabaseService {

	private static Connection conn;

	private static Connection getConnection() {

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fleet", "root",
					ArmadaCongifReader.usingBufferedReader());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	@Override
	public void updateDatabase() {
	}

	@Override
	public ArrayList<Ship> readDatabase() {

		ArrayList<Ship> shipList = new ArrayList<Ship>();

		try {
			Statement myStmt = getConnection().createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from uvships");

			while (myRs.next()) {
				if (myRs.getString(6).equals("Container")) {

					Ship containerCargo = new Container();
					containerCargo.setShipId(myRs.getInt(1));

					containerCargo.setName(myRs.getString(2));
					containerCargo.setMaxCargoWeight(myRs.getInt(3));
					containerCargo.setMaxSpeed(myRs.getInt(4));
					containerCargo.setCruisingSpeed(myRs.getInt(5));
					containerCargo.setCargo(myRs.getString(6));
					containerCargo.setShipLogId(myRs.getInt(7));
					containerCargo.setCurrentCoordinates(myRs.getString(8));
					containerCargo.setDestinationCoordinates(myRs.getString(9));
					containerCargo.setStartCoordinates(myRs.getString(10));
					containerCargo.setCurrentSpeed(myRs.getInt(11));
					containerCargo.setNauticMilage(myRs.getInt(12));
					containerCargo.setBearing(myRs.getString(13));
					containerCargo.setRoute(myRs.getString(14));
					containerCargo.setCurrentRoute(myRs.getString(15));
					shipList.add(containerCargo);
				} else {
					
					Ship oilCargo = new Oil();
					oilCargo.setShipId(myRs.getInt(1));
					oilCargo.setName(myRs.getString(2));
					oilCargo.setMaxCargoWeight(myRs.getInt(3));
					oilCargo.setMaxSpeed(myRs.getInt(4));
					oilCargo.setCruisingSpeed(myRs.getInt(5));
					oilCargo.setCargo(myRs.getString(6));
					oilCargo.setShipLogId(myRs.getInt(7));
					oilCargo.setCurrentCoordinates(myRs.getString(8));
					oilCargo.setDestinationCoordinates(myRs.getString(9));
					oilCargo.setStartCoordinates(myRs.getString(10));
					oilCargo.setCurrentSpeed(myRs.getInt(11));
					oilCargo.setNauticMilage(myRs.getInt(12));
					oilCargo.setBearing(myRs.getString(13));
					oilCargo.setRoute(myRs.getString(14));
					oilCargo.setCurrentRoute(myRs.getString(15));
					shipList.add(oilCargo);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shipList;
	}

	@Override
	public void getShipPosition() {

		try {
			Statement myStmt = getConnection().createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from uvshipposition");
			System.out.println("Ship Name | Bearing | Current Coordinatets\n");
			while (myRs.next()) {
				System.out.println(myRs.getString("ShipName") + " | " + myRs.getString("Bearing") + " | "
						+ myRs.getString("CurrentCoordinates"));
			}
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateCurrentCoordinatesAndBearingAndNauticalMilage(int shipID, int shipLogID, String coordinates,
			String bearing, int nauticalMilage) {
		try {
			Statement myStmt = getConnection().createStatement();
			myStmt.executeQuery(
					"call uspUpdateCurrentCoordinatesAndBearingAndNauticalMilage(" + shipID + "," + shipLogID + ",'"
							+ coordinates
							+ "','" + bearing + "'," + nauticalMilage + ")");

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateDestinationStartCoordinates(int shipID, int shipLogID, String destination,
			String start) {

		try {
			Statement myStmt = getConnection().createStatement();
			myStmt.executeQuery(
					"call uspUpdateDestinationStartCoordinates(" + shipID + "," + shipLogID + ",'" + destination
							+ "', '" + start + "')");
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	@Override
	public void updateCurrentRoute(int shipID, int shipLogID, String currentRoute, String destinationCoordinates) {
		try {
			Statement myStmt = getConnection().createStatement();
			myStmt.executeQuery("call uspUpdateCurrentRoute(" + shipID + "," + shipLogID + ",'" + currentRoute + "','"
					+ destinationCoordinates + "')");
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
}
