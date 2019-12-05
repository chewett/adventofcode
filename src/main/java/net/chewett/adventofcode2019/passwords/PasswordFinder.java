package net.chewett.adventofcode2019.passwords;

import java.util.ArrayList;
import java.util.List;

public class PasswordFinder {

    /**
     * Given a six digit number this will determine if it matches the Elves password requirements
     * Rules:
     * - It is a six-digit number.
     * - The value is within the range given in your puzzle input.
     * - Two adjacent digits are the same (like 22 in 122345).
     * - Going from left to right, the digits never decrease; they only ever increase or stay the same (like 111123 or 135679).
     *
     * @param num Number to check to see if its a valid password
     * @return true if the password is valid, otherwise false
     */
    public static boolean isValidPassword(int num) {
        char[] pieces = String.valueOf(num).toCharArray();
        int prevNum = pieces[0];
        boolean doubleFound = false;
        for(int i = 1; i < pieces.length; i++) {
            int currentNumber = pieces[i];
            //If prev num is same as current num, then there is a duplicate and its good!
            if(currentNumber == prevNum) {
                doubleFound = true;
            }

            //If the current number is lower than the previous number, its not a valid password
            if(currentNumber < prevNum) {
                return false;
            }else{
                prevNum = currentNumber;
            }
        }

        //If it doesnt descend, then its valid if it found a double.
        return doubleFound;
    }

    /**
     * Given a six digit number this will determine if it matches the Elves more advanced password critera.
     * Rules:
     * - It is a six-digit number.
     * - The value is within the range given in your puzzle input.
     * - Two adjacent digits are the same (like 22 in 122345).
     * - Going from left to right, the digits never decrease; they only ever increase or stay the same (like 111123 or 135679).
     * - The two adjacent matching digits are not part of a larger group of matching digits.
     *
     * The final rule is the new one compared to the above function isValidPassword
     *
     * @param num Number to check to see if its a valid password
     * @return true if the password is valid, otherwise false
     */
    public static boolean isValidPasswordMoreComplex(int num) {
        //First check to make sure it matches the previous requirements, if it doesnt it wont match the stricter ones.
        boolean doesItMatchBasic = isValidPassword(num);
        if(!doesItMatchBasic) {
            return false;
        }

        char[] pieces = String.valueOf(num).toCharArray();
        List<Integer> numberOfConsecutive = new ArrayList<>();

        int prevNum = pieces[0];
        int countOfSameNum = 1;
        for(int i = 1; i < pieces.length; i++) {
            //Keep track of the number of consecutive characters
            int currentNumber = pieces[i];
            if(currentNumber != prevNum) {
                if(countOfSameNum != 0) {
                    numberOfConsecutive.add(countOfSameNum);
                }
                countOfSameNum = 1;
                prevNum = currentNumber;
            }else{
                countOfSameNum++;
            }
        }
        numberOfConsecutive.add(countOfSameNum);

        //If there was two (and only two) consecutive numbers then it matches the requirements.
        //If there isnt two, or are more/less than two then it won't match.
        return (numberOfConsecutive.indexOf(2) != -1);
    }


}
