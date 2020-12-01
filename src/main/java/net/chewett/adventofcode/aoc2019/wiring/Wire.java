package net.chewett.adventofcode.aoc2019.wiring;

import java.util.*;

public class Wire {

    private Set<WireJoin> wireJoinSet;
    private Map<Integer, Map<Integer, Integer>> pointLengthMap = new HashMap<>();

    public Wire() {
        this.wireJoinSet = new HashSet<>();
    }

    private void addToPointMap(int x, int y, int distanceFromStart) {
        if(!this.pointLengthMap.containsKey(x)) {
            this.pointLengthMap.put(x, new HashMap<>());
        }

        if(!this.pointLengthMap.get(x).containsKey(y)) {
            this.pointLengthMap.get(x).put(y, distanceFromStart);
        }
    }

    public void createWireFromCommands(String commandString) {
        String[] commands = commandString.split(",");
        int x = 0;
        int y = 0;
        int distance = 0;
        for(String instruction : commands) {
            String direction = instruction.substring(0, 1);
            int movement = Integer.parseInt(instruction.substring(1));

            int xMovement = 0;
            int yMovement = 0;
            if (direction.equals("R")) {
                xMovement = 1;
            } else if (direction.equals("L")) {
                xMovement = -1;
            } else if (direction.equals("U")) {
                yMovement = 1;
            } else if (direction.equals("D")) {
                yMovement = -1;
            } else {
                throw new RuntimeException("BAD INSTRUCTION");
            }

            for (int i = 0; i < movement; i++) {
                distance++;
                int oldX = x;
                int oldY = y;
                x += xMovement;
                y += yMovement;

                this.addToPointMap(x, y, distance);
                this.addWireJoinList(new WireJoin(oldX, oldY, x, y));
            }
        }


    }

    private void addWireJoinList(WireJoin wj) {
        this.wireJoinSet.add(wj);
    }

    public Set<WirePoint> getAllWirePoints() {
        Set<WirePoint> allWirePoints = new HashSet<>();
        for(WireJoin wj : wireJoinSet) {
            allWirePoints.add(new WirePoint(wj.x1, wj.y1));
            allWirePoints.add(new WirePoint(wj.x2, wj.y2));
        }

        WirePoint zeroZeroToRemove = new WirePoint(0, 0);
        allWirePoints.remove(zeroZeroToRemove);

        return allWirePoints;
    };

    public int getDistanceToWp(WirePoint wp) {
        return this.pointLengthMap.get(wp.x).get(wp.y);
    }

}
