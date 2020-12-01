package net.chewett.adventofcode.aoc2019;

import net.chewett.adventofcode.aoc2019.ReactionChemicalValue;
import org.junit.Assert;
import org.junit.Test;

public class ReactionChemicalValueTest {

    @Test
    public void constructorTest() {
        ReactionChemicalValue rcv = new ReactionChemicalValue("  10 FUEL  ");
        Assert.assertEquals(10, rcv.getValue());
        Assert.assertEquals("FUEL", rcv.getReactionChemicalName());
    }


}
