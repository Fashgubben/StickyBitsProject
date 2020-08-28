import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


class TestArmada {

	@Test
	public void testConvertCoord() {

		final int[] expectedResult = { 0, 9 };
		final int[] actualResult = Functions.convertCoord("0,9");

		assertArrayEquals(expectedResult, actualResult);
	}

	@Test
	public void testConvertCoordToString() {

		final int[] testArray = { 0, 9 };

		final String expectedResult = "0,9";
		final String actualResult = Functions.convertCoordToString(testArray);

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testRemoveFirstIndex() {

		final String[] testArray = { "0", "1", "2" };

		final String[] expectedResult = { "1", "2" };
		final String[] actualResult = Functions.removeFirstIndex(testArray);

		assertArrayEquals(expectedResult, actualResult);
	}

	@Test
	public void testSplitString() {

		final String[] expectedResult = { "1", "2" };
		final String[] actualResult = Functions.splitString("1-2");

		assertArrayEquals(expectedResult, actualResult);
	}

	@Test
	public void testJoinSplittedArray() {

		final String[] testArray = { "0", "1", "2" };

		final String expectedResult = "0-1-2";
		final String actualResult = Functions.joinSplittedArray(testArray);

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testDock() {

		final Ship testShip = new Oil();
		testShip.setDocked(false);
		testShip.setCurrentSpeed(10);

		final int expectedResult = 0;
		testShip.dock();

		assertTrue(testShip.isDocked());
		assertEquals(expectedResult, testShip.getCurrentSpeed());

	}

	@Test
	public void testUndock() {

		final Ship testShip = new Oil();
		testShip.setDocked(true);
		testShip.setCurrentSpeed(0);
		testShip.setCruisingSpeed(20);

		final int expectedResult = 20;
		testShip.undock();

		assertFalse(testShip.isDocked());
		assertEquals(expectedResult, testShip.getCurrentSpeed());

	}

	@Test
	public void testUpdateRoute() {

		final Ship testShip = new Oil();
		testShip.setCurrentCoordinates("0,0");
		testShip.setDestinationCoordinates("49,49");
		testShip.setCurrentRoute("49,49-99,99-0,0");

		final String expectedResult1 = "99,99";
		final String expectedResult2 = "99,99-0,0";

		testShip.updateRoute();

		assertEquals(expectedResult1, testShip.getDestinationCoordinates());
		assertEquals(expectedResult2, testShip.getCurrentRoute());
	}

}