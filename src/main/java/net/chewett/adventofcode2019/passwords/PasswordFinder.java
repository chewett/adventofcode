package net.chewett.adventofcode2019.passwords;

import java.util.ArrayList;
import java.util.List;

public class PasswordFinder {

    public static boolean isValidPassword(int num) {
        char[] pieces = String.valueOf(num).toCharArray();
        int curNum = 0;
        boolean doubleFound = false;
        for(int i = 0; i < pieces.length; i++) {
            int numAtPoint = Integer.valueOf(pieces[i]);
            if(numAtPoint == curNum) {
                doubleFound = true;
            }

            if(numAtPoint < curNum) {
                return false;
            }else{
                curNum = numAtPoint;
            }
        }

        return doubleFound;
    }

    public static boolean isValidPasswordMoreComplex(int num) {
        boolean doesItMatchBasic = isValidPassword(num);
        if(!doesItMatchBasic) {
            return false;
        }

        char[] pieces = String.valueOf(num).toCharArray();
        List<Integer> numberOfConsequtive = new ArrayList<>();

        int prevNum = 0;
        int countOfSameNum = 0;
        for(int i = 0; i < pieces.length; i++) {
            int numAtPoint = Integer.valueOf(pieces[i]);
            if(numAtPoint != prevNum) {
                if(countOfSameNum != 0) {
                    numberOfConsequtive.add(countOfSameNum);
                }
                countOfSameNum = 1;
                prevNum = numAtPoint;
            }else{
                countOfSameNum++;
            }
        }
        numberOfConsequtive.add(countOfSameNum);

        return (numberOfConsequtive.indexOf(2) != -1);
    }


}
