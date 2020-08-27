import java.util.ArrayList;

public class Simulation {

	private static IDatabaseService db = new DatabaseService();

	public void startSimulation() {

		// Reads all data from data base and creates the environment.
		db.getShipPosition();
		ArrayList<Ship> shipList = db.readDatabase();
		String[][] seaGrid = Functions.createGrid();

		// GUI - The sea grid.
		FleetGUI fleetGUI = new FleetGUI();
		fleetGUI.setSize(1000, 1000);
		fleetGUI.setVisible(true);

		// GUI - Text area that prints messages to user.

		boolean run = true;
		while(run) {
			for (Ship ship : shipList) {
				// if (ship.getRoute().length() > 0) {

				// Moves all the ships
				ship.moveShip(seaGrid);

				// Updates database.
				db.updateCurrentCoordinatesAndBearingAndNauticalMilage(ship.getShipId(), ship.getShipLogId(),
						ship.getCurrentCoordinates(), ship.getBearing(), ship.getNauticMilage());

				db.updateCurrentRoute(ship.getShipId(), ship.getShipLogId(), ship.getCurrentRoute(),
						ship.getDestinationCoordinates());

				db.getShipPosition();

				// Updates GUI colors.
				fleetGUI.restoreColor(ship.getPreviousCoordinates());
				fleetGUI.changeColor(ship.getShipId(), Functions.convertCoord(ship.getCurrentCoordinates()));

				// }
			}

			// Waits 1 second before .
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}
}

