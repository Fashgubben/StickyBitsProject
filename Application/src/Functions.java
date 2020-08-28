import java.io.IOException;
import java.util.Arrays;

public class Functions {

	public static void main(String[] args) {

	}

	public static String[] removeFirstIndex(String[] route) {

		String[] stripedRoute = Arrays.copyOfRange(route, 1, route.length);

		return stripedRoute;
	}

	public static String[] splitString(String route) {
		String[] array = route.split("-");
		return array;
	}

	public static String joinSplittedArray(String[] route) {
		String routeString = String.join("-", route);
		return routeString;
	}

	public static void clearScreen() {
		// Clears the console screen (for esthetic reasons).

		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static String[][] createGrid() {
		// Creates a grid and sets the harbours.

		String[][] seaGrid = new String[100][100];

		for (int i = 0; i < seaGrid.length; i++) {
			for (int j = 0; j < seaGrid.length; j++) {
				seaGrid[i][j] = ".";
			}
		}

		seaGrid[0][0] = "A";
		seaGrid[0][99] = "B";
		seaGrid[99][0] = "C";
		seaGrid[99][99] = "D";
		seaGrid[49][49] = "E";

		return seaGrid;
	}

	public static int[] convertCoord(String stringCoordinates) {
		// Converts string coordinates to array list with integers.

		int[] coord = new int[2];
		String[] stringCoord = new String[2];

		stringCoord = stringCoordinates.split(",");

		coord[0] = Integer.parseInt(stringCoord[0]);
		coord[1] = Integer.parseInt(stringCoord[1]);

		return coord;

	}

	public static String convertCoordToString(int[] intCoord) {
		// Converts int array list values to a string.
		String stringCoord = "";

		stringCoord = (intCoord[0] + "," + intCoord[1]);

		return stringCoord;
	}

}
