package net.chewett.adventofcode2019.wiring;

import org.junit.Assert;
import org.junit.Test;

public class WireTest {

    @Test
    public void testDistanceCalculations() {
        Wire w = new Wire();

        w.createWireFromCommands("U10");
        int distance = w.getDistanceToWp(new WirePoint(0, 10));
        Assert.assertEquals(10, distance);
    }

    @Test
    public void testOverlapDistanceCalc() {
        Wire w = new Wire();
        w.createWireFromCommands("R1,U1,R1,D2,L3,U2,R3");
        int distance = w.getDistanceToWp(new WirePoint(0, 1));
        Assert.assertEquals(3, distance);

    }


}
