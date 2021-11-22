package net.chewett.adventofcode.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is a holder for an incredibly naive and simple travelling salesman problem
 * It generates all permutations (memory expensive) and then goes through them one by one to solve the TSP
 */
public class NaiveTspSolver {

    private Map<String, Map<String, Integer>> distances = new HashMap<>();
    private List<String> allLocationNames = new ArrayList<>();

    /**
     * This assumes that the data given to it is well formed according to the format
     * @param distances List of strings representing the distance between two locations
     */
    public NaiveTspSolver(List<String> distances) {
        for(String str : distances) {
            //The format for this is BLAH to BLAH = DISTANCE so we split on to and =
            String[] parts = str.split(" (to|=) ");
            String firstName = parts[0];
            String secondName = parts[1];
            int distance = Integer.parseInt(parts[2]);

            //Here we enforce an ordering which will be used later to ensure whatever way around the data is, is doesn't cause problems
            if(firstName.compareTo(secondName) > 0) {
                String tmp = firstName;
                firstName = secondName;
                secondName = tmp;
            }

            if(!this.distances.containsKey(firstName)) {
                this.distances.put(firstName, new HashMap<>());
            }

            this.distances.get(firstName).put(secondName, distance);

            //Store the names to use later
            if(!this.allLocationNames.contains(firstName)) {
                this.allLocationNames.add(firstName);
            }
            if(!this.allLocationNames.contains(secondName)) {
                this.allLocationNames.add(secondName);
            }
        }

    }

    /**
     * This is a small helper function to ensure that the ordering of the location names is preserved when we are
     * accessing the data. Assuming that both locations exist in the map it will find the distance between the two.
     * @param locationOne First location to check the distance between the second
     * @param locationTwo Second location to check the distance between the first
     * @return The distance between the two locations
     */
    public int getDistanceBetweenLocations(String locationOne, String locationTwo) {
        if(locationOne.compareTo(locationTwo) > 0) {
            String tmp = locationOne;
            locationOne = locationTwo;
            locationTwo = tmp;
        }
        return this.distances.get(locationOne).get(locationTwo);

    }

    /**
     * This will attempt to solve the TSP problem to find the shortest distance
     * @return The shortest distance starting at one location and finishing at another visiting all locations
     */
    public int solveForShortest() {
        PermutationGenerator<String> pg = new PermutationGenerator<>();
        List<List<String>> allLocationPermutations = pg.generatePermutations(this.allLocationNames);
        int minDistance = Integer.MAX_VALUE;
        //Find all the permutations, then loop over each one attempting to see if we can find a new shortest distance
        for(List<String> permutation : allLocationPermutations) {
            int curDistance = 0;
            for(int i = 1; i < permutation.size(); i++) {
                curDistance += this.getDistanceBetweenLocations(permutation.get(i - 1), permutation.get(i));
                //Here we can take a small shortcut as if we know the current shortest we can give up if this grows longer or the same
                if(curDistance >= minDistance) {
                    break;
                }
            }

            //Only replace it if we have found something shorter
            if(curDistance < minDistance) {
                minDistance = curDistance;
            }
        }

        return minDistance;
    }

    /**
     * This will attempt to solve the TSP problem to find the longest distance
     * @return The longest distance starting at one location and finishing at another visiting all locations
     */
    public int solveForLongest() {
        PermutationGenerator<String> pg = new PermutationGenerator<>();
        List<List<String>> allLocationPermutations = pg.generatePermutations(this.allLocationNames);
        int maxDistance = Integer.MIN_VALUE;
        //Find all the permutations, then loop over each one attempting to see if we can find a new longest distance
        for(List<String> permutation : allLocationPermutations) {
            int curDistance = 0;
            for(int i = 1; i < permutation.size(); i++) {
                curDistance += this.getDistanceBetweenLocations(permutation.get(i - 1), permutation.get(i));
            }

            if(curDistance > maxDistance) {
                maxDistance = curDistance;
            }
        }

        return maxDistance;
    }

}
