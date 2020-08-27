import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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

}
