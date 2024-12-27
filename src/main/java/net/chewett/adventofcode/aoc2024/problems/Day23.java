package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.*;

/**
 * --- Day 23: LAN Party ---
 * As The Historians wander around a secure area at Easter Bunny HQ, you come across posters for a LAN party
 * scheduled for today! Maybe you can find it; you connect to a nearby datalink port and download a map of the local
 * network (your puzzle input).
 *
 * The network map provides a list of every connection between two computers. For example:
 *
 * kh-tc
 * qp-kh
 * de-cg
 * ka-co
 * yn-aq
 * qp-ub
 * cg-tb
 * vc-aq
 * tb-ka
 * wh-tc
 * yn-cg
 * kh-ub
 * ta-co
 * de-co
 * tc-td
 * tb-wq
 * wh-td
 * ta-ka
 * td-qp
 * aq-cg
 * wq-ub
 * ub-vc
 * de-ta
 * wq-aq
 * wq-vc
 * wh-yn
 * ka-de
 * kh-ta
 * co-tc
 * wh-qp
 * tb-vc
 * td-yn
 * Each line of text in the network map represents a single connection; the line kh-tc represents a connection between
 * the computer named kh and the computer named tc. Connections aren't directional; tc-kh would mean exactly the same
 * thing.
 *
 * LAN parties typically involve multiplayer games, so maybe you can locate it by finding groups of connected
 * computers. Start by looking for sets of three computers where each computer in the set is connected to the other
 * two computers.
 *
 * In this example, there are 12 such sets of three inter-connected computers:
 *
 * aq,cg,yn
 * aq,vc,wq
 * co,de,ka
 * co,de,ta
 * co,ka,ta
 * de,ka,ta
 * kh,qp,ub
 * qp,td,wh
 * tb,vc,wq
 * tc,td,wh
 * td,wh,yn
 * ub,vc,wq
 * If the Chief Historian is here, and he's at the LAN party, it would be best to know that right away. You're pretty
 * sure his computer's name starts with t, so consider only sets of three computers where at least one computer's name
 * starts with t. That narrows the list down to 7 sets of three inter-connected computers:
 *
 * co,de,ta
 * co,ka,ta
 * de,ka,ta
 * qp,td,wh
 * tb,vc,wq
 * tc,td,wh
 * td,wh,yn
 * Find all the sets of three inter-connected computers. How many contain at least one computer with a name that
 * starts with t?
 *
 * --- Part Two ---
 * There are still way too many results to go through them all. You'll have to find the LAN party another way and
 * go there yourself.
 *
 * Since it doesn't seem like any employees are around, you figure they must all be at the LAN party. If that's true,
 * the LAN party will be the largest set of computers that are all connected to each other. That is, for each computer
 * at the LAN party, that computer will have a connection to every other computer at the LAN party.
 *
 * In the above example, the largest set of computers that are all connected to each other is made up of
 * co, de, ka, and ta. Each computer in this set has a connection to every other computer in the set:
 *
 * ka-co
 * ta-co
 * de-co
 * ta-ka
 * de-ta
 * ka-de
 * The LAN party posters say that the password to get into the LAN party is the name of every computer at the LAN
 * party, sorted alphabetically, then joined together with commas. (The people running the LAN party are clearly a
 * bunch of nerds.) In this example, the password would be co,de,ka,ta.
 *
 * What is the password to get into the LAN party?
 */
public class Day23 {

    /**
     * Givne the input this creates a set of mapping between the computer and it's neighbours
     * @param input List of mappings
     * @return Mapping between computers and it's neighbours
     */
    private Map<String, Set<String>> getConnections(List<String> input) {
        Map<String, Set<String>> connections = new HashMap<>();
        for(String line : input) {
            String[] split = line.split("-");
            if(!connections.containsKey(split[0])) {
                connections.put(split[0], new HashSet<>());
            }
            if(!connections.containsKey(split[1])) {
                connections.put(split[1], new HashSet<>());
            }
            connections.get(split[0]).add(split[1]);
            connections.get(split[1]).add(split[0]);
        }
        return connections;
    }

    /**
     * Works out how many lan parties who contain a computer starting with t
     * @param input List of mappings
     * @return Number of lan parties which contain a computer starting with t
     */
    public long solvePartOne(List<String> input) {
        Map<String, Set<String>> connections = this.getConnections(input);

        Set<List<String>> triples = new HashSet<>();
        for(Map.Entry<String, Set<String>> entry : connections.entrySet()) {
            for(String connection : entry.getValue()) {
                for(String connection2 : entry.getValue()) {
                    if(connection.equals(connection2)) {
                        continue;
                    }

                    //Work out if connection1 contains connection2 and therefore this is a computer that touches 3 others
                    if(connections.get(connection).contains(connection2)) {
                        List<String> newlist = new ArrayList<>();
                        newlist.add(entry.getKey());
                        newlist.add(connection);
                        newlist.add(connection2);
                        //Sort to make the setting above easy
                        Collections.sort(newlist);
                        triples.add(newlist);
                    }
                }
            }
        }

        //Simple count of those with a t in it
        long countWithT = 0;
        for(List<String> triple : triples) {
            for(String connection : triple) {
                if(connection.startsWith("t")) {
                    countWithT++;
                    break;
                }
            }
        }

        return countWithT;
    }

    /**
     * Works out the largest lan party by looking for the biggest clique
     * @param input
     * @return
     */
    public String solvePartTwo(List<String> input) {
        Map<String, Set<String>> connections = this.getConnections(input);

        List<Set<String>> cliques = this.findMaximalCliques(connections);
        List<String> bigClique = new ArrayList<>();
        for(Set<String> clique : cliques) {
            if(clique.size() > bigClique.size()) {
                bigClique = new ArrayList<>(clique);
            }
        }

        Collections.sort(bigClique);
        return String.join(",", bigClique);
    }

    /**
     * Simple function to call Bron Kerbosch recursively to get the maximal cliques
     * @param graph Mapping between the nodes and which nodes it connects to
     * @return Returns a list of sets containing the maximal cliques
     */
    public List<Set<String>> findMaximalCliques(Map<String, Set<String>> graph) {
        List<Set<String>> cliques = new ArrayList<>();
        Set<String> R = new HashSet<>();
        Set<String> P = new HashSet<>(graph.keySet());
        Set<String> X = new HashSet<>();

        bronKerboschRecursiveStep(R, P, X, cliques, graph);
        return cliques;
    }

    /**
     * This returns nothing but modifies the parameters you pass in
     * Once complete "cliques" will have the set of cliques
     *
     * See: https://en.wikipedia.org/wiki/Bron%E2%80%93Kerbosch_algorithm
     *
     * Pretty standard algorithm, very fancy
     *
     * @param R
     * @param P
     * @param X
     * @param cliques
     * @param graph
     */
    private void bronKerboschRecursiveStep(Set<String> R, Set<String> P, Set<String> X, List<Set<String>> cliques, Map<String, Set<String>> graph) {
        if (P.isEmpty() && X.isEmpty()) {
            cliques.add(new HashSet<>(R));
            return;
        }

        Set<String> pCopy = new HashSet<>(P);
        for (String v : pCopy) {
            R.add(v);
            Set<String> neighbors = graph.getOrDefault(v, new HashSet<>());
            Set<String> newP = new HashSet<>(P);
            newP.retainAll(neighbors);
            Set<String> newX = new HashSet<>(X);
            newX.retainAll(neighbors);

            bronKerboschRecursiveStep(R, newP, newX, cliques, graph);

            R.remove(v);
            P.remove(v);
            X.add(v);
        }
    }


    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 23);

        Day23 d = new Day23();
        long partOne = d.solvePartOne(input);
        System.out.println("The number of lan parties containing a t computer is " + partOne);

        String partTwo = d.solvePartTwo(input);
        System.out.println("The largest lan party is " + partTwo);
    }
}


