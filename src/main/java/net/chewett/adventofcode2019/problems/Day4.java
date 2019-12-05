package net.chewett.adventofcode2019.problems;

import net.chewett.adventofcode2019.passwords.PasswordFinder;
import java.util.ArrayList;
import java.util.List;

public class Day4 {

    public static void main(String[] args) {
        int START_NUM = 153517;
        int END_NUM = 630395;

        List<Integer> allPossiblePasswords = new ArrayList<>();
        List<Integer> allPossibleComplexPasswords = new ArrayList<>();
        for(int i = START_NUM; i <= END_NUM; i++) {
            if(PasswordFinder.isValidPassword(i)) {
                allPossiblePasswords.add(i);
                if(PasswordFinder.isValidPasswordMoreComplex(i)) {
                    allPossibleComplexPasswords.add(i);
                }

            }
        }

        System.out.println("Number of possible passwords: " + allPossiblePasswords.size());
        System.out.println("Number of possible passwords with more complex requirements: " + allPossibleComplexPasswords.size());

    }

}
