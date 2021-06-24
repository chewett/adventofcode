package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.aoc2020.Day20Tile;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 20: Jurassic Jigsaw ---
 * The high-speed train leaves the forest and quickly carries you south. You can even see a desert in the distance!
 * Since you have some spare time, you might as well see if there was anything interesting in the image the Mythical
 * Information Bureau satellite captured.
 *
 * After decoding the satellite messages, you discover that the data actually contains many small images created by the
 * satellite's camera array. The camera array consists of many cameras; rather than produce a single square image, they
 * produce many smaller square image tiles that need to be reassembled back into a single image.
 *
 * Each camera in the camera array returns a single monochrome image tile with a random unique ID number. The tiles
 * (your puzzle input) arrived in a random order.
 *
 * Worse yet, the camera array appears to be malfunctioning: each image tile has been rotated and flipped to a random
 * orientation. Your first task is to reassemble the original image by orienting the tiles so they fit together.
 *
 * To show how the tiles should be reassembled, each tile's image data includes a border that should line up exactly
 * with its adjacent tiles. All tiles have this border, and the border lines up exactly when the tiles are both oriented
 * correctly. Tiles at the edge of the image also have this border, but the outermost edges won't line up with any other
 * tiles.
 *
 * For example, suppose you have the following nine tiles:
 *
 * Tile 2311:
 * ..##.#..#.
 * ##..#.....
 * #...##..#.
 * ####.#...#
 * ##.##.###.
 * ##...#.###
 * .#.#.#..##
 * ..#....#..
 * ###...#.#.
 * ..###..###
 *
 * Tile 1951:
 * #.##...##.
 * #.####...#
 * .....#..##
 * #...######
 * .##.#....#
 * .###.#####
 * ###.##.##.
 * .###....#.
 * ..#.#..#.#
 * #...##.#..
 *
 * Tile 1171:
 * ####...##.
 * #..##.#..#
 * ##.#..#.#.
 * .###.####.
 * ..###.####
 * .##....##.
 * .#...####.
 * #.##.####.
 * ####..#...
 * .....##...
 *
 * Tile 1427:
 * ###.##.#..
 * .#..#.##..
 * .#.##.#..#
 * #.#.#.##.#
 * ....#...##
 * ...##..##.
 * ...#.#####
 * .#.####.#.
 * ..#..###.#
 * ..##.#..#.
 *
 * Tile 1489:
 * ##.#.#....
 * ..##...#..
 * .##..##...
 * ..#...#...
 * #####...#.
 * #..#.#.#.#
 * ...#.#.#..
 * ##.#...##.
 * ..##.##.##
 * ###.##.#..
 *
 * Tile 2473:
 * #....####.
 * #..#.##...
 * #.##..#...
 * ######.#.#
 * .#...#.#.#
 * .#########
 * .###.#..#.
 * ########.#
 * ##...##.#.
 * ..###.#.#.
 *
 * Tile 2971:
 * ..#.#....#
 * #...###...
 * #.#.###...
 * ##.##..#..
 * .#####..##
 * .#..####.#
 * #..#.#..#.
 * ..####.###
 * ..#.#.###.
 * ...#.#.#.#
 *
 * Tile 2729:
 * ...#.#.#.#
 * ####.#....
 * ..#.#.....
 * ....#..#.#
 * .##..##.#.
 * .#.####...
 * ####.#.#..
 * ##.####...
 * ##..#.##..
 * #.##...##.
 *
 * Tile 3079:
 * #.#.#####.
 * .#..######
 * ..#.......
 * ######....
 * ####.#..#.
 * .#...#.##.
 * #.#####.##
 * ..#.###...
 * ..#.......
 * ..#.###...
 * By rotating, flipping, and rearranging them, you can find a square arrangement that causes all adjacent borders to
 * line up:
 *
 * #...##.#.. ..###..### #.#.#####.
 * ..#.#..#.# ###...#.#. .#..######
 * .###....#. ..#....#.. ..#.......
 * ###.##.##. .#.#.#..## ######....
 * .###.##### ##...#.### ####.#..#.
 * .##.#....# ##.##.###. .#...#.##.
 * #...###### ####.#...# #.#####.##
 * .....#..## #...##..#. ..#.###...
 * #.####...# ##..#..... ..#.......
 * #.##...##. ..##.#..#. ..#.###...
 *
 * #.##...##. ..##.#..#. ..#.###...
 * ##..#.##.. ..#..###.# ##.##....#
 * ##.####... .#.####.#. ..#.###..#
 * ####.#.#.. ...#.##### ###.#..###
 * .#.####... ...##..##. .######.##
 * .##..##.#. ....#...## #.#.#.#...
 * ....#..#.# #.#.#.##.# #.###.###.
 * ..#.#..... .#.##.#..# #.###.##..
 * ####.#.... .#..#.##.. .######...
 * ...#.#.#.# ###.##.#.. .##...####
 *
 * ...#.#.#.# ###.##.#.. .##...####
 * ..#.#.###. ..##.##.## #..#.##..#
 * ..####.### ##.#...##. .#.#..#.##
 * #..#.#..#. ...#.#.#.. .####.###.
 * .#..####.# #..#.#.#.# ####.###..
 * .#####..## #####...#. .##....##.
 * ##.##..#.. ..#...#... .####...#.
 * #.#.###... .##..##... .####.##.#
 * #...###... ..##...#.. ...#..####
 * ..#.#....# ##.#.#.... ...##.....
 * For reference, the IDs of the above tiles are:
 *
 * 1951    2311    3079
 * 2729    1427    2473
 * 2971    1489    1171
 * To check that you've assembled the image correctly, multiply the IDs of the four corner tiles together. If you do
 * this with the assembled tiles from the example above, you get 1951 * 3079 * 2971 * 1171 = 20899048083289.
 *
 * Assemble the tiles into an image. What do you get if you multiply together the IDs of the four corner tiles?
 *
 * --- Part Two ---
 * Now, you're ready to check the image for sea monsters.
 *
 * The borders of each tile are not part of the actual image; start by removing them.
 *
 * In the example above, the tiles become:
 *
 * .#.#..#. ##...#.# #..#####
 * ###....# .#....#. .#......
 * ##.##.## #.#.#..# #####...
 * ###.#### #...#.## ###.#..#
 * ##.#.... #.##.### #...#.##
 * ...##### ###.#... .#####.#
 * ....#..# ...##..# .#.###..
 * .####... #..#.... .#......
 *
 * #..#.##. .#..###. #.##....
 * #.####.. #.####.# .#.###..
 * ###.#.#. ..#.#### ##.#..##
 * #.####.. ..##..## ######.#
 * ##..##.# ...#...# .#.#.#..
 * ...#..#. .#.#.##. .###.###
 * .#.#.... #.##.#.. .###.##.
 * ###.#... #..#.##. ######..
 *
 * .#.#.### .##.##.# ..#.##..
 * .####.## #.#...## #.#..#.#
 * ..#.#..# ..#.#.#. ####.###
 * #..####. ..#.#.#. ###.###.
 * #####..# ####...# ##....##
 * #.##..#. .#...#.. ####...#
 * .#.###.. ##..##.. ####.##.
 * ...###.. .##...#. ..#..###
 * Remove the gaps to form the actual image:
 *
 * .#.#..#.##...#.##..#####
 * ###....#.#....#..#......
 * ##.##.###.#.#..######...
 * ###.#####...#.#####.#..#
 * ##.#....#.##.####...#.##
 * ...########.#....#####.#
 * ....#..#...##..#.#.###..
 * .####...#..#.....#......
 * #..#.##..#..###.#.##....
 * #.####..#.####.#.#.###..
 * ###.#.#...#.######.#..##
 * #.####....##..########.#
 * ##..##.#...#...#.#.#.#..
 * ...#..#..#.#.##..###.###
 * .#.#....#.##.#...###.##.
 * ###.#...#..#.##.######..
 * .#.#.###.##.##.#..#.##..
 * .####.###.#...###.#..#.#
 * ..#.#..#..#.#.#.####.###
 * #..####...#.#.#.###.###.
 * #####..#####...###....##
 * #.##..#..#...#..####...#
 * .#.###..##..##..####.##.
 * ...###...##...#...#..###
 * Now, you're ready to search for sea monsters! Because your image is monochrome, a sea monster will look like this:
 *
 *                   #
 * #    ##    ##    ###
 *  #  #  #  #  #  #
 * When looking for this pattern in the image, the spaces can be anything; only the # need to match. Also, you might
 * need to rotate or flip your image before it's oriented correctly to find sea monsters. In the above image, after
 * flipping and rotating it to the appropriate orientation, there are two sea monsters (marked with O):
 *
 * .####...#####..#...###..
 * #####..#..#.#.####..#.#.
 * .#.#...#.###...#.##.O#..
 * #.O.##.OO#.#.OO.##.OOO##
 * ..#O.#O#.O##O..O.#O##.##
 * ...#.#..##.##...#..#..##
 * #.##.#..#.#..#..##.#.#..
 * .###.##.....#...###.#...
 * #.####.#.#....##.#..#.#.
 * ##...#..#....#..#...####
 * ..#.##...###..#.#####..#
 * ....#.##.#.#####....#...
 * ..##.##.###.....#.##..#.
 * #...#...###..####....##.
 * .#.##...#.##.#.#.###...#
 * #.###.#..####...##..#...
 * #.###...#.##...#.##O###.
 * .O##.#OO.###OO##..OOO##.
 * ..O#.O..O..O.#O##O##.###
 * #.#..##.########..#..##.
 * #.#####..#.#...##..#....
 * #....##..#.#########..##
 * #...#.....#..##...###.##
 * #..###....##.#...##.##.#
 * Determine how rough the waters are in the sea monsters' habitat by counting the number of # that are not part of a
 * sea monster. In the above example, the habitat's water roughness is 273.
 */
public class Day20 {

    /**
     * Given a set of tiles this attempts to find the four corner tiles and then return the multiplication of the four
     * @param tilesInput A list of strings representing the various tiles we want to find the corners for
     * @return The value of the four corner tiles multiplied together
     */
    public long solvePartOne(List<String> tilesInput) {

        //Process the tiles into objects
        List<Day20Tile> tiles = new ArrayList<>();
        List<String> currentTileData = new ArrayList<>();
        int currentTileName = 0;
        for(String s : tilesInput) {
            if(s.contains("Tile ")) {
                String tileRow = s.split(" ")[1];
                currentTileName = Integer.parseInt(tileRow.substring(0, tileRow.length()-1));
            }else if(s.equals("")) {
                tiles.add(new Day20Tile(currentTileData, currentTileName));
                currentTileData = new ArrayList<>();
            }else{
                currentTileData.add(s);
            }
        }
        tiles.add(new Day20Tile(currentTileData, currentTileName));

        long cornerTileIdsMultiplied = 1;

        //Loop over every tile
        for(Day20Tile tile : tiles) {
            int tileId = tile.getTileId();
            List<Integer> cornerIds = tile.getEdgeIds();

            //A corner tile will have four matches (two matched sides with two orientations) so search for them
            int cornersMatching = 0;
            for(Day20Tile tileToCheckAgainst: tiles) {
                if(tileId != tileToCheckAgainst.getTileId()) {
                    List<Integer> cornersToCheck = tileToCheckAgainst.getEdgeIds();
                    for(int cornerId : cornersToCheck) {
                        if(cornerIds.contains(cornerId)) {
                            cornersMatching++;
                        }
                    }
                }
            }

            //If we have found a corner tile, multiply the sum we are keeping track of
            if(cornersMatching == 4) {
                cornerTileIdsMultiplied *= tileId;
            }
        }

        //Once all the tiles have been processed, return the value
        return cornerTileIdsMultiplied;
    }

    /**
     * Given a List of List of characters and a position, this will attempt to determine if there is a sea monster there
     * @param image List of List of characters representing the image
     * @param x X position to start checking for a sea monster
     * @param y Y position to start checking for a sea monster
     * @return Boolean representing whether a sea monster has been found at this X and Y position
     */
    private boolean isSeaMonsterAt(List<List<Character>> image, int x, int y) {
        //If the X/Y position is too far along the image, then it can't be a sea monster
        if(x + 19 >= image.size()) {
            return false;
        }
        if(y + 2 >= image.get(0).size()) {
            return false;
        }

        //A sea monster is represented by a series of rough waves, in a specific pattern. This is the specific pattern
        return (
                image.get(y).get(x+18) == '#' &&

                image.get(y+1).get(x) == '#' &&
                image.get(y+1).get(x+5) == '#' &&
                image.get(y+1).get(x+6) == '#' &&
                image.get(y+1).get(x+11) == '#' &&
                image.get(y+1).get(x+12) == '#' &&
                image.get(y+1).get(x+17) == '#' &&
                image.get(y+1).get(x+18) == '#' &&
                image.get(y+1).get(x+19) == '#' &&

                image.get(y+2).get(x+1) == '#' &&
                image.get(y+2).get(x+4) == '#' &&
                image.get(y+2).get(x+7) == '#' &&
                image.get(y+2).get(x+10) == '#' &&
                image.get(y+2).get(x+13) == '#' &&
                image.get(y+2).get(x+16) == '#'
        );
    }

    /**
     * Given a set of tiles this attempts to find the how rough the sea monsters habitat is. This first needs to position
     * all the pieces, and then determine what is rough sea and what is a sea monster
     * @param tilesInput A list of strings representing the various tiles we want to find the corners for
     * @return A long representing the roughness of the sea, counted by each rough patch that isn't a sea monster
     */
    public long solvePartTwo(List<String> tilesInput) {
        //Parse the tiles
        List<Day20Tile> tiles = new ArrayList<>();
        List<String> currentTileData = new ArrayList<>();
        int currentTileName = 0;
        for(String s : tilesInput) {
            if(s.contains("Tile ")) {
                String tileRow = s.split(" ")[1];
                currentTileName = Integer.parseInt(tileRow.substring(0, tileRow.length()-1));
            }else if(s.equals("")) {
                tiles.add(new Day20Tile(currentTileData, currentTileName));
                currentTileData = new ArrayList<>();
            }else{
                currentTileData.add(s);
            }
        }
        tiles.add(new Day20Tile(currentTileData, currentTileName));

        Day20Tile startingCorner = null;
        //First we search for an initial corner piece
        for(Day20Tile tile : tiles) {
            int tileId = tile.getTileId();
            List<Integer> cornerIds = tile.getEdgeIds();

            int cornersMatching = 0;

            for(Day20Tile tileToCheckAgainst: tiles) {
                if(tileId != tileToCheckAgainst.getTileId()) {
                    List<Integer> cornersToCheck = tileToCheckAgainst.getEdgeIds();
                    for(int cornerId : cornersToCheck) {
                        if(cornerIds.contains(cornerId)) {
                            cornersMatching++;
                        }
                    }
                }
            }

            if(cornersMatching == 4) {
                startingCorner = tile;
                break;
            }
        }

        if(startingCorner == null) {
            throw new RuntimeException("Starting tile not found, end program");
        }

        //Then we get every orientation of every tile
        List<Day20Tile> orientations = startingCorner.getAllOrientations();

        //And then create a list that doesn't include teh starting tile
        List<Integer> allCorners = new ArrayList<>();
        for(Day20Tile tile : tiles) {
            int tileId = tile.getTileId();
            if(tileId != startingCorner.getTileId()){
                allCorners.addAll(tile.getEdgeIds());
            }
        }

        //Then we orient the starting tile so that it fits in the top left position of our puzzle
        startingCorner = null;
        for(Day20Tile t : orientations) {
            int topId = t.getTopSideInt();
            int leftId = t.getLeftSideInt();

            if(!allCorners.contains(topId) && !allCorners.contains(leftId)) {
                startingCorner = t;
            }
        }

        if(startingCorner == null) {
            throw new RuntimeException("Starting tile not found, ending program");
        }

        //Now we set up the array of tiles as we will need to position them in order
        int numberOfTilesInEachDimension = (int)Math.sqrt(tiles.size());
        Day20Tile[][] arr = new Day20Tile[numberOfTilesInEachDimension][numberOfTilesInEachDimension];

        List<Day20Tile> remainingTiles = new ArrayList<>();
        for(Day20Tile tile : tiles) {
            if(tile.getTileId() != startingCorner.getTileId()) {
                remainingTiles.addAll(tile.getAllOrientationsWithFlips());
            }
        }

        int curX = 1;
        int curY = 0;
        arr[0][0] = startingCorner;
        Day20Tile currentComparisonTile = startingCorner;
        //We go left to right, top to bottom attempting to find a piece that fits with our current piece.
        while(remainingTiles.size() > 0) {
            Day20Tile newlyFoundTile = null;
            for(Day20Tile t : remainingTiles) {
                //If curX is zero then we are dealing with a piece along the top left, and therefore must be matching
                //The top of the new tile with the bottom of the "Known" tile.
                //Because we have all the orientations we don't need to worry about rotating/flipping anything
                if(curX == 0) {
                    if(t.getTopSideInt() == currentComparisonTile.getBottomSideInt()) {
                        newlyFoundTile = t;
                        break;
                    }
                //If curX isn't zero then we are matching the left of the current tile with the right of the new potential tile
                }else{
                    if(t.getLeftSideInt() == currentComparisonTile.getRightSideInt()) {
                        newlyFoundTile = t;
                        break;
                    }
                }
            }

            if(newlyFoundTile == null) {
                throw new RuntimeException("I have been unable to find an appropiate tile");
            }

            //Once we have found a tile that we can place we remove all the orientations of that tile from the list we are processing
            List<Day20Tile> tilesToRemove = new ArrayList<>();
            for(Day20Tile t : remainingTiles) {
                if(t.getTileId() == newlyFoundTile.getTileId()) {
                    tilesToRemove.add(t);
                }
            }
            remainingTiles.removeAll(tilesToRemove);

            //And then place the tile in our grid
            arr[curY][curX] = newlyFoundTile;
            currentComparisonTile = newlyFoundTile;
            curX++;
            //If we have reached the end of the array, we move onto the next line
            if(curX >= arr[curY].length) {
                curX = 0;
                curY++;
                //Make sure to compare the "above" tile and not the left one like normal
                currentComparisonTile = arr[curY-1][0];
            }
        }

        //Now we have formed our grid we can create a new array by removing the pieces
        List<List<List<List<Character>>>> charListsArr = new ArrayList<>();
        for(int y = 0; y < arr.length; y++) {
            List<List<List<Character>>> characterListListRow = new ArrayList<>();
            for(int x = 0; x < arr[y].length; x++) {
                characterListListRow.add(arr[y][x].getCharDataWithoutBorder());
            }
            charListsArr.add(characterListListRow);
        }

        //The final image is created here which represents the finished puzzle in one List of List of Characters
        List<List<Character>> finalImage = new ArrayList<>();
        for(int yArray = 0; yArray < charListsArr.size(); yArray++) {
            for(int yImage = 0; yImage < charListsArr.get(0).get(0).size(); yImage++) {
                List<Character> rowData = new ArrayList<>();
                for(int xArray = 0; xArray < charListsArr.get(yArray).size(); xArray++) {
                    for(int xImage = 0; xImage < charListsArr.get(0).get(0).get(0).size(); xImage++) {
                        rowData.add(charListsArr.get(yArray).get(xArray).get(yImage).get(xImage));
                    }
                }
                finalImage.add(rowData);
            }
        }


        //Since we don't actually know the orientation of the image, we creat all four orientations, and then flip them
        List<List<List<Character>>> allFinalImageOrientations = new ArrayList<>();
        allFinalImageOrientations.add(finalImage);
        finalImage = FormatConversion.rotateCharListListAnticlockwise(finalImage);
        allFinalImageOrientations.add(finalImage);
        finalImage = FormatConversion.rotateCharListListAnticlockwise(finalImage);
        allFinalImageOrientations.add(finalImage);
        finalImage = FormatConversion.rotateCharListListAnticlockwise(finalImage);
        allFinalImageOrientations.add(finalImage);

        //Here we also flip them
        List<List<List<Character>>> allFlippedImages = new ArrayList<>();
        for(List<List<Character>> cs : allFinalImageOrientations) {
            allFlippedImages.add(FormatConversion.flipCharListList(cs));
        }
        allFinalImageOrientations.addAll(allFlippedImages);

        //Since we define the sea monster in one "form" we need to check every orientation and flip
        for(List<List<Character>> imageToCheck : allFinalImageOrientations) {
            int seaMonstersFound = 0;
            for(int y = 0; y < imageToCheck.size(); y++) {
                for(int x = 0; x < imageToCheck.get(y).size(); x++) {
                    boolean seamonster = this.isSeaMonsterAt(imageToCheck, x, y);
                    if(seamonster) {
                        seaMonstersFound++;
                    }
                }
            }

            //If we have found a sea monster, this must be the correct orientation!
            if(seaMonstersFound > 0) {
                //Now we can count the rough sea elements
                long allSeaUnitsFound = 0;
                for(int y = 0; y < imageToCheck.size(); y++) {
                    for (int x = 0; x < imageToCheck.get(y).size(); x++) {
                        if(imageToCheck.get(y).get(x) == '#') {
                            allSeaUnitsFound++;
                        }
                    }
                }
                //And then finally remove the count of the sea monsters (sea monsters are always 15 squares)
                return allSeaUnitsFound - (15 * seaMonstersFound);
            }
        }

        //Something went really wrong here, return this as a "bad" value
        return -1;
    }

    public static void main(String[] args) {
        List<String> rows = ProblemLoader.loadProblemIntoStringArray(2020, 20);
        Day20 d = new Day20();

        long p1 = d.solvePartOne(rows);
        System.out.println("The ID's of the four corner tiles multiplied together is " + p1);
        long p2 = d.solvePartTwo(rows);
        System.out.println("The roughness of the water is "+ p2 + " in the sea creatures habitat");

    }

}
