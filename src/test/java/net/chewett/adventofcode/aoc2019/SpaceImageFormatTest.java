package net.chewett.adventofcode.aoc2019;


import net.chewett.adventofcode.aoc2019.SpaceImageFormat;
import org.junit.Assert;
import org.junit.Test;

public class SpaceImageFormatTest {


    @Test
    public void simpleImageTest() {
        String imageData = "123456789012";
        SpaceImageFormat img = new SpaceImageFormat(imageData, 3, 2);
        Assert.assertEquals(2, img.getNumOfLayers());
    }



}
