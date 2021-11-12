package net.chewett.adventofcode.aoc2019;

import net.chewett.adventofcode.aoc2019.ReactionChemicalValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReactionChemicalValueTest {

    @Test
    public void constructorTest() {
        ReactionChemicalValue rcv = new ReactionChemicalValue("  10 FUEL  ");
        Assertions.assertEquals(10, rcv.getValue());
        Assertions.assertEquals("FUEL", rcv.getReactionChemicalName());
    }


}
