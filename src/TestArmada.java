import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class TestArmada {

	@Test
	public void testConvertCoord() {

		int[] expectedResult = { 0, 9 };
		int[] actualResult = Functions.convertCoord("0,9");

		assertArrayEquals(expectedResult, actualResult);
	}

	@Test
	public void testConvertCoordToString() {

		int[] testArray = { 0, 9 };

		String expectedResult = "0,9";
		String actualResult = Functions.convertCoordToString(testArray);

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testRemoveFirstIndex() {

		String[] testArray = { "0", "1", "2" };

		String[] expectedResult = { "1", "2" };
		String[] actualResult = Functions.removeFirstIndex(testArray);

		assertArrayEquals(expectedResult, actualResult);
	}

	@Test
	public void testSplitString() {

		String[] expectedResult = { "1", "2" };
		String[] actualResult = Functions.splitString("1-2");

		assertArrayEquals(expectedResult, actualResult);
	}

	@Test
	public void testJoinSplittedArray() {

		String[] testArray = { "0", "1", "2" };

		String expectedResult = "0-1-2";
		String actualResult = Functions.joinSplittedArray(testArray);

		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void testDock() {

		Ship testShip = new Oil();
		testShip.setDocked(false);
		testShip.setCurrentSpeed(10);

		int expectedResult = 0;
		testShip.dock();

		assertTrue(testShip.isDocked());
		assertEquals(expectedResult, testShip.getCurrentSpeed());

	}

	@Test
	public void testUndock() {

		Ship testShip = new Oil();
		testShip.setDocked(true);
		testShip.setCurrentSpeed(0);
		testShip.setCruisingSpeed(20);

		int expectedResult = 20;
		testShip.undock();

		assertFalse(testShip.isDocked());
		assertEquals(expectedResult, testShip.getCurrentSpeed());

	}

	@Test
	public void testUpdateRoute() {

		Ship testShip = new Oil();
		testShip.setCurrentCoordinates("0,0");
		testShip.setDestinationCoordinates("49,49");
		testShip.setCurrentRoute("49,49-99,99-0,0");

		String expectedResult1 = "99,99";
		String expectedResult2 = "99,99-0,0";

		testShip.updateRoute();

		assertEquals(expectedResult1, testShip.getDestinationCoordinates());
		assertEquals(expectedResult2, testShip.getCurrentRoute());
	}

}
