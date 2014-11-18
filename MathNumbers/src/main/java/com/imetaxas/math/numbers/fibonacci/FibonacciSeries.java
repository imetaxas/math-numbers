package com.imetaxas.math.numbers.fibonacci;

import java.math.BigInteger;

/**
 * Class for calculating Fibonacci numbers 1, 1, 2, 3, 5, 8, 13, 21, 34...
 * 
 * @author imetaxas
 * @see <a href="http://en.wikipedia.org/wiki/Fibonacci_number">Fibonacci Number</a>
 */
public final class FibonacciSeries {
	
	/**
	 * Threshold value for picking the fast doubling algorithm when is reached. 
	 * The value is an estimation based on metrics which come from the evaluateFibonacciMethods method.
	 */
	private static final long PICK_METHOD_THRESHOLD_VALUE = 440;

	/**
	 * Main method for evaluating the slow and fast Fibonacci algorithms
	 * 
	 */
	public static void main(String[] args) {
		evaluateFibonacciMethods(450);
		
		System.out.println(getFibonacciNumber(450));
		System.out.println(getFibonacciNumber(100));
	}
	
	/** Removes constructor */
	private FibonacciSeries(){}
	
	/**
	 * Returns the Nth value of the Fibonacci series using the fastest algorithm based on the input.
	 * <p> Uses the slow iterative algorithm for inputs between 0 and 440 or the fast doubling algorithm otherwise. Inputs range is an estimation based on metrics. </p>
	 * 
	 * <p> Examples: </p>
	 * <p> getFibonacciNumber(6) -> 8 </p>
	 * <p> getFibonacciNumber(8) -> 21 </p>
	 * <p> getFibonacciNumber(100) -> 354224848179261915075 </p>
	 * <p> getFibonacciNumber(450) -> 4953967011875066473162524925231604047727791871346061001150551747313593851366517214899257280600 </p>
	 * 
	 * @param N th index of the Fibonacci series
	 * @return the Nth value of the Fibonacci series using the fastest algorithm based on the input.
	 */
	public static BigInteger getFibonacciNumber(long N) {
		if(N <= 0){
			throw new IllegalArgumentException("Argument can NOT be a negative number!");
		}else if(N < PICK_METHOD_THRESHOLD_VALUE){
			return getFibonacciNumberSlowIterative(N);
		}else{
			return getFibonacciNumberFastDoubling(N);
		}
	}
	
	/**
	 * Returns the Nth value of the Fibonacci series using the Fast doubling method.
	 * 
	 * <p> Mathematical formula: </p>
	 * <p> F(2n) = F(n) * (2*F(n+1) - F(n)) </p>
	 * <p> F(2n+1) = F(n+1)^2 + F(n)^2 </p>
	 * <p> Complexity: O(log(n)) </p>
	 * 
	 * <p> Examples: </p>
	 * <p> getFibonacciNumberFastDoubling(6) -> 8 </p>
	 * <p> getFibonacciNumberFastDoubling(8) -> 21 </p>
	 * <p> getFibonacciNumberFastDoubling(100) -> 354224848179261915075 </p>
	 * <p> getFibonacciNumberFastDoubling(450) -> 4953967011875066473162524925231604047727791871346061001150551747313593851366517214899257280600 </p>
	 * 
	 * @param N th index of the Fibonacci series
	 * @return the Nth value of the Fibonacci series using the Fast doubling method
	 */
	public static BigInteger getFibonacciNumberFastDoubling(long N) {
		BigInteger number1 = BigInteger.ZERO;
		BigInteger number2 = BigInteger.ONE;
		
		for (int i = 31 - Integer.numberOfLeadingZeros((int)N); i >= 0; i--) {
			BigInteger fNumber1 = number1.multiply(number2.shiftLeft(1).subtract(number1));
			BigInteger fNumber2 = number1.multiply(number1).add(number2.multiply(number2));
			number1 = fNumber1;
			number2 = fNumber2;
			// Advance by one conditionally
			if (((N >>> i) & 1) != 0) {
				BigInteger c = number1.add(number2);
				number1 = number2;
				number2 = c;
			}
		}
		return number1;
	}
	
	/**
	 * Returns the Nth value of the Fibonacci series using the simple slow iterative method.
	 * 
	 * <p> Mathematical formula: </p>
	 * <p> F(n+2) = F(n+1) + F(n) </p>
	 * <p> Complexity: O(n) </p>
	 * 
	 * <p> Examples: </p>
	 * <p> getFibonacciNumberFastDoubling(6) -> 8 </p>
	 * <p> getFibonacciNumberFastDoubling(8) -> 21 </p>
	 * <p> getFibonacciNumberFastDoubling(100) -> 354224848179261915075 </p>
	 * <p> getFibonacciNumberFastDoubling(450) -> 4953967011875066473162524925231604047727791871346061001150551747313593851366517214899257280600 </p>
	 * 
	 * @param N th index of the Fibonacci series
	 * @return the Nth value of the Fibonacci series using the simple slow iterative method.
	 */
	public static BigInteger getFibonacciNumberSlowIterative(long N) {
		BigInteger number1 = BigInteger.ZERO;
		BigInteger number2 = BigInteger.ONE;
		for (int i = 0; i < N; i++) {
			BigInteger fNumber = number1.add(number2);
			number1 = number2;
			number2 = fNumber;
		}
		return number1;
	}
	
	/**
	 * Returns the Nth value of the Fibonacci series using simple slow recursion method.
	 * 
	 * <p> Mathematical formula: </p>
	 * <p> F(n+2) = F(n+1) + F(n) </p>
	 * <p> Complexity: O(f^n), f = the golden ration factor </p>
	 * 
	 * <p> Examples: </p>
	 * <p> getFibonacciNumberFastDoubling(6) -> 8 </p>
	 * <p> getFibonacciNumberFastDoubling(8) -> 21 </p>
	 * <p> getFibonacciNumberFastDoubling(100) -> 354224848179261915075 </p>
	 * 
	 * @param N th index of the Fibonacci series
	 * @return the Nth value of the Fibonacci series using the simple slow recursion method.
	 */
	public static BigInteger getFibonacciNumberSlowRecusion(long N){
        if(N == 1 || N == 2){
            return BigInteger.ONE;
        }
        return getFibonacciNumberSlowRecusion(N - 1).add(getFibonacciNumberSlowRecusion(N - 2));
    }
	
	/**
	 * Evaluates all three Fibonacci methods: the Fast doubling, the simple slow iterative and recursion methods.
	 * 
	 * <p> Can be used to measure results and decide which method is the best based on the input number range. </p>
	 * 
	 * @param N th index of the Fibonacci series
	 */
	private static void evaluateFibonacciMethods(long N){
		evaluateFibonacciMethods(N, false);
	}
	
	/**
	 * Evaluates all three Fibonacci methods: the Fast doubling, the simple slow iterative and recursion methods.
	 * 
	 * @param N th index of the Fibonacci series
	 * @param isSlowRecursionMethodEnabled
	 */
	private static void evaluateFibonacciMethods(long N, boolean isSlowRecursionMethodEnabled){
		long overallX = 0;
		long overallY = 0;
		long overallZ = 0;
		for(int n = 1; n <= N; n++){
			System.out.println("Input: " + n);
			long startTime = System.nanoTime();
			BigInteger x = getFibonacciNumberFastDoubling(n);
			long duration = System.nanoTime() - startTime;
			overallX += duration; 
			System.out.printf("Fast doubling: %d ns%n", duration);
			
			startTime = System.nanoTime();
			BigInteger z = getFibonacciNumberSlowIterative(n);
			duration = System.nanoTime() - startTime;
			overallZ += duration; 
			System.out.printf("Slow iter: %d ns%n", duration);
			
			if(isSlowRecursionMethodEnabled){
				startTime = System.nanoTime();
				BigInteger y = getFibonacciNumberSlowRecusion(n);
				duration = System.nanoTime() - startTime;
				overallY += duration; 
				System.out.printf("Slow rec: %d ns%n", duration);
			
				if (!x.equals(z) || !x.equals(y)){
					System.out.println("Wrong answer computed, x=" + x + ", y=" + y + ", z=" + z);
				}
			}else{
				if (!x.equals(z)){
					System.out.println("Wrong answer computed, x=" + x + ", z=" + z);
				}
			}
			
			if (z.bitLength() < 1000)
				System.out.printf("Answer: %d%n", z);
			else
				System.out.printf("Answer: (%d bits long)%n", z.bitLength());
			
			System.out.println();
		}
		
		System.out.println("Overall time");
		System.out.println("-------------");
		System.out.println("Fast doubling: " + overallX + " ns");
		System.out.println("Slow iter: " + overallZ + " ns");
		
		if(isSlowRecursionMethodEnabled){
			System.out.println("Slow rec: " + overallY + " ns");
		}
	}
}
