package net.chewett.adventofcode.aoc2020.problems;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day1 {

    public void solve() {
        try {
            File file = new File(getClass().getResource("/aoc2020/2020_day_1_input.txt").getFile());
            BufferedReader br = new BufferedReader(new FileReader(file));

            List<Integer> ints = new ArrayList<>();

            String st;
            while ((st = br.readLine()) != null) {
                int val = Integer.parseInt(st);
                ints.add(val);
            }

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
        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        Day1 d = new Day1();
        d.solve();
    }

}
