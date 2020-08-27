
public class Oil extends Ship {

	public Oil() {

	}

	public Oil(int shipId, int shipLogId, int maxSpeed, int cruisingSpeed, String name, int currentSpeed,
			String bearing, String cargo, String currentCoordinates, String startCoordinates,
			String destinationCoordinates, String route, int nauticMilage, boolean docked, int maxCargoWeight,
			int cargoWeight, int[] previousCoordinates) {
		super(shipId, shipLogId, maxSpeed, cruisingSpeed, name, currentSpeed, bearing, cargo, currentCoordinates,
				startCoordinates, destinationCoordinates, route, nauticMilage, docked, maxCargoWeight, cargoWeight,
				previousCoordinates);
	}

	@Override
	public void unloadAndLoad() {
		
		if (this.getCargoWeight() == 0) {
			System.out.println("Loading ship");
			this.setCargoWeight(this.getMaxCargoWeight());
		} else {
			System.out.println("Unloading ship");
			this.setCargoWeight(0);
		}
	}
}
