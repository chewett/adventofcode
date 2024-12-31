package net.chewett.adventofcode.aoc2019;

import java.util.ArrayList;
import java.util.List;

/**
 * A "Space Image Format" for 2019 Day 8
 */
public class SpaceImageFormat {

    public static final int PIXEL_BLACK = 0;
    public static final int PIXEL_TRANSPARENT = 2;

    private List<SpaceImageLayer> layers = new ArrayList<SpaceImageLayer>();
    private int width;
    private int height;


    public SpaceImageFormat(String bitData, int width, int height) {
        this.height = height;
        this.width = width;
        int bitsPerLayer = width * height;

        int curStart = 0;
        while(curStart < bitData.length()) {
            this.layers.add(new SpaceImageLayer(bitData.substring(curStart, curStart + bitsPerLayer), width, height));
            curStart += bitsPerLayer;
        }
    }

    /**
     * Simple function to work out which layer has the least number of 0's
     * @return Layer with the least number of 0's
     */
    public SpaceImageLayer findLayerWithFewestZeroDigits() {
        int numOfZeroDigits = 999999999;
        SpaceImageLayer bestLayer = null;
        for(SpaceImageLayer layer : this.layers) {
            int zeroDigitsPerLayer = layer.getNumOfDigit(0);
            if(zeroDigitsPerLayer < numOfZeroDigits) {
                numOfZeroDigits = zeroDigitsPerLayer;
                bestLayer = layer;
            }
        }

        return bestLayer;
    };

    /**
     * Go over the layers merging them together until you end up with the final picture
     * @return A Space Image Layer representing the final image
     */
    public SpaceImageLayer generateFinalMergedLayer() {
        int lengthOfString = this.width * this.height;
        StringBuilder mergeLayerText = new StringBuilder();
        for(int i = 0; i < lengthOfString; i++) {
            mergeLayerText.append(SpaceImageFormat.PIXEL_TRANSPARENT);
        }

        SpaceImageLayer finalMergedLayer = new SpaceImageLayer(mergeLayerText.toString(), this.width, this.height);

        for(SpaceImageLayer layer : this.layers) {
            finalMergedLayer.mergeLayer(layer);
        }

        return finalMergedLayer;
    }

    /**
     * Returns the number of layers in the image
     * @return Number of layers
     */
    public int getNumOfLayers() {
        return this.layers.size();
    }
}
