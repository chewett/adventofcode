package net.chewett.adventofcode.aoc2019;

import java.util.ArrayList;
import java.util.List;

public class SpaceImageLayer {

    private int width;
    private int height;

    private List<List<Integer>> imageData = new ArrayList<>();

    public SpaceImageLayer(String bits, int width, int height) {
        this.width = width;
        this.height = height;
        int curPos = 0;
        while(curPos < bits.length()) {
            String row = bits.substring(curPos, curPos + width);
            char[] rowBits = row.toCharArray();
            List<Integer> newRow = new ArrayList<>();
            for(char c : rowBits) {
                newRow.add(Character.getNumericValue(c));
            }
            this.imageData.add(newRow);

            curPos += width;
        }
    }

    public int getNumOfDigit(int numberToSearchFor) {
        int count = 0;
        for(List<Integer> row : this.imageData) {
            for(Integer i : row) {
                if(i == numberToSearchFor) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getPixelAtPos(int x, int y) {
        return this.imageData.get(y).get(x);
    }

    public void mergeLayer(SpaceImageLayer layer) {
        for(int y = 0; y < this.height; y++) {
            for(int x = 0; x < this.width; x++) {
                if(this.getPixelAtPos(x, y) == SpaceImageFormat.PIXEL_TRANSPARENT) {
                    this.imageData.get(y).set(x, layer.getPixelAtPos(x, y));
                }
            }
        }
    }

    public void printImageOut() {
        for(int y = 0; y < this.height; y++) {
            String rowToPrint = "";
            for (int x = 0; x < this.width; x++) {
                if(this.getPixelAtPos(x, y) == SpaceImageFormat.PIXEL_BLACK) {
                    rowToPrint += " ";
                }else{
                    rowToPrint += "#";
                }
            }
            System.out.println(rowToPrint);
        }
    }


}
