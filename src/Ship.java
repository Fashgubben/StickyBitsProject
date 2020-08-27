public abstract class Ship {


	private int shipId;
	private int shipLogId;
	private String name;
	private int maxSpeed;
	private int cruisingSpeed;
	private int currentSpeed;
	private String bearing;
	private String cargo;
	private String currentCoordinates;
	private String startCoordinates;
	private String destinationCoordinates;
	private String route;
	private int nauticMilage;
	private boolean docked;
	private int maxCargoWeight;
	private int cargoWeight;
	private int[] previousCoordinates;
	private String currentRoute;

	// Empty constructor for jUnit class.
	public Ship() {

	}

	public Ship(int shipId, int shipLogId, int maxSpeed, int cruisingSpeed, String name, int currentSpeed,
			String bearing, String cargo, String currentCoordinates, String startCoordinates,
			String destinationCoordinates, String route, int nauticMilage, boolean docked, int maxCargoWeight,
			int cargoWeight, int[] previousCoordinates) {
		super();
		this.shipId = shipId;
		this.shipLogId = shipLogId;
		this.maxSpeed = maxSpeed;
		this.cruisingSpeed = cruisingSpeed;
		this.name = name;
		this.currentSpeed = currentSpeed;
		this.bearing = bearing;
		this.cargo = cargo;
		this.currentCoordinates = currentCoordinates;
		this.startCoordinates = startCoordinates;
		this.destinationCoordinates = destinationCoordinates;
		this.route = route;
		this.nauticMilage = nauticMilage;
		this.docked = docked;
		this.setMaxCargoWeight(maxCargoWeight);
		this.cargoWeight = cargoWeight;
		this.previousCoordinates = previousCoordinates;
	}


	public String getCurrentRoute() {
		return currentRoute;
	}

	public void setCurrentRoute(String currentRoute) {
		this.currentRoute = currentRoute;
	}

	public int[] getPreviousCoordinates() {
		return previousCoordinates;
	}

	public void setPreviousCoordinates(int[] previousCoordinates) {
		this.previousCoordinates = previousCoordinates;
	}

	public int getShipId() {
		return shipId;
	}

	public void setShipId(int shipId) {
		this.shipId = shipId;
	}

	public int getShipLogId() {
		return shipLogId;
	}

	public void setShipLogId(int shipLogId) {
		this.shipLogId = shipLogId;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public int getCruisingSpeed() {
		return cruisingSpeed;
	}

	public void setCruisingSpeed(int cruisingSpeed) {
		this.cruisingSpeed = cruisingSpeed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(int currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	public String getBearing() {
		return bearing;
	}

	public void setBearing(String bearing) {
		this.bearing = bearing;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCurrentCoordinates() {
		return currentCoordinates;
	}

	public void setCurrentCoordinates(String currentCoordinates) {
		this.currentCoordinates = currentCoordinates;
	}

	public String getStartCoordinates() {
		return startCoordinates;
	}

	public void setStartCoordinates(String startCoordinates) {
		this.startCoordinates = startCoordinates;
	}

	public String getDestinationCoordinates() {
		return destinationCoordinates;
	}

	public void setDestinationCoordinates(String destinationCoordinates) {
		this.destinationCoordinates = destinationCoordinates;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public int getNauticMilage() {
		return nauticMilage;
	}

	public void setNauticMilage(int nauticMilage) {
		this.nauticMilage = nauticMilage;
	}

	public int getMaxCargoWeight() {
		return maxCargoWeight;
	}

	public void setMaxCargoWeight(int maxCargoWeight) {
		this.maxCargoWeight = maxCargoWeight;
	}

	public boolean isDocked() {
		return docked;
	}

	public void setDocked(boolean docked) {
		this.docked = docked;
	}

	public int getCargoWeight() {
		return cargoWeight;
	}

	public void setCargoWeight(int cargoWeight) {
		this.cargoWeight = cargoWeight;
	}


	public void dock() {
		// Stops and docks the ship.

		System.out.println(this.getName() + " is docking at " + this.getCurrentCoordinates());
		this.setCurrentSpeed(0);
		this.setDocked(true);
	}

	public void undock() {
		// Starts and undocks ship.

		System.out.println(this.getName() + " is undocking..." + " at " + this.getCurrentCoordinates());
		this.setDocked(false);
		this.setCurrentSpeed(this.getCruisingSpeed());
	}


	public void updateRoute() {
		// Points the ship to the next coordinate in the route.

		String[] route = Functions.splitString(this.getCurrentRoute());

		try {
			this.setStartCoordinates(this.getCurrentCoordinates());
			
			route = Functions.removeFirstIndex(route);

			this.setDestinationCoordinates(route[0]);
			this.setCurrentRoute(Functions.joinSplittedArray(route));
			
		} catch (ArrayIndexOutOfBoundsException e) {
			// Gives ship a new route if route list is empty.

			this.setCurrentRoute(this.getRoute());
			route = Functions.splitString(this.getCurrentRoute());
			this.setDestinationCoordinates(route[0]);
		}
	}

	public void moveShip(String[][] seaGrid) {
		// The ship detects which direction it has to go based on destination
		// coordinate, the moves.

		int movingDistance = (this.getCurrentSpeed() / 10);
		this.setPreviousCoordinates(Functions.convertCoord(this.getCurrentCoordinates()));
		int[] currentCoordinates = Functions.convertCoord(this.getCurrentCoordinates());
		int[] destinationCoordinates = Functions.convertCoord(this.getDestinationCoordinates());

		// Bearing: S
		if (currentCoordinates[0] < destinationCoordinates[0] && currentCoordinates[1] == destinationCoordinates[1]) {
			this.goSouth(movingDistance, currentCoordinates, destinationCoordinates);

		} // Bearing: N
		else if (currentCoordinates[0] > destinationCoordinates[0]
				&& currentCoordinates[1] == destinationCoordinates[1]) {
			this.goNorth(movingDistance, currentCoordinates, destinationCoordinates);

		} // Bearing: E
		else if (currentCoordinates[0] == destinationCoordinates[0]
				&& currentCoordinates[1] < destinationCoordinates[1]) {
			this.goEast(movingDistance, currentCoordinates, destinationCoordinates);

		} // Bearing: W
		else if (currentCoordinates[0] == destinationCoordinates[0]
				&& currentCoordinates[1] > destinationCoordinates[1]) {
			this.goWest(movingDistance, currentCoordinates, destinationCoordinates);

		} // Bearing: SE
		else if (currentCoordinates[0] < destinationCoordinates[0]
				&& currentCoordinates[1] < destinationCoordinates[1]) {
			this.goSouthEast(movingDistance, currentCoordinates, destinationCoordinates);

		} // Bearing: NW
		else if (currentCoordinates[0] > destinationCoordinates[0]
				&& currentCoordinates[1] > destinationCoordinates[1]) {
			this.goNorthWest(movingDistance, currentCoordinates, destinationCoordinates);

		} // Bearing: SW
		else if (currentCoordinates[0] < destinationCoordinates[0]
				&& currentCoordinates[1] > destinationCoordinates[1]) {
			this.goSouthWest(movingDistance, currentCoordinates, destinationCoordinates);

		} // Bearing: NE
		else if (currentCoordinates[0] > destinationCoordinates[0]
				&& currentCoordinates[1] < destinationCoordinates[1]) {
			this.goNorthEast(movingDistance, currentCoordinates, destinationCoordinates);
		} else {
			System.out.println(this.getShipId() + " - " + this.getName() + " has finished it's route.");
		}

		// Updates the ships position on the grid.
		int[] coord = Functions.convertCoord(this.getCurrentCoordinates());
		seaGrid[coord[0]][coord[1]] = Integer.toString(this.getShipId());
	}

	public void goSouth(int movingDistance, int[] currentCoordinates,
			int[] destinationCoordinates) {
		

		for (int i = 0; i < movingDistance; i++) {
			
			// Prevents the ship from going outside the grid.
			if (currentCoordinates[0] < 99) {
				currentCoordinates[0] = (currentCoordinates[0]) + 1;
				this.setNauticMilage(this.getNauticMilage() + 1);
				this.setCurrentCoordinates(Functions.convertCoordToString(currentCoordinates));
				this.setBearing("S");
			} else
				break;

			// When ship reaches it's destination.
			if ((currentCoordinates[0] == destinationCoordinates[0])) {

				this.setCurrentCoordinates(Functions.convertCoordToString(currentCoordinates));
				this.dock();
				this.unloadAndLoad();
				this.updateRoute();
				this.undock();
				break;
			}
		}
	}

	public void goNorth(int movingDistance, int[] currentCoordinates,
			int[] destinationCoordinates) {


		for (int i = 0; i < movingDistance; i++) {

			if (currentCoordinates[0] > 0) {
				currentCoordinates[0] = currentCoordinates[0] - 1;
				this.setNauticMilage(this.getNauticMilage() + 1);
				this.setCurrentCoordinates(Functions.convertCoordToString(currentCoordinates));
				this.setBearing("N");
			} else
				break;

			// When ship reaches it's destination.
			if ((currentCoordinates[0] == destinationCoordinates[0])) {

				this.setCurrentCoordinates(Functions.convertCoordToString(currentCoordinates));
				this.dock();
				this.unloadAndLoad();
				this.updateRoute();
				this.undock();
				break;
			}
		}
	}

	public void goEast(int movingDistance, int[] currentCoordinates,

			int[] destinationCoordinates) {

		for (int i = 0; i < movingDistance; i++) {

			// Prevents the ship from going outside the grid.
			if (currentCoordinates[1] < 99) {
			currentCoordinates[1] = currentCoordinates[1] + 1;
				this.setNauticMilage(this.getNauticMilage() + 1);
				this.setCurrentCoordinates(Functions.convertCoordToString(currentCoordinates));
				this.setBearing("E");
			} else
				break;

			// When ship reaches it's destination.
			if ((currentCoordinates[1] == destinationCoordinates[1])) {

				this.setCurrentCoordinates(Functions.convertCoordToString(currentCoordinates));
				this.dock();
				this.unloadAndLoad();
				this.updateRoute();
				this.undock();
				break;
			}
		}
	}

	public void goWest(int movingDistance,int[] currentCoordinates,
			int[] destinationCoordinates) {

		for (int i = 0; i < movingDistance; i++) {

			// Prevents the ship from going outside the grid.
			if (currentCoordinates[1] > 0) {
			currentCoordinates[1] = currentCoordinates[1] - 1;
				this.setNauticMilage(this.getNauticMilage() + 1);
				this.setCurrentCoordinates(Functions.convertCoordToString(currentCoordinates));
				this.setBearing("W");
			}

			// When ship reaches it's destination.
			if ((currentCoordinates[1] == destinationCoordinates[1])) {

				this.setCurrentCoordinates(Functions.convertCoordToString(currentCoordinates));
				this.dock();
				this.unloadAndLoad();
				this.updateRoute();
				this.undock();
				break;
			}
		}

	}
	
	public void goSouthEast(int movingDistance, int[] currentCoordinates, int[] destinationCoordinates) {

		for (int i = 0; i < movingDistance; i++) {

			// Prevents the ship from going outside the grid.
			if (currentCoordinates[0] < 99 && currentCoordinates[1] < 99) {
				currentCoordinates[0] = currentCoordinates[0] + 1;
				currentCoordinates[1] = currentCoordinates[1] + 1;
				this.setNauticMilage(this.getNauticMilage() + 1);
				this.setCurrentCoordinates(Functions.convertCoordToString(currentCoordinates));
				this.setBearing("SE");
			} else
				break;

			// When ship reaches it's destination.
			if ((currentCoordinates[1] == destinationCoordinates[1])
					&& (currentCoordinates[0] == destinationCoordinates[0])) {

				this.setCurrentCoordinates(Functions.convertCoordToString(currentCoordinates));
				this.dock();
				this.unloadAndLoad();
				this.updateRoute();
				this.undock();
				break;
			}
		}
	}
	
	
	public void goNorthWest(int movingDistance, int[] currentCoordinates, int[] destinationCoordinates) {

		for (int i = 0; i < movingDistance; i++) {

			// Prevents the ship from going outside the grid.
			if (currentCoordinates[0] > 0 && currentCoordinates[1] > 0) {
				currentCoordinates[0] = currentCoordinates[0] - 1;
				currentCoordinates[1] = currentCoordinates[1] - 1;
				this.setNauticMilage(this.getNauticMilage() + 1);
				this.setCurrentCoordinates(Functions.convertCoordToString(currentCoordinates));
				this.setBearing("NW");
			} else
				break;

			// When ship reaches it's destination.
			if ((currentCoordinates[1] == destinationCoordinates[1])
					&& (currentCoordinates[0] == destinationCoordinates[0])) {

				this.setCurrentCoordinates(Functions.convertCoordToString(currentCoordinates));
				this.dock();
				this.unloadAndLoad();
				this.updateRoute();
				this.undock();
				break;
			}
		}
	}
		
	public void goNorthEast(int movingDistance, int [] currentCoordinates,
			int[] destinationCoordinates) {

		for (int i = 0; i < movingDistance; i++) {

			// Prevents the ship from going outside the grid.
			if (currentCoordinates[0] > 0 && currentCoordinates[1] < 99) {
				currentCoordinates[0] = currentCoordinates[0] - 1;
				currentCoordinates[1] = currentCoordinates[1] + 1;
				this.setNauticMilage(this.getNauticMilage() + 1);
				this.setCurrentCoordinates(Functions.convertCoordToString(currentCoordinates));
				this.setBearing("NE");
			} else
				break;

			// When ship reaches it's destination.
			if ((currentCoordinates[1] == destinationCoordinates[1])
					&& (currentCoordinates[0] == destinationCoordinates[0])) {

				this.setCurrentCoordinates(Functions.convertCoordToString(currentCoordinates));
				this.dock();
				this.unloadAndLoad();
				this.updateRoute();
				this.undock();
				break;
			}
		}
}

	public void goSouthWest(int movingDistance, int[] currentCoordinates, int[] destinationCoordinates) {

		for (int i = 0; i < movingDistance; i++) {

			// Prevents the ship from going outside the grid.
			if (currentCoordinates[0] < 99 && currentCoordinates[1] > 0) {
				currentCoordinates[0] = currentCoordinates[0] + 1;
				currentCoordinates[1] = currentCoordinates[1] - 1;
				this.setNauticMilage(this.getNauticMilage() + 1);
				this.setCurrentCoordinates(Functions.convertCoordToString(currentCoordinates));
				this.setBearing("SW");
			} else
				break;

			// When ship reaches it's destination.
			if ((currentCoordinates[1] == destinationCoordinates[1])
					&& (currentCoordinates[0] == destinationCoordinates[0])) {

				this.setCurrentCoordinates(Functions.convertCoordToString(currentCoordinates));
				this.dock();
				this.unloadAndLoad();
				this.updateRoute();
				this.undock();
				break;
			}
		}
	}
	
	public abstract void unloadAndLoad();


}
