package net.chewett.adventofcode.aoc2019;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data holder holding information about how we are filling the map with oxygen for 2019 Day 15
 *
 * TODO: See if some of this can be replaced by the 2D Discrete Character grid
 */
public class OxygenRefillingModel {

    private Map<Point, Character> mapData = new HashMap<>();

    public OxygenRefillingModel(MapArea ma) {
        this.copyMapToOtherMap(ma.getMapData(), this.mapData);
    }

    private boolean stillNeedsOxygen(Map<Point, Character> map) {
        for( Map.Entry<Point, Character> p : map.entrySet()) {
            if(p.getValue() == '.') {
                return true;
            }
        }
        return false;
    }

    private void copyMapToOtherMap(Map<Point, Character> thingToCopy, Map<Point, Character> thingToHaveNewValues) {
        for(Map.Entry<Point, Character> p : thingToCopy.entrySet()) {
            thingToHaveNewValues.put(p.getKey(), p.getValue());
        }
    }

    public int getMinutesToFillAreaWithOxygen() {
        int minute = 0;

        Map<Point, Character> currentOxygen = new HashMap<>();
        Map<Point, Character> nextOxygen = new HashMap<>();
        this.copyMapToOtherMap(this.mapData, currentOxygen);
        this.copyMapToOtherMap(this.mapData, nextOxygen);

        while(this.stillNeedsOxygen(currentOxygen)) {
            minute++;
            for(Map.Entry<Point, Character> p : currentOxygen.entrySet()) {
                if(p.getValue() == 'O') {
                    GraphNode gn = new GraphNode(0, p.getKey().x, p.getKey().y);
                    List<Point> adjacentPoints = gn.getAdjacentPoints();
                    for(Point point : adjacentPoints) {
                        if(nextOxygen.containsKey(point) && nextOxygen.get(point) == '.') {
                            nextOxygen.put(point, 'O');
                        }
                    }
                }
            }

            this.copyMapToOtherMap(nextOxygen, currentOxygen);
        }

        return minute;
    }


    public void draw(Map<Point, Character> thingToDraw) {
        int minX = 99999;
        int minY = 99999;
        int maxX = -99999;
        int maxY = -99999;

        for(Map.Entry<Point, Character> e : thingToDraw.entrySet()) {
            minX = Math.min(minX, e.getKey().x);
            minY = Math.min(minY, e.getKey().y);
            maxX = Math.max(maxX, e.getKey().x);
            maxY = Math.max(maxY, e.getKey().y);
        }

        for(int y = minY; y <= maxY; y++) {
            String lineToPrint = "";
            for(int x = minX; x <= maxX; x++) {
                lineToPrint += thingToDraw.getOrDefault(new Point(x, y), ' ');
            }
            System.out.println(lineToPrint);
        }


    }

}
