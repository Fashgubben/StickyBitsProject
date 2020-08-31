import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class DatabaseService implements IDatabaseService {

	private static Connection conn;

	private static Connection getConnection() {

		// root
		String userName = "root";
		// password
		String userPassword = "password";
		// docker_mysql-db_1:3306
		String host = "docker_mysql-db_1";

		String path = "jdbc:mysql://" + host + "/fleet?serverTimezone=UTC";

		boolean transactionCompleted = false;
		do {
			try {
				conn = DriverManager.getConnection(path, userName, userPassword);
				transactionCompleted = true;

			} catch (SQLException sqlEx) {
				String sqlState = sqlEx.getSQLState();
				// Put right sqlState here:

				if ("08S01".equals(sqlState)) {
					System.out.println(sqlState);
					System.out.println(sqlEx);
				} else {
					System.out.println(sqlState);
					System.out.println(sqlEx);
				}

				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		}while (!transactionCompleted);
		return conn;
		
	}

	@Override
	public ArrayList<Ship> readDatabase() {
		// Reads from database and maps them to appropriate class, then returns an
		// ArrayList

		ArrayList<Ship> shipList = new ArrayList<Ship>();
		Statement myStmt = null;
		ResultSet myRs = null;

		try {

			myStmt = getConnection().createStatement();
			myRs = myStmt.executeQuery("select * from uvships");

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

			myRs.close();
			myRs = null;
			myStmt.close();
			myStmt = null;
			conn.close();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			if (myRs != null) {
				try {
					myRs.close();
				} catch (SQLException sqlEx) {
					// log this
				}
			}
			if (myStmt != null) {
				try {
					myStmt.close();
				} catch (SQLException sqlEx) {
					// log this
				}
			}
			if (conn != null) {
				try {
					//
					// If we got here, and conn is not null, the
					// transaction should be rolled back, as not
					// all work has been done
					try {
						conn.rollback();
					} finally {

						conn.close();
					}
				} catch (SQLException sqlEx) {
					//
					// If we got an exception here, something
					// pretty serious is going on, so we better
					// pass it up the stack, rather than just
					// logging it. . .
				}
			}
		}

		return shipList;
	}


	@Override
	public void updateCurrentCoordinatesAndBearingAndNauticalMilage(int shipID, int shipLogID, String coordinates,
			String bearing, int nauticalMilage) {
		// Updates current coordinate, bearing and nautical milage in the database

		try {
			Statement myStmt = getConnection().createStatement();
			myStmt.executeQuery("call uspUpdateCurrentCoordinatesAndBearingAndNauticalMilage(" + shipID + ","
					+ shipLogID + ",'" + coordinates + "','" + bearing + "'," + nauticalMilage + ")");

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateCurrentRoute(int shipID, int shipLogID, String currentRoute, String destinationCoordinates) {
		// Updates current route and destination coordinate in database

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

