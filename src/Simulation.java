import java.util.ArrayList;

public class Simulation {

	private static IDatabaseService db = new DatabaseService();

	public void startSimulation() {

		// Reads all data from data base and creates the environment.
		ArrayList<Ship> shipList = db.readDatabase();
		String[][] seaGrid = Functions.createGrid();

		// GUI
		FleetGUI fleetGUI = new FleetGUI();
		fleetGUI.setSize(1000, 1000);
		fleetGUI.setVisible(true);

		boolean run = true;
		while(run) {
			db.getShipPosition();
			for (Ship ship : shipList) {

				// Moves all the ships
				ship.moveShip(seaGrid);

				// Updates database.
				db.updateCurrentCoordinatesAndBearingAndNauticalMilage(ship.getShipId(), ship.getShipLogId(),
						ship.getCurrentCoordinates(), ship.getBearing(), ship.getNauticMilage());
				db.updateCurrentRoute(ship.getShipId(), ship.getShipLogId(), ship.getCurrentRoute(),
						ship.getDestinationCoordinates());

				// Updates GUI colors.
				fleetGUI.restoreColor(ship.getPreviousCoordinates());
				fleetGUI.changeColor(ship.getShipId(), Functions.convertCoord(ship.getCurrentCoordinates()));
			}

			// Waits 1 second.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}
}

