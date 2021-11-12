package net.chewett.adventofcode.aoc2019;

import net.chewett.adventofcode.aoc2019.MapArea;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapAreaTest {

    private MapArea getBasicMapArea() {
        MapArea ma = new MapArea();
        for(int x = 0; x < 20; x++) {
            for(int y = 0; y < 20; y++) {
                ma.setMapData(x, y, '.');
            }
        }
        return ma;
    }

    @Test
    public void testBaseMapDataSetting() {
        MapArea ma = new MapArea();
        ma.setMapData(0, 0, '.');

        Assertions.assertEquals('.', ma.getMapData(0, 0));
        Assertions.assertEquals('?', ma.getMapData(0, 1));
    }

    @Test
    public void testPathingMoveNorth() {
        MapArea ma = this.getBasicMapArea();
        int directionToMove = ma.getDirectionToMoveToPoint(0, 10, 0, 6);
        Assertions.assertEquals(1, directionToMove);
    }

    @Test
    public void testPathingMoveSouth() {
        MapArea ma = this.getBasicMapArea();
        int directionToMove = ma.getDirectionToMoveToPoint(0, 10, 0, 15);
        Assertions.assertEquals(2, directionToMove);
    }

    @Test
    public void testPathingMoveEast() {
        MapArea ma = this.getBasicMapArea();
        int directionToMove = ma.getDirectionToMoveToPoint(0, 10, 10, 10);
        Assertions.assertEquals(4, directionToMove);
    }

    @Test
    public void testPathingMoveWest() {
        MapArea ma = this.getBasicMapArea();
        int directionToMove = ma.getDirectionToMoveToPoint(10, 10, 0, 10);
        Assertions.assertEquals(3, directionToMove);
    }

    @Test
    public void testPathingMoveWithObstacle() {
        MapArea ma = new MapArea();
        ma.setMapData(0, 0, '.');
        ma.setMapData(0, 1, '#');
        ma.setMapData(1, 0, '.');
        ma.setMapData(1, 1, '?');

        int directionToMove = ma.getDirectionToMoveToPoint(0, 0, 1, 1);
        Assertions.assertEquals(4, directionToMove);
    }


}
