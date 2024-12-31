package net.chewett.adventofcode.aoc2019;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpaceImageFormatTest {

    /**
     * Quick test to verify that the layer function works fine with the example data with a smaller canvas
     */
    @Test
    public void simpleImageTest() {
        String imageData = "123456789012";
        SpaceImageFormat img = new SpaceImageFormat(imageData, 3, 2);
        Assertions.assertEquals(2, img.getNumOfLayers());
    }



}
