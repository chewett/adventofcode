package net.chewett.adventofcode2019.wiring;

import java.util.*;

public class Wire {

    private Set<WireJoin> wireJoinSet;

    public Wire() {
        this.wireJoinSet = new HashSet<>();
    }

    public void createWireFromCommands(String commandString) {
        String[] commands = commandString.split(",");
        int x = 0;
        int y = 0;
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
                int oldX = x;
                int oldY = y;
                x += xMovement;
                y += yMovement;

                this.addWireJoinList(new WireJoin(oldX, oldY, x, y));
            }
        }


    }

    public void addWireJoinList(WireJoin wj) {
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
        Set<WireJoin> allWireJoinsLeftToUse = new HashSet<>();
        Map<WirePoint, List<WireJoin>> pointListMap = new HashMap<>();
        for(WireJoin wjToAdd: this.wireJoinSet) {
            allWireJoinsLeftToUse.add(wjToAdd);
        }

        int currentDistance = 0;
        List<WirePoint> wirePointsToCheck = new ArrayList<>();
        List<WirePoint> wirePointsToCheckNext = new ArrayList<>();
        wirePointsToCheck.add(new WirePoint(0, 0));

        while(wirePointsToCheck.size() > 0) {
            currentDistance++;
            for(WirePoint wpToCheck : wirePointsToCheck) {
                Iterator<WireJoin> wjIt = allWireJoinsLeftToUse.iterator();
                while(wjIt.hasNext()) {
                    WireJoin wj = wjIt.next();
                    if(wj.containsPoint(wpToCheck)) {
                        if(wj.containsPoint(wp)) {
                            return currentDistance;
                        }

                        wirePointsToCheckNext.add(wj.getOtherPoint(wpToCheck));
                        wjIt.remove();
                    }
                }
            }

            wirePointsToCheck = wirePointsToCheckNext;
            wirePointsToCheckNext = new ArrayList<>();

        }



        return 999999999;

    }

    public int getDistanceToWpNew(WirePoint wp) {
        Map<String, List<WireJoin>> pointListMap = new HashMap<>();
        for(WireJoin wjToAdd: this.wireJoinSet) {
            WirePoint[] bothPoints = wjToAdd.getBothPoints();
            for(WirePoint wirePointToAddToMap : bothPoints) {
                String key = wirePointToAddToMap.getMapString();
                if(!pointListMap.containsKey(key)) {
                    pointListMap.put(key, new ArrayList<>());
                }
                pointListMap.get(key).add(wjToAdd);
            }
        }

        int currentDistance = 0;
        List<WirePoint> wirePointsToCheck = new ArrayList<>();
        List<WirePoint> wirePointsToCheckNext = new ArrayList<>();
        wirePointsToCheck.add(new WirePoint(0, 0));

        while(wirePointsToCheck.size() > 0) {
            currentDistance++;
            for(WirePoint wpToCheck : wirePointsToCheck) {
                String keyToLookFor = wpToCheck.getMapString();
                if(pointListMap.containsKey(keyToLookFor)) {
                    for(WireJoin newWjToCheck : pointListMap.get(keyToLookFor)) {
                        if(newWjToCheck.containsPoint(wp)) {
                            return currentDistance;
                        }else{
                            wirePointsToCheckNext.add(newWjToCheck.getOtherPoint(wpToCheck));
                        }
                    }
                }
            }

            wirePointsToCheck = wirePointsToCheckNext;
            wirePointsToCheckNext = new ArrayList<>();

        }



        return 999999999;

    }



}
