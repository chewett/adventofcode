package net.chewett.adventofcode.aoc2023.problems;


import net.chewett.adventofcode.datastructures.Cuboid;
import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.*;

/**
 * --- Day 22: Sand Slabs ---
 * Enough sand has fallen; it can finally filter water for Snow Island.
 *
 * Well, almost.
 *
 * The sand has been falling as large compacted bricks of sand, piling up to form an impressive stack here near the
 * edge of Island Island. In order to make use of the sand to filter water, some of the bricks will need to be broken
 * apart - nay, disintegrated - back into freely flowing sand.
 *
 * The stack is tall enough that you'll have to be careful about choosing which bricks to disintegrate; if you
 * disintegrate the wrong brick, large portions of the stack could topple, which sounds pretty dangerous.
 *
 * The Elves responsible for water filtering operations took a snapshot of the bricks while they were still falling
 * (your puzzle input) which should let you work out which bricks are safe to disintegrate. For example:
 *
 * 1,0,1~1,2,1
 * 0,0,2~2,0,2
 * 0,2,3~2,2,3
 * 0,0,4~0,2,4
 * 2,0,5~2,2,5
 * 0,1,6~2,1,6
 * 1,1,8~1,1,9
 * Each line of text in the snapshot represents the position of a single brick at the time the snapshot was taken.
 * The position is given as two x,y,z coordinates - one for each end of the brick - separated by a tilde (~). Each
 * brick is made up of a single straight line of cubes, and the Elves were even careful to choose a time for the
 * snapshot that had all of the free-falling bricks at integer positions above the ground, so the whole snapshot
 * is aligned to a three-dimensional cube grid.
 *
 * A line like 2,2,2~2,2,2 means that both ends of the brick are at the same coordinate - in other words, that the
 * brick is a single cube.
 *
 * Lines like 0,0,10~1,0,10 or 0,0,10~0,1,10 both represent bricks that are two cubes in volume, both oriented
 * horizontally. The first brick extends in the x direction, while the second brick extends in the y direction.
 *
 * A line like 0,0,1~0,0,10 represents a ten-cube brick which is oriented vertically. One end of the brick is the cube
 * located at 0,0,1, while the other end of the brick is located directly above it at 0,0,10.
 *
 * The ground is at z=0 and is perfectly flat; the lowest z value a brick can have is therefore 1. So, 5,5,1~5,6,1
 * and 0,2,1~0,2,5 are both resting on the ground, but 3,3,2~3,3,3 was above the ground at the time of the snapshot.
 *
 * Because the snapshot was taken while the bricks were still falling, some bricks will still be in the air; you'll
 * need to start by figuring out where they will end up. Bricks are magically stabilized, so they never rotate, even
 * in weird situations like where a long horizontal brick is only supported on one end. Two bricks cannot occupy the
 * same position, so a falling brick will come to rest upon the first other brick it encounters.
 *
 * Here is the same example again, this time with each brick given a letter so it can be marked in diagrams:
 *
 * 1,0,1~1,2,1   <- A
 * 0,0,2~2,0,2   <- B
 * 0,2,3~2,2,3   <- C
 * 0,0,4~0,2,4   <- D
 * 2,0,5~2,2,5   <- E
 * 0,1,6~2,1,6   <- F
 * 1,1,8~1,1,9   <- G
 * At the time of the snapshot, from the side so the x axis goes left to right, these bricks are arranged like this:
 *
 *  x
 * 012
 * .G. 9
 * .G. 8
 * ... 7
 * FFF 6
 * ..E 5 z
 * D.. 4
 * CCC 3
 * BBB 2
 * .A. 1
 * --- 0
 * Rotating the perspective 90 degrees so the y axis now goes left to right, the same bricks are arranged like this:
 *
 *  y
 * 012
 * .G. 9
 * .G. 8
 * ... 7
 * .F. 6
 * EEE 5 z
 * DDD 4
 * ..C 3
 * B.. 2
 * AAA 1
 * --- 0
 * Once all of the bricks fall downward as far as they can go, the stack looks like this, where ? means bricks are
 * hidden behind other bricks at that location:
 *
 *  x
 * 012
 * .G. 6
 * .G. 5
 * FFF 4
 * D.E 3 z
 * ??? 2
 * .A. 1
 * --- 0
 * Again from the side:
 *
 *  y
 * 012
 * .G. 6
 * .G. 5
 * .F. 4
 * ??? 3 z
 * B.C 2
 * AAA 1
 * --- 0
 * Now that all of the bricks have settled, it becomes easier to tell which bricks are supporting which other bricks:
 *
 * Brick A is the only brick supporting bricks B and C.
 * Brick B is one of two bricks supporting brick D and brick E.
 * Brick C is the other brick supporting brick D and brick E.
 * Brick D supports brick F.
 * Brick E also supports brick F.
 * Brick F supports brick G.
 * Brick G isn't supporting any bricks.
 * Your first task is to figure out which bricks are safe to disintegrate. A brick can be safely disintegrated if,
 * after removing it, no other bricks would fall further directly downward. Don't actually disintegrate any bricks
 * - just determine what would happen if, for each brick, only that brick were disintegrated. Bricks can be
 * disintegrated even if they're completely surrounded by other bricks; you can squeeze between bricks if you need to.
 *
 * In this example, the bricks can be disintegrated as follows:
 *
 * Brick A cannot be disintegrated safely; if it were disintegrated, bricks B and C would both fall.
 * Brick B can be disintegrated; the bricks above it (D and E) would still be supported by brick C.
 * Brick C can be disintegrated; the bricks above it (D and E) would still be supported by brick B.
 * Brick D can be disintegrated; the brick above it (F) would still be supported by brick E.
 * Brick E can be disintegrated; the brick above it (F) would still be supported by brick D.
 * Brick F cannot be disintegrated; the brick above it (G) would fall.
 * Brick G can be disintegrated; it does not support any other bricks.
 * So, in this example, 5 bricks can be safely disintegrated.
 *
 * Figure how the blocks will settle based on the snapshot. Once they've settled, consider disintegrating a single
 * brick; how many bricks could be safely chosen as the one to get disintegrated?
 *
 * --- Part Two ---
 * Disintegrating bricks one at a time isn't going to be fast enough. While it might sound dangerous, what you really
 * need is a chain reaction.
 *
 * You'll need to figure out the best brick to disintegrate. For each brick, determine how many other bricks would
 * fall if that brick were disintegrated.
 *
 * Using the same example as above:
 *
 * Disintegrating brick A would cause all 6 other bricks to fall.
 * Disintegrating brick F would cause only 1 other brick, G, to fall.
 * Disintegrating any other brick would cause no other bricks to fall. So, in this example, the sum of the number of
 * other bricks that would fall as a result of disintegrating each brick is 7.
 *
 * For each brick, determine how many other bricks would fall if that brick were disintegrated. What is the sum of
 * the number of other bricks that would fall?
 */
public class Day22 {

    /**
     * Takes the input and simulates moving all the inputs down until they stop
     * @param input List of blocks as string inputs
     * @return List of cuboids after having fallen
     */
    public List<Cuboid> parseInputAndSimulateFalling(List<String> input) {
        List<Cuboid> cubes = new ArrayList<>();
        for(String str : input) {
            String[] parts = str.split("[,~]");
            Cuboid c = new Cuboid(
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[3]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[4]),
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[5])
            );

            cubes.add(c);
        }

        //Sort by z so we can move all of the ones down in order (speeds up the moving down)
        cubes.sort(Comparator.comparingInt(Cuboid::getZ));

        //Tracking the blocks that can no longer be moved down makes it MUCH faster since we don't keep iterating over the same blocks
        Set<String> blocksThatHaveReachedFinalPosition = new HashSet<>();

        //Keep going until we can no longer move any of the cuboids
        boolean cubeMovedAnyDistance = true;
        while(cubeMovedAnyDistance) {
            cubeMovedAnyDistance = false;
            List<Cuboid> newCubes = new ArrayList<>();

            for(Cuboid cubeToTryMoving : cubes) {
                //If we moved a cube then just add this to the list and keep going for now
                if(cubeMovedAnyDistance) {
                    newCubes.add(cubeToTryMoving);
                    continue;
                }

                //If we know we have reached the "final pos" for this cube them we just automatically add it
                if(blocksThatHaveReachedFinalPosition.contains(cubeToTryMoving.toString())) {
                    newCubes.add(cubeToTryMoving);
                    continue;
                }

                //Essentially keep moving it down until we can't.
                boolean hasMovedDown = true;
                Cuboid currentLocation = cubeToTryMoving;
                while(hasMovedDown) {
                    hasMovedDown = false;
                    Cuboid newCubeLocation = currentLocation.moveDown();
                    //Cannot move below 1
                    if(newCubeLocation.getZ() < 1) {
                        break;
                    }
                    boolean cubeMovedThisIteration = true;

                    for(Cuboid otherCube : cubes) {
                        //Ignore myself
                        if(otherCube.equals(cubeToTryMoving)) {
                            continue;
                        }
                        //If the new position intersects a current cube, don't move it
                        if(otherCube.intersects(newCubeLocation)) {
                            cubeMovedThisIteration = false;
                        }
                    }

                    if(cubeMovedThisIteration) {
                        currentLocation = newCubeLocation;
                        hasMovedDown = true;
                        cubeMovedAnyDistance = true;
                    }
                }

                if(cubeMovedAnyDistance) {
                    blocksThatHaveReachedFinalPosition.add(currentLocation.toString());
                }

                newCubes.add(currentLocation);
            }
            cubes = newCubes;
        }
        return cubes;
    }

    /**
     * Work out how many cuboids could be removed without making anything else fall
     * @param input List of cuboids
     * @return Number of cuboids that could be removed without making anything fall
     */
    public long solvePartOne(List<String> input) {
        //Get the "fallen cuboids"
        List<Cuboid> cubes = this.parseInputAndSimulateFalling(input);

        //Work out what is supported by what
        Map<String, Set<String>> xIsSupportedByY = new HashMap<>();
        for(Cuboid c : cubes) {
            xIsSupportedByY.put(c.toString(), new HashSet<>());
            Cuboid oneDown = c.moveDown();

            for(Cuboid collisonsTOCheck : cubes) {
                //Ignore itself
                if(collisonsTOCheck.equals(c)) {
                    continue;
                }

                //If moving down intersects with something, that something supports this cube
                if(oneDown.intersects(collisonsTOCheck)) {
                    xIsSupportedByY.get(c.toString()).add(collisonsTOCheck.toString());
                }
            }
        }

        //Now loop over each cuboid to see if it supports 1 thing, otherwise it can be removed without any issues
        int canRemove = 0;
        for(Cuboid c : cubes) {
            boolean foundNoOtherSUpports = true;

            for(Cuboid otherCubes : cubes) {
                if(c.equals(otherCubes)) {
                    continue;
                }
                Set<String> supportSet = xIsSupportedByY.get(otherCubes.toString());
                if(supportSet.contains(c.toString()) && supportSet.size() == 1) {
                    foundNoOtherSUpports = false;
                }
            }

            if(foundNoOtherSUpports) {
                canRemove++;
            }
        }

        return canRemove;
    }

    /**
     * Works out how many cuboids would fall if each of the cuboids were removed independently
     * @param input List of cuboids
     * @return Number of cuboids that would fall if each of the cuboids were removed independently
     */
    public long solvePartTwo(List<String> input) {
        List<Cuboid> cubes = this.parseInputAndSimulateFalling(input);

        Map<String, Set<String>> xIsSupportedByY = new HashMap<>();
        Map<String, Set<String>> xSupportsY = new HashMap<>();

        for(Cuboid c : cubes) {
            xIsSupportedByY.put(c.toString(), new HashSet<>());
            xSupportsY.put(c.toString(), new HashSet<>());
        }

        //Work out which cubes are touching which cubes above and below
        for(Cuboid c : cubes) {
            Cuboid oneDown = c.moveDown();

            for(Cuboid collisonsTOCheck : cubes) {
                //Ignore itself
                if(collisonsTOCheck.equals(c)) {
                    continue;
                }

                if(oneDown.intersects(collisonsTOCheck)) {
                    xIsSupportedByY.get(c.toString()).add(collisonsTOCheck.toString());
                    xSupportsY.get(collisonsTOCheck.toString()).add(c.toString());
                }
            }
        }

        //Loop over each cuboid and see how many blocks move after removing it
        long blockCountDisintigrated = 0;
        for(Cuboid blockToTryAndDisintigrate : cubes) {

            Map<String, Set<String>> newXIsSupportedByY = new HashMap<>();
            Map<String, Set<String>> newXSupportsY = new HashMap<>();
            //"deep clone" these for modification
            for(Cuboid c : cubes) {
                String strOfC = c.toString();
                newXIsSupportedByY.put(c.toString(), new HashSet<>(xIsSupportedByY.get(strOfC)));
                newXSupportsY.put(c.toString(), new HashSet<>(xSupportsY.get(strOfC)));
            }

            //Remove this block, and then keep iterating over the lists to work out what else falls
            Queue<String> blocksToRemove = new LinkedList<>();
            blocksToRemove.add(blockToTryAndDisintigrate.toString());
            while(!blocksToRemove.isEmpty()) {
                String removingBlock = blocksToRemove.poll();

                //All the blocks this supports
                Set<String> supportedBlocks = newXSupportsY.get(removingBlock);
                for (String supportedBlock : supportedBlocks) {
                    Set<String> b = newXIsSupportedByY.get(supportedBlock);
                    b.remove(removingBlock);
                    //Each time we find out something no longer has any support as increment our drop count and add it to the list to introspect
                    if (b.isEmpty()) {
                        blocksToRemove.add(supportedBlock);
                        blockCountDisintigrated++;
                    }
                }
            }
        }

        return blockCountDisintigrated;
    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 22);

        Day22 d = new Day22();
        long partOne = d.solvePartOne(input);
        System.out.println("How many bricks can be disintegrated safely without shifting the pile " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("How many blocks fall in total as each block is independently removed " + partTwo);
    }
}


