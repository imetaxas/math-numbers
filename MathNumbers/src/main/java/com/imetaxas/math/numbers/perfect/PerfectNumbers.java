package com.imetaxas.math.numbers.perfect;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/** 
 * Class for finding Perfect Numbers.
 * In number theory, a perfect number is a positive integer that is equal 
 * to the sum of its proper positive divisors, that is, the sum of its 
 * positive divisors excluding the number itself (also known as its aliquot sum). 
 * Equivalently, a perfect number is a number that is half the sum of all of 
 * its positive divisors (including itself).
 *
 * @author imetaxas
 * @see <a href="http://en.wikipedia.org/wiki/Perfect_number">Perfect Number</a>
*/
public final class PerfectNumbers {
	
	/** Removes constructor */
	private PerfectNumbers(){}
	
   /** 
	* Tests if the given number is a perfect number. 
	*
	* @param number The integer number to be tested as perfect
	* @return True if the number is perfect, false otherwise.
	*/
	public static boolean isPerfect(final int number){
		if(number < 1 || number % 2 == 1){
			return false;
		}else{
			int temp = 0;
			for(int i = 1; i <= number / 2; i++){
				if(number % i == 0){
					temp += i;
				}
			}
			if(temp == number){
				return true;
			} else {
				return false;
			}
		}
	}
	
	/** 
	* Returns all the perfect numbers in the given integer range. 
	*
	* @param beginNumber The starting integer number in the range
	* @param endNumber The ending integer number in the range
	* @return An array of integers with all the perfect numbers in the range.
	*/
	public static List<Integer> findPerfectInRange(final int beginNumber, final int endNumber) {
		if(beginNumber > endNumber || endNumber < 1){
			throw new IllegalArgumentException("Expected start < end && end > 0");
		}
		List<Integer> perfectNumbersList = new ArrayList<>();
		int i = 2;
		int value = 0;
		while(value < endNumber){
			double calculatedValue = Math.pow(2, i - 1) * (Math.pow(2, i) - 1);
            value = new Double(calculatedValue).intValue();
			if (value >= beginNumber && value <= endNumber && new BigInteger(String.valueOf(i)).isProbablePrime(i / 2) && isPerfect(value)){
				perfectNumbersList.add(value);
			}
			if(i > 2){
				i += 2;
			}else{
				i++;
			}
        }
		return perfectNumbersList;
	}

}