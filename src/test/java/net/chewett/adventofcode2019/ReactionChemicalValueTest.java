package net.chewett.adventofcode2019;

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
