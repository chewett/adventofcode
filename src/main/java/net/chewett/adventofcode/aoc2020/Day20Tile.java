package net.chewett.adventofcode.aoc2020;

import net.chewett.adventofcode.helpers.FormatConversion;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper object that holds the tile data and lets you more easily perform operations needed to solve the problem
 */
public class Day20Tile {

    private int tileId;
    private List<List<Character>> charData = new ArrayList<>();

    public Day20Tile(List<String> rows, int tileId) {
        this.tileId = tileId;

        for(String row : rows) {
            List<Character> rowData = new ArrayList<>();
            for(int i = 0; i < row.length(); i++) {
                rowData.add(row.charAt(i));
            }
            this.charData.add(rowData);
        }
    }

    /**
     * Gets the stored Tile ID
     * @return The ID of the tile
     */
    public int getTileId() {
        return this.tileId;
    }

    /**
     * Gets a list of strings representing each side of the square and in each orientation.
     * @return List of strings representing each side
     */
    private List<String> getAllSideOrientations() {
        List<String> sides = new ArrayList<>();

        StringBuilder sideOneBuilder = new StringBuilder();
        StringBuilder sideTwoBuilder = new StringBuilder();
        StringBuilder sideThreeBuilder = new StringBuilder();
        StringBuilder sideFourBuilder = new StringBuilder();
        for(int i = 0; i < this.charData.get(0).size(); i++) {
            sideOneBuilder.append(this.charData.get(0).get(i));
            sideTwoBuilder.append(this.charData.get(this.charData.size()-1).get(i));
            sideThreeBuilder.append(this.charData.get(i).get(0));
            sideFourBuilder.append(this.charData.get(i).get(this.charData.size()-1));
        }

        String sideOne = sideOneBuilder.toString();
        String sideTwo = sideTwoBuilder.toString();
        String sideThree = sideThreeBuilder.toString();
        String sideFour = sideFourBuilder.toString();

        sides.add(sideOne);
        sides.add(sideTwo);
        sides.add(sideThree);
        sides.add(sideFour);

        StringBuilder reversing = new StringBuilder();
        reversing.append(sideOne).reverse();
        sides.add(reversing.toString());

        reversing = new StringBuilder();
        reversing.append(sideTwo).reverse();
        sides.add(reversing.toString());

        reversing = new StringBuilder();
        reversing.append(sideThree).reverse();
        sides.add(reversing.toString());

        reversing = new StringBuilder();
        reversing.append(sideFour).reverse();
        sides.add(reversing.toString());

        return sides;
    }

    /**
     * This converts one of the sides of the puzzles into a number which can be more easily used for comparison
     * It sneakily uses the fact there is only over a . or a # in the side which can trivially be converted to an integer
     * @param side String representing the side of the the puzzle
     * @return An integer repsenting the side of the puzzle
     */
    private int convertStringToSideInt(String side) {
        return Integer.parseInt(side.replace(".", "0").replace("#", "1"), 2);
    }

    /**
     * Returns a list of ID's representing each corner of the tile that could be connected to any other tile
     * This includes flipped and rotated tiles
     * @return A list of integers representing all the possible sides this tile could have
     */
    public List<Integer> getEdgeIds() {
        List<Integer> edgeIds = new ArrayList<>();
        List<String> allSides = this.getAllSideOrientations();
        for(String side : allSides) {
            String st = side.replace(".", "0").replace("#", "1");
            edgeIds.add(Integer.parseInt(st, 2));
        }

        return edgeIds;
    }

    /**
     * Gets the current left side as an integer
     * @return Integer representing the left side
     */
    public int getLeftSideInt() {
        StringBuilder leftSideBuilder = new StringBuilder();
        for(int i = 0; i < this.charData.size(); i++) {
            leftSideBuilder.append(this.charData.get(i).get(0));
        }

        return this.convertStringToSideInt(leftSideBuilder.toString());
    }

    /**
     * Gets the current top side as an integer
     * @return Integer representing the top side
     */
    public int getTopSideInt() {
        StringBuilder topSideBuilder = new StringBuilder();
        for(int i = 0; i < this.charData.size(); i++) {
            topSideBuilder.append(this.charData.get(0).get(i));
        }

        return this.convertStringToSideInt(topSideBuilder.toString());
    }

    /**
     * Gets the current bottom side as an integer
     * @return Integer representing the bottom side
     */
    public int getBottomSideInt() {
        StringBuilder topSideBuilder = new StringBuilder();
        for(int i = 0; i < this.charData.size(); i++) {
            topSideBuilder.append(this.charData.get(this.charData.size()-1).get(i));
        }

        return this.convertStringToSideInt(topSideBuilder.toString());
    }

    /**
     * Gets the current right side as an integer
     * @return Integer representing the right side
     */
    public int getRightSideInt() {
        StringBuilder rightSideBuilder = new StringBuilder();
        for(int i = 0; i < this.charData.size(); i++) {
            rightSideBuilder.append(this.charData.get(i).get(this.charData.size()-1));
        }

        return this.convertStringToSideInt(rightSideBuilder.toString());
    }

    /**
     * Gets the flipped form of this tile
     * @return A mirror image of this tile, flipped over
     */
    public Day20Tile getFlippedTile() {
        List<List<Character>> charData = FormatConversion.flipCharListList(this.charData);
        List<String> newD = FormatConversion.convertCharListListToStringList(charData);

        return new Day20Tile(newD, this.getTileId());
    }

    /**
     * Gets all the different ways of representing this tile with the various flips and rotates
     * @return A list of tiles representing all the ways to orient this
     */
    public List<Day20Tile> getAllOrientationsWithFlips() {
        List<Day20Tile> allOrientations = this.getAllOrientations();
        List<Day20Tile> flippedOrientations = new ArrayList<>();
        for(Day20Tile t : allOrientations) {
            flippedOrientations.add(t.getFlippedTile());
        }
        allOrientations.addAll(flippedOrientations);
        return allOrientations;
    }

    /**
     * Returns a list of tiles that represent all possible ways to rotate this tile
     * @return A list of all possible ways to rotate this tile
     */
    public List<Day20Tile> getAllOrientations() {
        List<Day20Tile> allOrientations = new ArrayList<>();
        List<List<Character>> curData = this.charData;

        List<String> currentTileData = FormatConversion.convertCharListListToStringList(curData);
        allOrientations.add(new Day20Tile(currentTileData, this.getTileId()));

        curData = FormatConversion.rotateCharListListAnticlockwise(curData);
        currentTileData = FormatConversion.convertCharListListToStringList(curData);
        allOrientations.add(new Day20Tile(currentTileData, this.getTileId()));

        curData = FormatConversion.rotateCharListListAnticlockwise(curData);
        currentTileData = FormatConversion.convertCharListListToStringList(curData);
        allOrientations.add(new Day20Tile(currentTileData, this.getTileId()));


        curData = FormatConversion.rotateCharListListAnticlockwise(curData);
        currentTileData = FormatConversion.convertCharListListToStringList(curData);
        allOrientations.add(new Day20Tile(currentTileData, this.getTileId()));

        return allOrientations;
    }


    /**
     * Each tile has a one wide border around the actual content which is used to align the pieces.
     * This content is returned from this function after removing the corner of the data
     * @return The content of the tile after removing the surrounding border
     */
    public List<List<Character>> getCharDataWithoutBorder() {
        List<List<Character>> borderlessData = new ArrayList<>();
        for(int y=1; y < this.charData.size()-1; y++) {
            List<Character> row =new ArrayList<>();
            for(int x=1; x < this.charData.get(y).size()-1; x++) {
                row.add(this.charData.get(y).get(x));
            }
            borderlessData.add(row);
        }

        return borderlessData;
    }


}
