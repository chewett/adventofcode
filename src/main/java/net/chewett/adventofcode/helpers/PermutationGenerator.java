package net.chewett.adventofcode.helpers;

import java.util.ArrayList;
import java.util.List;

public class PermutationGenerator<T> {

    /**
     * Assume numbers are unique
     * @param numbers List of elements to permute
     * @return Returns a list of lists representing every element in every order
     */
    public List<List<T>> generatePermutations(List<T> numbers) {
        List<List<T>> allPermutations = new ArrayList<>();

        if(numbers.size() == 1) {
           allPermutations.add(numbers);
        }else{
            for(T i : numbers) {
                List<T> listWithoutThatNumber = new ArrayList<>();
                for(T numbersToAdd : numbers) {
                    if(i != numbersToAdd) {
                        listWithoutThatNumber.add(numbersToAdd);
                    }
                }

                List<List<T>> newPerms = this.generatePermutations(listWithoutThatNumber);
                for(List<T> perm : newPerms) {
                    perm.add(i);
                }
                allPermutations.addAll(newPerms);
            }
        }

        return allPermutations;
    }
}
