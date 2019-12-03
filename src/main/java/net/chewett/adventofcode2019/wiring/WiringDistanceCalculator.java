package net.chewett.adventofcode2019.wiring;

import java.util.*;

public class WiringDistanceCalculator {

    public static int calculateManhattan(String wireOne, String wireTwo) {
        return WiringDistanceCalculator.calculateStuff(wireOne, wireTwo, true);
    }

    public static int calculateOther(String wireOne, String wireTwo) {
        return WiringDistanceCalculator.calculateStuff(wireOne, wireTwo, false);
    }


    private static int calculateStuff(String wireOneString, String wireTwoString, boolean returnManhattan) {
        Wire wireOne = new Wire();
        wireOne.createWireFromCommands(wireOneString);
        Wire wireTwo = new Wire();
        wireTwo.createWireFromCommands(wireTwoString);

        Set<WirePoint> allConjoinedPoints = wireOne.getAllWirePoints();
        allConjoinedPoints.retainAll(wireTwo.getAllWirePoints());

        if(returnManhattan) {
            List<Integer> distancesFromOrigin = new ArrayList<>();
            for(WirePoint wp : allConjoinedPoints) {
                distancesFromOrigin.add(wp.getFromOrigin());
            }
            Collections.sort(distancesFromOrigin);
            return distancesFromOrigin.get(0);
        }else{
            List<Integer> totalWireDistancesFromOrigin = new ArrayList<>();
            for(WirePoint wp : allConjoinedPoints) {
                int distanceForW1 = wireOne.getDistanceToWp(wp);
                int distanceFromW2 = wireTwo.getDistanceToWp(wp);

                totalWireDistancesFromOrigin.add(distanceForW1 + distanceFromW2);
            }
            Collections.sort(totalWireDistancesFromOrigin);
            return totalWireDistancesFromOrigin.get(0);
        }


    }


}
