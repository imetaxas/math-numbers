package com.imetaxas.math.numbers.perfect;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/** 
 * JUnit test class for testing the PerfectNumbers class.
 *
 * @author imetaxas
 * @version 1.0
 * @since 1.0
 * @see <a href="http://junit.org/">JUnit tests</a>
*/
public final class PerfectNumbersTest {
	
	/**
	 * Main for running the tests from the console.
	 * @param args
	 */
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(PerfectNumbersTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println("Failure: " + failure.toString());
		}
		System.out.println("Was successful: " + result.wasSuccessful());
	}
  
	/** 
	* Tests the return true cases of the isPerfect method
	*
	*/
	@Test 
	public void isPerfectShouldReturnTrue() {
		assertTrue("6 should return true", PerfectNumbers.isPerfect(6));
		assertTrue("28 should return true", PerfectNumbers.isPerfect(28));
		assertTrue("496 should return true", PerfectNumbers.isPerfect(496));
		assertTrue("8128 should return true", PerfectNumbers.isPerfect(8128));
		assertTrue("33550336 should return true", PerfectNumbers.isPerfect(33550336));
	}
   
	/** 
	* Tests the return false cases of the isPerfect method
	*
	*/
	@Test 
	public void isPerfectShouldReturnFalse() {
		assertFalse(Integer.MAX_VALUE + " should return false", PerfectNumbers.isPerfect(Integer.MAX_VALUE));
		assertFalse("3 should return false", PerfectNumbers.isPerfect(3));
		assertFalse("1 should return false", PerfectNumbers.isPerfect(1));
		assertFalse("0 should return false", PerfectNumbers.isPerfect(0));
		assertFalse("-1 should return false", PerfectNumbers.isPerfect(-1));
		assertFalse("-28 should return false", PerfectNumbers.isPerfect(-28));
		assertFalse(Integer.MIN_VALUE + " should return false", PerfectNumbers.isPerfect(Integer.MIN_VALUE));
	}
	
	/** 
	* Tests the array equals cases of the findPerfectInRange method.
	*
	*/
	@Test 
	public void findPerfectInRangeTestAssertArrayEquals() {
		Integer[] expected = new Integer[]{6, 28, 496, 8128, 33550336};
		assertArrayEquals("Range(" + Integer.MIN_VALUE + ", " + Integer.MAX_VALUE + ") should return [6, 28, 496, 8128, 33550336]", expected, PerfectNumbers.findPerfectInRange(Integer.MIN_VALUE, Integer.MAX_VALUE).toArray());
		assertArrayEquals("Range(6, 33550336) should return []", expected, PerfectNumbers.findPerfectInRange(6, 33550336).toArray());
		
		expected = new Integer[0];
		assertArrayEquals("Range(" + Integer.MIN_VALUE + ", " + "5) should return []", expected, PerfectNumbers.findPerfectInRange(Integer.MIN_VALUE, 5).toArray());
		assertArrayEquals("Range(7, 27) should return []", expected, PerfectNumbers.findPerfectInRange(7, 27).toArray());
		assertArrayEquals("Range(29, 495) should return []", expected, PerfectNumbers.findPerfectInRange(29, 495).toArray());
		assertArrayEquals("Range(497, 8127) should return []", expected, PerfectNumbers.findPerfectInRange(497, 8127).toArray());
		assertArrayEquals("Range(8129, 33550335) should return []", expected, PerfectNumbers.findPerfectInRange(8129, 33550335).toArray());
		assertArrayEquals("Range(33550337, " + Integer.MAX_VALUE + ") should return []", expected, PerfectNumbers.findPerfectInRange(33550337, Integer.MAX_VALUE).toArray());
		
		expected = new Integer[]{6};
		assertArrayEquals("Range(6, 6) should return [6]", expected, PerfectNumbers.findPerfectInRange(6, 6).toArray());
		assertArrayEquals("Range(5, 7) should return [6]", expected, PerfectNumbers.findPerfectInRange(5, 7).toArray());
		
		expected = new Integer[]{28};
		assertArrayEquals("Range(28, 28) should return [28]", expected, PerfectNumbers.findPerfectInRange(28, 28).toArray());
		assertArrayEquals("Range(27, 29) should return [28]", expected, PerfectNumbers.findPerfectInRange(27, 29).toArray());
		
		expected = new Integer[]{496};
		assertArrayEquals("Range(496, 496) should return [496]", expected, PerfectNumbers.findPerfectInRange(496, 496).toArray());
		assertArrayEquals("Range(495, 497) should return [496]", expected, PerfectNumbers.findPerfectInRange(495, 497).toArray());
		
		expected = new Integer[]{8128};
		assertArrayEquals("Range(8128, 8128) should return [8128]", expected, PerfectNumbers.findPerfectInRange(8128, 8128).toArray());
		assertArrayEquals("Range(8127, 8129) should return [8128]", expected, PerfectNumbers.findPerfectInRange(8127, 8129).toArray());
		
		expected = new Integer[]{33550336};
		assertArrayEquals("Range(33550336, 33550336) should return [33550336]", expected, PerfectNumbers.findPerfectInRange(33550336, 33550336).toArray());
		assertArrayEquals("Range(33550335, 33550337) should return [33550336]", expected, PerfectNumbers.findPerfectInRange(33550335, 33550337).toArray());
		
	}
	
	/** 
	* Tests if an IllegalArgumentException is thrown when the starting value in the range is greater 
	* than the ending value when calling the findPerfectInRange method
	*
	*/
	@Test(expected=IllegalArgumentException.class)
	public void findPerfectInRangeThrowsIllegalArgumentExceptionIfStartGreaterThanEnd() {
		PerfectNumbers.findPerfectInRange(Integer.MAX_VALUE, Integer.MIN_VALUE);
	}
	
	/** 
	* Tests if an IllegalArgumentException is thrown when the ending value is less than one when calling the findPerfectInRange method
	*
	*/
	@Test(expected=IllegalArgumentException.class)
	public void findPerfectInRangeThrowsIllegalArgumentExceptionIfEndLessThanOne() {
		PerfectNumbers.findPerfectInRange(Integer.MIN_VALUE, 0);
	}
	
}