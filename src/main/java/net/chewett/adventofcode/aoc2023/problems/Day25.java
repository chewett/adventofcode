package net.chewett.adventofcode.aoc2023.problems;


import net.chewett.adventofcode.datastructures.OrderedPair;
import net.chewett.adventofcode.aoc2023.Day25NodeVisitCount;
import net.chewett.adventofcode.aoc2023.Day25State;
import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.*;

/**
 * --- Day 25: Snowverload ---
 * Still somehow without snow, you go to the last place you haven't checked: the center of Snow Island, directly below
 * the waterfall.
 *
 * Here, someone has clearly been trying to fix the problem. Scattered everywhere are hundreds of weather machines,
 * almanacs, communication modules, hoof prints, machine parts, mirrors, lenses, and so on.
 *
 * Somehow, everything has been wired together into a massive snow-producing apparatus, but nothing seems to be
 * running. You check a tiny screen on one of the communication modules: Error 2023. It doesn't say what Error 2023
 * means, but it does have the phone number for a support line printed on it.
 *
 * "Hi, you've reached Weather Machines And So On, Inc. How can I help you?" You explain the situation.
 *
 * "Error 2023, you say? Why, that's a power overload error, of course! It means you have too many components plugged
 * in. Try unplugging some components and--" You explain that there are hundreds of components here and you're in a
 * bit of a hurry.
 *
 * "Well, let's see how bad it is; do you see a big red reset button somewhere? It should be on its own module. If you
 * push it, it probably won't fix anything, but it'll report how overloaded things are." After a minute or two, you
 * find the reset button; it's so big that it takes two hands just to get enough leverage to push it. Its screen then
 * displays:
 *
 * SYSTEM OVERLOAD!
 *
 * Connected components would require
 * power equal to at least 100 stars!
 * "Wait, how many components did you say are plugged in? With that much equipment, you could produce snow for an
 * entire--" You disconnect the call.
 *
 * You have nowhere near that many stars - you need to find a way to disconnect at least half of the equipment here,
 * but it's already Christmas! You only have time to disconnect three wires.
 *
 * Fortunately, someone left a wiring diagram (your puzzle input) that shows how the components are connected.
 * For example:
 *
 * jqt: rhn xhk nvd
 * rsh: frs pzl lsr
 * xhk: hfx
 * cmg: qnr nvd lhk bvb
 * rhn: xhk bvb hfx
 * bvb: xhk hfx
 * pzl: lsr hfx nvd
 * qnr: nvd
 * ntq: jqt hfx bvb xhk
 * nvd: lhk
 * lsr: lhk
 * rzs: qnr cmg lsr rsh
 * frs: qnr lhk lsr
 * Each line shows the name of a component, a colon, and then a list of other components to which that component is
 * connected. Connections aren't directional; abc: xyz and xyz: abc both represent the same configuration. Each
 * connection between two components is represented only once, so some components might only ever appear on the left
 * or right side of a colon.
 *
 * In this example, if you disconnect the wire between hfx/pzl, the wire between bvb/cmg, and the wire between nvd/jqt,
 * you will divide the components into two separate, disconnected groups:
 *
 * 9 components: cmg, frs, lhk, lsr, nvd, pzl, qnr, rsh, and rzs.
 * 6 components: bvb, hfx, jqt, ntq, rhn, and xhk.
 * Multiplying the sizes of these groups together produces 54.
 *
 * Find the three wires you need to disconnect in order to divide the components into two separate groups. What do you
 * get if you multiply the sizes of these two groups together?
 */
public class Day25 {

    /**
     * Work out the three nodes that are linking the two groups and then remove them and work out the sizes of the two groups
     * @param input List of connections between the groups
     * @return Multiplication of the two group sizes
     */
    public long solvePartOne(List<String> input) {

        //Track the first node we found just to make it easier later on to use that node
        String firstNodeFound = null;
        Map<String, Set<String>> connections = new HashMap<>();

        //Track the connections between the different nodes
        for(String line : input) {
            String[] parts = line.split(": ");
            String name = parts[0];
            String[] connectedTo = parts[1].split(" ");

            //Keep track of the first node we have found for later use
            if(firstNodeFound == null) {
                firstNodeFound = parts[0];
            }

            if(!connections.containsKey(name)) {
                connections.put(name, new HashSet<>());
            }

            for(String conn : connectedTo) {
                if(!connections.containsKey(conn)) {
                    connections.put(conn, new HashSet<>());
                }
                connections.get(name).add(conn);
                connections.get(conn).add(name);
            }
        }

        //Right.. now we are going to work out the edges which are most visited.
        //What we are going to do is visit all the nodes from all of the nodes and count every time we cross an edge
        Map<OrderedPair, Integer> edgeVisitedCount = new HashMap<>();
        for(Map.Entry<String, Set<String>> entry : connections.entrySet()) {

            Set<String> visited = new HashSet<>();
            visited.add(entry.getKey());
            Queue<Day25State> q = new LinkedList<>();
            q.add(new Day25State(entry.getKey(), new HashSet<>()));

            while(!q.isEmpty()) {
                Day25State s = q.poll();

                //Add the edges we have visited to the count
                for(OrderedPair pair : s.getVisited()) {
                    if(!edgeVisitedCount.containsKey(pair)) {
                        edgeVisitedCount.put(pair, 0);
                    }
                    edgeVisitedCount.put(pair, edgeVisitedCount.get(pair) + 1);
                }

                //Loop over the next states and add them to the queue to visit
                for(String nextPositions : connections.get(s.getCur())) {
                    if(!visited.contains(nextPositions)) {
                        OrderedPair newPair = new OrderedPair(s.getCur(), nextPositions);
                        visited.add(nextPositions);
                        Set<OrderedPair> thisVisited = new HashSet<>(s.getVisited());
                        thisVisited.add(newPair);
                        q.add(new Day25State(nextPositions, thisVisited));
                    }
                }
            }
        }

        //Store the distances for each edge into a simple list then order it
        List<Day25NodeVisitCount> distances = new ArrayList<>();
        for(Map.Entry<OrderedPair, Integer> e : edgeVisitedCount.entrySet()) {
            distances.add(new Day25NodeVisitCount(e.getKey(), e.getValue()));
        }
        distances.sort(Collections.reverseOrder());

        //Now remove the top three connections
        for(int i = 0; i < 3; i++) {
            Day25NodeVisitCount n = distances.get(i);
            connections.get(n.getLocation().getPairOne()).remove(n.getLocation().getPairTwo());
            connections.get(n.getLocation().getPairTwo()).remove(n.getLocation().getPairOne());
        }

        // Now we can start at the "first" node (logged at the top) and then see how many nodes we visit
        Set<String> visited = new HashSet<>();
        visited.add(firstNodeFound);
        Queue<String> q = new LinkedList<>();
        q.add(firstNodeFound);
        while(!q.isEmpty()) {
            String currentNode = q.poll();
            for(String nextLocation : connections.get(currentNode)) {
                if(!visited.contains(nextLocation)) {
                    visited.add(nextLocation);
                    q.add(nextLocation);
                }
            }
        }

        //Once we have visited all of the nodes we can, we just multiply that size with the "other" size of the node and we have the answer
        return visited.size() * (connections.size() - visited.size());
    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 25);

        Day25 d = new Day25();
        long partOne = d.solvePartOne(input);
        System.out.println("The sizes of two groups multiplied by two is " + partOne);
    }
}


