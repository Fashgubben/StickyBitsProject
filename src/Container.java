
public class Container extends Ship {

	public Container() {

	}

	public Container(int shipId, int shipLogId, int maxSpeed, int cruisingSpeed, String name, int currentSpeed,
			String bearing, String cargo, String currentCoordinates, String startCoordinates,
			String destinationCoordinates, String route, int nauticMilage, boolean docked, int maxCargoWeight,
			int cargoWeight, int[] previousCoordinates) {
		super(shipId, shipLogId, maxSpeed, cruisingSpeed, name, currentSpeed, bearing, cargo, currentCoordinates,
				startCoordinates, destinationCoordinates, route, nauticMilage, docked, maxCargoWeight, cargoWeight,
				previousCoordinates);
	}

	@Override
	public void unloadAndLoad() {
		System.out.println("Unloading containers...");
		this.setCargoWeight(0);
		System.out.println("Loading containers...");
		this.setCargoWeight(this.getMaxCargoWeight());
	}

}
