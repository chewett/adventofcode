package net.chewett.adventofcode2019;

import java.util.ArrayList;
import java.util.List;

public class PermutationGenerator {

    /**
     * Assume numbers are unique
     * @param numbers
     * @return
     */
    public static List<List<Integer>> generatePermutations(List<Integer> numbers) {
        List<List<Integer>> allPermutations = new ArrayList<>();

        if(numbers.size() == 1) {
           allPermutations.add(numbers);
        }else{
            for(int i : numbers) {
                List<Integer> listWithoutThatNumber = new ArrayList<>();
                for(int numbersToAdd : numbers) {
                    if(i != numbersToAdd) {
                        listWithoutThatNumber.add(numbersToAdd);
                    }
                }

                List<List<Integer>> newPerms = PermutationGenerator.generatePermutations(listWithoutThatNumber);
                for(List<Integer> perm : newPerms) {
                    perm.add(i);
                }
                allPermutations.addAll(newPerms);
            }
        }

        return allPermutations;
    };
}
