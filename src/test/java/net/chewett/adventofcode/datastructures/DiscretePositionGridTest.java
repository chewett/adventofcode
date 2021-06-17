package net.chewett.adventofcode.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class DiscretePositionGridTest {

    @Test
    public void testIntConstructor() {
        DiscretePositionGrid<Integer> dpg = new DiscretePositionGrid<>(0);
        Assert.assertNotNull(dpg);
    }

    @Test
    public void testCharConstructor() {
        DiscretePositionGrid<Character> dpg = new DiscretePositionGrid<>('0');
        Assert.assertNotNull(dpg);
    }

    @Test
    public void testGetValueAtPositionWithDefault() {
        DiscretePositionGrid<Integer> dpg = new DiscretePositionGrid<>(0);
        int i = dpg.getValueAtPosition(123, 456);
        Assert.assertEquals(0, i);
    }

    @Test
    public void testSetGetValueAtPositionWithNoDefault() {
        DiscretePositionGrid<Integer> dpg = new DiscretePositionGrid<>(0);
        dpg.setValueAtPosition(123, 456, 111);
        int i = dpg.getValueAtPosition(123, 456);
        Assert.assertEquals(111, i);
    }

    @Test
    public void testCountInstancesOfValue() {
        DiscretePositionGrid<Integer> dpg = new DiscretePositionGrid<>(13);
        dpg.setValueAtPosition(1, 3, 7);
        dpg.setValueAtPosition(5, 3, 7);
        dpg.setValueAtPosition(9, 3, 7);
        dpg.setValueAtPosition(11, 3, 7);
        dpg.setValueAtPosition(54, 3, 7);
        //Set the same value twice to make sure its not doing some funky stuff to count it
        dpg.setValueAtPosition(11, 3, 7);

        Assert.assertEquals(5, dpg.countInstancesOfValue(7));
    }



}
