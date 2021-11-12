package net.chewett.adventofcode.aoc2019;

import net.chewett.adventofcode.aoc2019.Orbiter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrbiterTest {

    @Test
    public void testNoOrbits() {
        Orbiter o = new Orbiter("o");

        Assertions.assertEquals(0, o.calculateOrbits());
    }

    @Test
    public void testOneOrbits() {
        Orbiter o = new Orbiter("o");
        Orbiter o2 = new Orbiter("o2");
        o.addOrbit(o2);

        Assertions.assertEquals(1, o.calculateOrbits());

    }

    @Test
    public void testOneOrbitsAnother() {
        Orbiter o = new Orbiter("o");
        Orbiter o2 = new Orbiter("o2");
        Orbiter o3 = new Orbiter("o3");
        o.addOrbit(o2);
        o2.addOrbit(o3);

        Assertions.assertEquals(1, o2.calculateOrbits());
        Assertions.assertEquals(3, o.calculateOrbits());
    }

    @Test
    public void testManyOrbits() {
        //FIXME: incomplete.
        String orbits[] = new String[] {
            "COM)B",
            "B)C",
            "C)D",
            "D)E",
            "E)F",
            "B)G",
            "G)H",
            "D)I",
            "E)J",
            "J)K",
            "K)L"
        };

    }


}
