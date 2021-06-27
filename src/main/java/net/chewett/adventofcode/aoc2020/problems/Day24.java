package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * --- Day 24: Lobby Layout ---
 * Your raft makes it to the tropical island; it turns out that the small crab was an excellent navigator. You make your
 * way to the resort.
 *
 * As you enter the lobby, you discover a small problem: the floor is being renovated. You can't even reach the check-in
 * desk until they've finished installing the new tile floor.
 *
 * The tiles are all hexagonal; they need to be arranged in a hex grid with a very specific color pattern. Not in the
 * mood to wait, you offer to help figure out the pattern.
 *
 * The tiles are all white on one side and black on the other. They start with the white side facing up. The lobby is
 * large enough to fit whatever pattern might need to appear there.
 *
 * A member of the renovation crew gives you a list of the tiles that need to be flipped over (your puzzle input). Each
 * line in the list identifies a single tile that needs to be flipped by giving a series of steps starting from a
 * reference tile in the very center of the room. (Every line starts from the same reference tile.)
 *
 * Because the tiles are hexagonal, every tile has six neighbors: east, southeast, southwest, west, northwest, and
 * northeast. These directions are given in your list, respectively, as e, se, sw, w, nw, and ne. A tile is identified
 * by a series of these directions with no delimiters; for example, esenee identifies the tile you land on if you start
 * at the reference tile and then move one tile east, one tile southeast, one tile northeast, and one tile east.
 *
 * Each time a tile is identified, it flips from white to black or from black to white. Tiles might be flipped more than
 * once. For example, a line like esew flips a tile immediately adjacent to the reference tile, and a line like nwwswee
 * flips the reference tile itself.
 *
 * Here is a larger example:
 *
 * sesenwnenenewseeswwswswwnenewsewsw
 * neeenesenwnwwswnenewnwwsewnenwseswesw
 * seswneswswsenwwnwse
 * nwnwneseeswswnenewneswwnewseswneseene
 * swweswneswnenwsewnwneneseenw
 * eesenwseswswnenwswnwnwsewwnwsene
 * sewnenenenesenwsewnenwwwse
 * wenwwweseeeweswwwnwwe
 * wsweesenenewnwwnwsenewsenwwsesesenwne
 * neeswseenwwswnwswswnw
 * nenwswwsewswnenenewsenwsenwnesesenew
 * enewnwewneswsewnwswenweswnenwsenwsw
 * sweneswneswneneenwnewenewwneswswnese
 * swwesenesewenwneswnwwneseswwne
 * enesenwswwswneneswsenwnewswseenwsese
 * wnwnesenesenenwwnenwsewesewsesesew
 * nenewswnwewswnenesenwnesewesw
 * eneswnwswnwsenenwnwnwwseeswneewsenese
 * neswnwewnwnwseenwseesewsenwsweewe
 * wseweeenwnesenwwwswnew
 * In the above example, 10 tiles are flipped once (to black), and 5 more are flipped twice (to black, then back to
 * white). After all of these instructions have been followed, a total of 10 tiles are black.
 *
 * Go through the renovation crew's list and determine which tiles they need to flip. After all of the instructions have
 * been followed, how many tiles are left with the black side up?
 *
 * --- Part Two ---
 * The tile floor in the lobby is meant to be a living art exhibit. Every day, the tiles are all flipped according to
 * the following rules:
 *
 * Any black tile with zero or more than 2 black tiles immediately adjacent to it is flipped to white.
 * Any white tile with exactly 2 black tiles immediately adjacent to it is flipped to black.
 * Here, tiles immediately adjacent means the six tiles directly touching the tile in question.
 *
 * The rules are applied simultaneously to every tile; put another way, it is first determined which tiles need to be
 * flipped, then they are all flipped at the same time.
 *
 * In the above example, the number of black tiles that are facing up after the given number of days has passed is as
 * follows:
 *
 * Day 1: 15
 * Day 2: 12
 * Day 3: 25
 * Day 4: 14
 * Day 5: 23
 * Day 6: 28
 * Day 7: 41
 * Day 8: 37
 * Day 9: 49
 * Day 10: 37
 *
 * Day 20: 132
 * Day 30: 259
 * Day 40: 406
 * Day 50: 566
 * Day 60: 788
 * Day 70: 1106
 * Day 80: 1373
 * Day 90: 1844
 * Day 100: 2208
 * After executing this process a total of 100 times, there would be 2208 black tiles facing up.
 *
 * How many tiles will be black after 100 days?
 */
public class Day24 {

    /**
     * Returns true if the current tile is black and false if not
     *
     * This also creates the y position given in the map which means it can be used more easily going forward
     * @param x X value to check
     * @param y V value to check
     * @param blackTiles Object to check in
     * @return True if the tile is black, false if not
     */
    private boolean isTileBlack(int x, int y, Map<Integer, Map<Integer, Boolean>> blackTiles ) {
        if(!blackTiles.containsKey(y)) {
            blackTiles.put(y, new HashMap<>());
        }

        return blackTiles.get(y).getOrDefault(x, false);
    }

    public List<String> parseInstructionsIntoEachPiece(String instructionString) {
        List<String> parsedInstructions = new ArrayList<>();

        for(int i = 0; i < instructionString.length(); i++) {
            char curChar = instructionString.charAt(i);
            if(curChar == 'e' || curChar == 'w') {
                parsedInstructions.add(""+curChar);
            }else{
                //If the instruction is not starting with e or w, then its made of two characters so pick the next two
                parsedInstructions.add("" + curChar + instructionString.charAt(i+1));
                i++;
            }
        }

        return parsedInstructions;
    }

    /**
     * Perform a list of instructions and save the results to the blackTile map provided
     * @param instructions List of instructions to perform
     * @param blackTiles The black tile map to save the results to
     */
    public void performInstructionsOnTileset(List<String> instructions, Map<Integer, Map<Integer, Boolean>> blackTiles) {
        int curX = 0;
        int curY = 0;
        for(String instToRun : instructions) {
            if(instToRun.equals("e")) {
                curX +=2;
            }else if(instToRun.equals("se")) {
                curY -= 1;
                curX += 1;
            }else if(instToRun.equals("sw")) {
                curY -= 1;
                curX -= 1;
            }else if(instToRun.equals("w")) {
                curX -= 2;
            }else if(instToRun.equals("nw")) {
                curY += 1;
                curX -= 1;
            }else if(instToRun.equals("ne")) {
                curY += 1;
                curX += 1;
            }else{
                throw new RuntimeException("Error during parsing instruction");
            }
        }

        //Now we know what tile we have ended on, flip it
        boolean currentlyBlack = this.isTileBlack(curX, curY, blackTiles);
        currentlyBlack = !currentlyBlack;
        blackTiles.get(curY).put(curX, currentlyBlack);
    }

    /**
     * Processes the tiles and returns the number of black tiles that have been found after checking the entire floor
     * @param instructions Instructions for the flipping robot
     * @return Number of black tiles after processing all tiles
     */
    public long solvePartOne(List<String> instructions) {
        Map<Integer, Map<Integer, Boolean>> blackTiles = new HashMap<>();

        //Split and perform the instructions to flip each tile
        for(String instr : instructions) {
            List<String> parsedInstructions = this.parseInstructionsIntoEachPiece(instr);
            this.performInstructionsOnTileset(parsedInstructions, blackTiles);
        }

        //Now count all the tiles
        long count = 0;
        for(Map.Entry<Integer, Map<Integer, Boolean>> e : blackTiles.entrySet()) {
            for(Map.Entry<Integer, Boolean> e2 : e.getValue().entrySet()) {
                if(e2.getValue()) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Flip the tiles as in part one, and then perform the daily flipping to work out how many tiles will be left
     * at the end of the artistic process
     * @param instructions List of flipping instructions to perform
     * @return Black tiles face up at the end of the artistic process
     */
    public long solvePartTwo(List<String> instructions) {
        Map<Integer, Map<Integer, Boolean>> blackTiles = new HashMap<>();

        //Split and perform the instructions to flip each tile
        for(String instr : instructions) {
            List<String> parsedInstructions = this.parseInstructionsIntoEachPiece(instr);
            this.performInstructionsOnTileset(parsedInstructions, blackTiles);
        }

        //Now do flipping
        for(int days = 0; days < 100; days++) {
            Map<Integer, Map<Integer, Boolean>> newBlackTiles = new HashMap<>();
            //Create a set of tiles to check
            Set<Point> tilesToCheck = new HashSet<>();

            //This finds all the tiles and surrounding tiles to check
            for(Map.Entry<Integer, Map<Integer, Boolean>> e : blackTiles.entrySet()) {
                for (Map.Entry<Integer, Boolean> e2 : e.getValue().entrySet()) {
                    if(e2.getValue()) {
                        int curY = e.getKey();
                        int curX = e2.getKey();

                        tilesToCheck.add(new Point(curX, curY));
                        tilesToCheck.add(new Point((curX+2), curY));
                        tilesToCheck.add(new Point((curX-2), curY));
                        tilesToCheck.add(new Point((curX+1), (curY+1)));
                        tilesToCheck.add(new Point((curX-1), (curY+1)));
                        tilesToCheck.add(new Point((curX+1), (curY-1)));
                        tilesToCheck.add(new Point((curX-1), (curY-1)));
                    }
                }
            }

            //Now loop over every point to check
            for(Point tileToCheck : tilesToCheck) {
                int tY = (int)tileToCheck.getY();
                int tX = (int)tileToCheck.getX();

                int blackTilesCount = 0;
                if(this.isTileBlack(tX+2, tY, blackTiles)) { blackTilesCount++; }
                if(this.isTileBlack(tX-2, tY, blackTiles)) { blackTilesCount++; }

                if(this.isTileBlack(tX+1, tY+1, blackTiles)) { blackTilesCount++; }
                if(this.isTileBlack(tX-1, tY+1, blackTiles)) { blackTilesCount++; }

                if(this.isTileBlack(tX+1, tY-1, blackTiles)) { blackTilesCount++; }
                if(this.isTileBlack(tX-1, tY-1, blackTiles)) { blackTilesCount++; }

                boolean newTileIsBlack = this.isTileBlack(tX, tY, blackTiles);

                if(newTileIsBlack) {
                    //Current colour is black
                    if(blackTilesCount == 0 || blackTilesCount > 2) {
                        newTileIsBlack = false;
                    }
                }else{
                    //Current colour is white
                    if(blackTilesCount == 2) {
                        newTileIsBlack = true;
                    }
                }

                //This is useful to instead of check the tile to see if its black, just to ensure the X Y values are inserted into the List List Structure
                this.isTileBlack(tX, tY, newBlackTiles);
                newBlackTiles.get(tY).put(tX, newTileIsBlack);

            }

            blackTiles = newBlackTiles;
        }


        //Now just count up every black tile and return the result
        long count = 0;
        for(Map.Entry<Integer, Map<Integer, Boolean>> e : blackTiles.entrySet()) {
            for(Map.Entry<Integer, Boolean> e2 : e.getValue().entrySet()) {
                if(e2.getValue()) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Day24 d = new Day24();
        List<String> instructions = ProblemLoader.loadProblemIntoStringArray(2020, 24);
        long p1 = d.solvePartOne(instructions);
        System.out.println("Number of black tiles after processing the instructions " + p1);
        long p2 = d.solvePartTwo(instructions);
        System.out.println("Number of black tiles at the end of the art exhibition days " + p2);
    }


}
