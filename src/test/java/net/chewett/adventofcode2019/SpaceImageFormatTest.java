package net.chewett.adventofcode2019;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SpaceImageFormatTest {


    @Test
    public void simpleImageTest() {
        String imageData = "123456789012";
        SpaceImageFormat img = new SpaceImageFormat(imageData, 3, 2);
        Assert.assertEquals(2, img.getNumOfLayers());
    }



}
