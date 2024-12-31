package net.chewett.adventofcode.aoc2019.problems;

import net.chewett.adventofcode.aoc2019.Orbiter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Awesome problem taken from: https://adventofcode.com/2019/day/6
 * Go have a try yourself!
 * ----------------------------------------------------------
 *
 * --- Day 6: Universal Orbit Map ---
 * You've landed at the Universal Orbit Map facility on Mercury. Because navigation in space often involves
 * transferring between orbits, the orbit maps here are useful for finding efficient routes between, for example,
 * you and Santa. You download a map of the local orbits (your puzzle input).
 *
 * Except for the universal Center of Mass (COM), every object in space is in orbit around exactly one other object.
 * An orbit looks roughly like this:
 *
 *                   \
 *                    \
 *                     |
 *                     |
 * AAA--> o            o <--BBB
 *                     |
 *                     |
 *                    /
 *                   /
 * In this diagram, the object BBB is in orbit around AAA. The path that BBB takes around AAA (drawn with lines) is
 * only partly shown. In the map data, this orbital relationship is written AAA)BBB, which means "BBB is in orbit
 * around AAA".
 *
 * Before you use your map data to plot a course, you need to make sure it wasn't corrupted during the download. To
 * verify maps, the Universal Orbit Map facility uses orbit count checksums - the total number of direct orbits (like
 * the one shown above) and indirect orbits.
 *
 * Whenever A orbits B and B orbits C, then A indirectly orbits C. This chain can be any number of objects long:
 * if A orbits B, B orbits C, and C orbits D, then A indirectly orbits D.
 *
 * For example, suppose you have the following map:
 *
 * COM)B
 * B)C
 * C)D
 * D)E
 * E)F
 * B)G
 * G)H
 * D)I
 * E)J
 * J)K
 * K)L
 * Visually, the above map of orbits looks like this:
 *
 *         G - H       J - K - L
 *        /           /
 * COM - B - C - D - E - F
 *                \
 *                 I
 * In this visual representation, when two objects are connected by a line, the one on the right directly orbits the
 * one on the left.
 *
 * Here, we can count the total number of orbits as follows:
 *
 * D directly orbits C and indirectly orbits B and COM, a total of 3 orbits.
 * L directly orbits K and indirectly orbits J, E, D, C, B, and COM, a total of 7 orbits.
 * COM orbits nothing.
 * The total number of direct and indirect orbits in this example is 42.
 *
 * What is the total number of direct and indirect orbits in your map data?
 *
 * --- Part Two ---
 * Now, you just need to figure out how many orbital transfers you (YOU) need to take to get to Santa (SAN).
 *
 * You start at the object YOU are orbiting; your destination is the object SAN is orbiting. An orbital transfer lets
 * you move from any object to an object orbiting or orbited by that object.
 *
 * For example, suppose you have the following map:
 *
 * COM)B
 * B)C
 * C)D
 * D)E
 * E)F
 * B)G
 * G)H
 * D)I
 * E)J
 * J)K
 * K)L
 * K)YOU
 * I)SAN
 * Visually, the above map of orbits looks like this:
 *
 *                           YOU
 *                          /
 *         G - H       J - K - L
 *        /           /
 * COM - B - C - D - E - F
 *                \
 *                 I - SAN
 * In this example, YOU are in orbit around K, and SAN is in orbit around I. To move from K to I, a minimum of 4
 * orbital transfers are required:
 *
 * K to J
 * J to E
 * E to D
 * D to I
 * Afterward, the map of orbits looks like this:
 *
 *         G - H       J - K - L
 *        /           /
 * COM - B - C - D - E - F
 *                \
 *                 I - SAN
 *                  \
 *                   YOU
 * What is the minimum number of orbital transfers required to move from the object YOU are orbiting to the object
 * SAN is orbiting? (Between the objects they are orbiting - not between YOU and SAN.)
 */
public class Day6 {

    public void solve() {
        try {
            File file = new File(getClass().getResource("/aoc2019/2019_day_6_input.txt").getFile());
            BufferedReader br = new BufferedReader(new FileReader(file));


            List<String> orbits = new ArrayList<>();
            String st;
            while ((st = br.readLine()) != null) {
                orbits.add(st);
            }


            Map<String, Orbiter> orbitMap = new HashMap<>();
            for(String s : orbits) {
                String[] twoOrbitors = s.split("\\)");
                Orbiter thingThatIsOrbitted;
                Orbiter thingthatOrbits;

                if(!orbitMap.containsKey(twoOrbitors[0])) {
                    thingThatIsOrbitted = new Orbiter(twoOrbitors[0]);
                    orbitMap.put(twoOrbitors[0], thingThatIsOrbitted);
                }else {
                    thingThatIsOrbitted = orbitMap.get(twoOrbitors[0]);
                }

                if(!orbitMap.containsKey(twoOrbitors[1])) {
                    thingthatOrbits = new Orbiter(twoOrbitors[1]);
                    orbitMap.put(twoOrbitors[1], thingthatOrbits);
                }else {
                    thingthatOrbits = orbitMap.get(twoOrbitors[1]);
                }

                thingThatIsOrbitted.addOrbit(thingthatOrbits);

            }

            int numOrbits = orbitMap.get("COM").calculateOrbits();

            System.out.println("Number of orbits COM has in total " + numOrbits);

            //Start as YOU, try to find SAN
            Orbiter you = orbitMap.get("YOU");
            Orbiter san = orbitMap.get("SAN");

            List<Orbiter> visited = new ArrayList<>();
            List<Orbiter> toVisit = new ArrayList<>();
            List<Orbiter> toVisitNext = new ArrayList<>();
            int numVisits = -2; //Need to account that I count my "starting" point as 1, and my ending point as 1, to produce the right number.
            boolean foundSanta = false;
            toVisit.add(you.getParent());
            while(!foundSanta && toVisit.size() > 0) {
                numVisits++;
                for(Orbiter o : toVisit) {
                    if(o.equals(san)) {
                        foundSanta = true;
                        break;
                    }

                    if(!visited.contains(o)) {
                        if(o.getParent() != null) {
                            toVisitNext.add(o.getParent());
                        }
                        toVisitNext.addAll(o.getThingsThatOrbitThis());
                        visited.add(o);
                    }
                }

                toVisit = toVisitNext;
                toVisitNext = new ArrayList<>();
            }

            System.out.println("Number of hops to Santa " + numVisits);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Day6 d = new Day6();
        d.solve();
    }


}
