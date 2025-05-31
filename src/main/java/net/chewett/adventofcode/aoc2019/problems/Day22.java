package net.chewett.adventofcode.aoc2019.problems;

import net.chewett.adventofcode.ProblemCreator;
import net.chewett.adventofcode.aoc2019.DeckShuffler;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day22 {


    public void solve() {
        List<Integer> spaceDeck = new ArrayList<>();
        DeckShuffler.initDeckWithNumberOfNumbers(spaceDeck, 10007);

        List<String> lines = ProblemLoader.loadProblemIntoStringArray(2019, 22);

        for(String line : lines) {
            String[] splitLine = line.split(" ");
            String command = splitLine[0];
            if (command.equals("cut")) {
                int num = Integer.parseInt(splitLine[splitLine.length - 1]);
                DeckShuffler.cutCards(spaceDeck, num);
            } else if (command.equals("deal")) {
                String commandTwo = splitLine[1];
                if (commandTwo.equals("into")) {
                    DeckShuffler.dealIntoNewStack(spaceDeck);
                } else if (commandTwo.equals("with")) {
                    int num = Integer.parseInt(splitLine[splitLine.length - 1]);
                    DeckShuffler.dealWithIncrement(spaceDeck, num);
                }
            }
        }

        for(int i = 0; i < 10007; i++) {
            if(spaceDeck.get(i) == 2019) {
                System.out.println("Found card 2019 at position " + i);
            }
        }

    }

    public static void main(String[] args) {
        Day22 d = new Day22();
        d.solve();
    }

}
