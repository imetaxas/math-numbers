package com.imetaxas.math.numbers.fibonacci;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/** 
 * JUnit test class for testing the FibonacciSeries class.
 *
 * @author imetaxas
 * @version 1.0
 * @since 1.0
 * @see <a href="http://junit.org/">JUnit tests</a>
*/
@RunWith(value = Parameterized.class)
public final class FibonacciSeriesTest {
	
	/**
	 * Main for running the tests from the console.
	 * @param args
	 */
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(FibonacciSeriesTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println("Failure: " + failure.toString());
		}
		System.out.println("Was successful: " + result.wasSuccessful());
	}
	
	private int fibNumber;
	private int fibExpected;
	
	/**
	 * Constructor for this parameterized class
	 * 
	 * @param fibNumber
	 * @param fibExpected
	 */
	public FibonacciSeriesTest(int fibNumber, int fibExpected){
		this.fibNumber = fibNumber;
		this.fibExpected = fibExpected;
	}
	
	/**
	 * Creates a parameters set for testing the methods.
	 * 
	 * @return parameters set for testing the methods.
	 */
	@Parameters(name = "Fibonacci {0}th value is: {1}")
	public static Iterable<Object[]> parameters() {
		return Arrays.asList(new Object[][] { 
				{ 1, 1 }, 
				{ 2, 1 }, 
				{ 3, 2 }, 
				{ 4, 3 },
				{ 5, 5 },
				{ 6, 8 },
				{ 7, 13 },
				{ 8, 21 },
				{ 9, 34 },
				{ 10, 55 },
				{ 11, 89 },
				{ 12, 144 },
				{ 13, 233 },
				{ 14, 377 },
				{ 15, 610 },
				{ 16, 987 }
		});
	}
	
	/** 
	* Tests whether the Fibonacci numbers in the range [1-16] are correct when calling the getFibonacciNumber method
	*
	* @since 1.0
	*/
	@Test
    public void getFibonacciNumberAssertEquals() {
		assertEquals(fibExpected, FibonacciSeries.getFibonacciNumber(fibNumber).intValue());
	}
	
	/** 
	* Tests if an IllegalArgumentException is thrown when the value is less than one when calling the getFibonacciNumber method
	*
	* @since 1.0
	*/
	@Test(expected=IllegalArgumentException.class)
    public void getValueBelowOneThrowsIllegalArgumentException() {
		FibonacciSeries.getFibonacciNumber(-fibNumber).intValue();
	}
}
