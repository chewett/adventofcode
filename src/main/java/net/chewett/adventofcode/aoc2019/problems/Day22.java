package net.chewett.adventofcode.aoc2019.problems;

import net.chewett.adventofcode.aoc2019.DeckShuffler;

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

        try {
            File file = new File(getClass().getResource("/aoc2019/2019_day_22_input.txt").getFile());
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;

            while ((st = br.readLine()) != null) {
                String[] splitLine = st.split(" ");
                String command = splitLine[0];
                if(command.equals("cut")) {
                    int num = Integer.parseInt(splitLine[splitLine.length - 1]);
                    DeckShuffler.cutCards(spaceDeck, num);
                }else if(command.equals("deal")) {
                    String commandTwo = splitLine[1];
                    if(commandTwo.equals("into")) {
                        DeckShuffler.dealIntoNewStack(spaceDeck);
                    }else if(commandTwo.equals("with")) {
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

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Day22 d = new Day22();
        d.solve();
    }

}
