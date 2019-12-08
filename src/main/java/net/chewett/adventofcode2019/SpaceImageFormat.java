package net.chewett.adventofcode2019;

import java.util.ArrayList;
import java.util.List;

public class SpaceImageFormat {

    public static final int PIXEL_BLACK = 0;
    public static final int PIXEL_WHITE = 1;
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

    public SpaceImageLayer generateFinalMergedLayer() {
        int lengthOfString = this.width * this.height;
        String mergeLayerText = "";
        for(int i = 0; i < lengthOfString; i++) {
            mergeLayerText += SpaceImageFormat.PIXEL_TRANSPARENT;
        }

        SpaceImageLayer finalMergedLayer = new SpaceImageLayer(mergeLayerText, this.width, this.height);

        for(SpaceImageLayer layer : this.layers) {
            finalMergedLayer.mergeLayer(layer);
        }

        return finalMergedLayer;
    }

    public int getNumOfLayers() {
        return this.layers.size();
    }
}
