package net.chewett.adventofcode.aoc2019.problems;

import net.chewett.adventofcode.aoc2019.Reaction;
import net.chewett.adventofcode.aoc2019.ReactionChemical;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14PartTwo {

    public void solve() {
        try {
            File file = new File(getClass().getResource("/aoc2019/2019_day_14_input.txt").getFile());
            BufferedReader br = new BufferedReader(new FileReader(file));

            List<String> reactionStringsList = new ArrayList<>();
            String st;
            while ((st = br.readLine()) != null) {
                reactionStringsList.add(st);
            }

            List<Reaction> allReactions = new ArrayList<>();
            Map<ReactionChemical, Reaction> chemicalReactionMap = new HashMap<>();
            for(String s : reactionStringsList) {
                Reaction r = new Reaction(s);
                allReactions.add(r);
                chemicalReactionMap.put(r.getProduces().getReactionChemical(), r);
            }

            ReactionChemical fuel = new ReactionChemical("FUEL");
            ReactionChemical ore = new ReactionChemical("ORE");

            long MAX_ORE = 1000000000000L;
            Map<ReactionChemical, Long> chemicalPool = new HashMap<>();
            chemicalPool.put(fuel, 0L);
            chemicalPool.put(ore, MAX_ORE);

            boolean cantRunReaction = false;
            while(!cantRunReaction) {
                int timesToRun;
                //Cheat to speed up calculations, still relatively slow.
                long currentFuel = chemicalPool.get(fuel);
                if(currentFuel < 3000000) {
                    timesToRun = 100000;
                }else if(currentFuel < 3600000) {
                    timesToRun = 10000;
                }else if(currentFuel < 3680000) {
                    timesToRun = 1000;
                }else if(currentFuel < 3685000) {
                    timesToRun = 10;
                }else{
                    timesToRun = 1;
                }

                ReactionChemical currentlyNeeds = fuel;
                boolean foundReactionToRun = false;
                Reaction reactionToRun = allReactions.get(0);
                while(!foundReactionToRun && !cantRunReaction) {
                    Reaction r = chemicalReactionMap.get(currentlyNeeds);
                    boolean canRun = r.canReactionBeRun(chemicalPool, timesToRun);
                    if(canRun) {
                        foundReactionToRun = true;
                        reactionToRun = r;
                    }else{
                        currentlyNeeds = r.findMissingChemical(chemicalPool, timesToRun);
                        if(currentlyNeeds.getName().equals("ORE")) {
                            cantRunReaction = true;
                        }
                    }
                }
                if(!cantRunReaction) {
                    reactionToRun.runReaction(chemicalPool, timesToRun);
                }

                String currentStockpile = "Fuel: " + chemicalPool.get(fuel) + " Ore: " + chemicalPool.get(ore) + " ";
                for(Map.Entry<ReactionChemical, Long> e : chemicalPool.entrySet()) {
                    currentStockpile += e.getKey().getName() + ": " + e.getValue() + ", ";
                }
                System.out.println(currentStockpile);

            }

            System.out.println("Fuel produced: " + chemicalPool.get(fuel));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Day14PartTwo d = new Day14PartTwo();
        d.solve();
    }


}
