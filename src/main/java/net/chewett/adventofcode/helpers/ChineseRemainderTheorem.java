package net.chewett.adventofcode.helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * This simple class allows you to perform the Chinese Remainder Theorem if given data in the appropiate form
 */
public class ChineseRemainderTheorem {

    /**
     * Note this only works when both numbers are co-prime (Assumed by the algorithm that uses this).
     * @param a
     * @param b
     * @return The modular multiplicative inverse of the two given numbers
     */
    private static long computeModularMultiplicativeInverse(long a, long b){
        //If b is one then there is nothing more to do as we know the value is 0
        if (b == 1) {
            return 0;
        }

        long b0 = b;
        long currentX = 0;
        long previousX = 1;
        while (a > 1) {
            long aFloordivB = a / b;
            long aModB = a % b;
            a = b;
            b = aModB;
            long smallerNewX = previousX - (aFloordivB * currentX);
            previousX = currentX;
            currentX = smallerNewX;
        }

        // Make previousX positive
        if (previousX < 0) {
            previousX += b0;
        }
        return previousX;
    }

    /**
     * Given a list of values and list of remainders this will find the number where X % value = remainder.
     *
     * @param values List of values to find the highest value for, these must be co-prime
     * @param remainders List of remainders which are found after running mod value on the intended solution
     * @return Value N where by all the given data matches N % value = remainder
     */
    public static long solve(List<Integer> values, List<Integer> remainders) {
        if(values.size() != remainders.size()) {
            throw new RuntimeException("Values array must be the same length as the remainder array");
        }

        //Calculate and store the product of all of the values
        long product = 1;
        for(int value : values) {
            product *= value;
        }

        long sum = 0;
        List<Long> partialProducts = new ArrayList<>();
        List<Long> inverse = new ArrayList<>();
        for(int i = 0; i < values.size(); i++) {
            partialProducts.add(product / values.get(i));
            inverse.add(ChineseRemainderTheorem.computeModularMultiplicativeInverse(partialProducts.get(i), values.get(i)));
            sum += (partialProducts.get(i) * inverse.get(i) * remainders.get(i));
        }

        //Now we can compute the final value
        return sum % product;
    }


}
