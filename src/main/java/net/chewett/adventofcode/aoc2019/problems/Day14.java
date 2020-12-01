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

public class Day14 {

    public static void main(String[] args) {


        try {
            File file = new File("day_14_input.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            List<String> reactionStringsList = new ArrayList<>();
            String st;
            while ((st = br.readLine()) != null) {
                reactionStringsList.add(st);
            }

            String[] reactionStrings = new String[] {
                "10 ORE => 10 A",
                "1 ORE => 1 B",
                "7 A, 1 B => 1 C",
                "7 A, 1 C => 1 D",
                "7 A, 1 D => 1 E",
                "7 A, 1 E => 1 FUEL"
            };

            reactionStrings = new String[] {
                "171 ORE => 8 CNZTR",
                "7 ZLQW, 3 BMBT, 9 XCVML, 26 XMNCP, 1 WPTQ, 2 MZWV, 1 RJRHP => 4 PLWSL",
                "114 ORE => 4 BHXH",
                "14 VRPVC => 6 BMBT",
                "6 BHXH, 18 KTJDG, 12 WPTQ, 7 PLWSL, 31 FHTLT, 37 ZDVW => 1 FUEL",
                "6 WPTQ, 2 BMBT, 8 ZLQW, 18 KTJDG, 1 XMNCP, 6 MZWV, 1 RJRHP => 6 FHTLT",
                "15 XDBXC, 2 LTCX, 1 VRPVC => 6 ZLQW",
                "13 WPTQ, 10 LTCX, 3 RJRHP, 14 XMNCP, 2 MZWV, 1 ZLQW => 1 ZDVW",
                "5 BMBT => 4 WPTQ",
                "189 ORE => 9 KTJDG",
                "1 MZWV, 17 XDBXC, 3 XCVML => 2 XMNCP",
                "12 VRPVC, 27 CNZTR => 2 XDBXC",
                "15 KTJDG, 12 BHXH => 5 XCVML",
                "3 BHXH, 2 VRPVC => 7 MZWV",
                "121 ORE => 7 VRPVC",
                "7 XCVML => 6 RJRHP",
                "5 BHXH, 4 VRPVC => 5 LTCX"
            };

            List<Reaction> allReactions = new ArrayList<>();
            Map<ReactionChemical, Reaction> chemicalReactionMap = new HashMap<>();
            for(String s : reactionStrings) {
                Reaction r = new Reaction(s);
                allReactions.add(r);
                chemicalReactionMap.put(r.getProduces().getReactionChemical(), r);
            }

            ReactionChemical fuel = new ReactionChemical("FUEL");
            ReactionChemical ore = new ReactionChemical("ORE");

            long MAX_ORE = 1000000000L;
            Map<ReactionChemical, Long> chemicalPool = new HashMap<>();
            chemicalPool.put(fuel, 0L);
            chemicalPool.put(ore, MAX_ORE);
            while(chemicalPool.get(fuel) == 0) {
                ReactionChemical currentlyNeeds = fuel;
                boolean foundReactionToRun = false;
                Reaction reactionToRun = allReactions.get(0);
                while(!foundReactionToRun) {
                    Reaction r = chemicalReactionMap.get(currentlyNeeds);
                    boolean canRun = r.canReactionBeRun(chemicalPool);
                    if(canRun) {
                        foundReactionToRun = true;
                        reactionToRun = r;
                    }else{
                        currentlyNeeds = r.findMissingChemical(chemicalPool);
                    }
                }

                reactionToRun.runReaction(chemicalPool);
            }

            System.out.println("Ore used: " + (MAX_ORE - chemicalPool.get(ore)));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
