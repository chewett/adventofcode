package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day1 {

    public void solve() {
        List<Integer> ints = ProblemLoader.loadProblemIntoIntegerList(2020, 1);

        Set<Integer> s = new HashSet<>(ints);

        for (int i : ints) {
            int diff = 2020 - i;
            if (s.contains(diff)) {
                System.out.println("Found: " + i + " and " + diff + " which multiply to " + (diff * i));
            }
        }

        for (int i : ints) {
            int initialDiff = 2020 - i;

            for (int j : ints) {
                int diff = initialDiff - j;
                if (s.contains(diff)) {
                    System.out.println("Found: " + i + " and " + j + " and " + diff + " which multiply to " + (diff * i * j));
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        Day1 d = new Day1();
        d.solve();
    }

}
