package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.datastructures.Pair;
import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.*;

/**
 * --- Day 9: Disk Fragmenter ---
 * Another push of the button leaves you in the familiar hallways of some friendly amphipods! Good thing you each
 * somehow got your own personal mini submarine. The Historians jet away in search of the Chief, mostly by driving
 * directly into walls.
 *
 * While The Historians quickly figure out how to pilot these things, you notice an amphipod in the corner struggling
 * with his computer. He's trying to make more contiguous free space by compacting all of the files, but his program
 * isn't working; you offer to help.
 *
 * He shows you the disk map (your puzzle input) he's already generated. For example:
 *
 * 2333133121414131402
 * The disk map uses a dense format to represent the layout of files and free space on the disk. The digits alternate
 * between indicating the length of a file and the length of free space.
 *
 * So, a disk map like 12345 would represent a one-block file, two blocks of free space, a three-block file, four
 * blocks of free space, and then a five-block file. A disk map like 90909 would represent three nine-block files in
 * a row (with no free space between them).
 *
 * Each file on disk also has an ID number based on the order of the files as they appear before they are rearranged,
 * starting with ID 0. So, the disk map 12345 has three files: a one-block file with ID 0, a three-block file with
 * ID 1, and a five-block file with ID 2. Using one character for each block where digits are the file ID and . is
 * free space, the disk map 12345 represents these individual blocks:
 *
 * 0..111....22222
 * The first example above, 2333133121414131402, represents these individual blocks:
 *
 * 00...111...2...333.44.5555.6666.777.888899
 * The amphipod would like to move file blocks one at a time from the end of the disk to the leftmost free space block
 * (until there are no gaps remaining between file blocks). For the disk map 12345, the process looks like this:
 *
 * 0..111....22222
 * 02.111....2222.
 * 022111....222..
 * 0221112...22...
 * 02211122..2....
 * 022111222......
 * The first example requires a few more steps:
 *
 * 00...111...2...333.44.5555.6666.777.888899
 * 009..111...2...333.44.5555.6666.777.88889.
 * 0099.111...2...333.44.5555.6666.777.8888..
 * 00998111...2...333.44.5555.6666.777.888...
 * 009981118..2...333.44.5555.6666.777.88....
 * 0099811188.2...333.44.5555.6666.777.8.....
 * 009981118882...333.44.5555.6666.777.......
 * 0099811188827..333.44.5555.6666.77........
 * 00998111888277.333.44.5555.6666.7.........
 * 009981118882777333.44.5555.6666...........
 * 009981118882777333644.5555.666............
 * 00998111888277733364465555.66.............
 * 0099811188827773336446555566..............
 * The final step of this file-compacting process is to update the filesystem checksum. To calculate the checksum,
 * add up the result of multiplying each of these blocks' position with the file ID number it contains. The leftmost
 * block is in position 0. If a block contains free space, skip it instead.
 *
 * Continuing the first example, the first few blocks' position multiplied by its file ID number are
 * 0 * 0 = 0, 1 * 0 = 0, 2 * 9 = 18, 3 * 9 = 27, 4 * 8 = 32, and so on. In this example, the checksum is the sum of
 * these, 1928.
 *
 * Compact the amphipod's hard drive using the process he requested. What is the resulting filesystem checksum?
 * (Be careful copy/pasting the input for this puzzle; it is a single, very long line.)
 *
 * --- Part Two ---
 * Upon completion, two things immediately become clear. First, the disk definitely has a lot more contiguous free
 * space, just like the amphipod hoped. Second, the computer is running much more slowly! Maybe introducing all of that
 * file system fragmentation was a bad idea?
 *
 * The eager amphipod already has a new plan: rather than move individual blocks, he'd like to try compacting the
 * files on his disk by moving whole files instead.
 *
 * This time, attempt to move whole files to the leftmost span of free space blocks that could fit the file. Attempt
 * to move each file exactly once in order of decreasing file ID number starting with the file with the highest file
 * ID number. If there is no span of free space to the left of a file that is large enough to fit the file, the file
 * does not move.
 *
 * The first example from above now proceeds differently:
 *
 * 00...111...2...333.44.5555.6666.777.888899
 * 0099.111...2...333.44.5555.6666.777.8888..
 * 0099.1117772...333.44.5555.6666.....8888..
 * 0099.111777244.333....5555.6666.....8888..
 * 00992111777.44.333....5555.6666.....8888..
 * The process of updating the filesystem checksum is the same; now, this example's checksum would be 2858.
 *
 * Start over, now compacting the amphipod's hard drive using this new method instead. What is the resulting filesystem
 * checksum?
 */
public class Day9 {

    /**
     * Exampands the "short form" block map into a much longer form (not super efficient)
     * @param input Short form input
     * @return Long form block list
     */
    public LinkedList<Integer> createBlockMap(String input) {
        LinkedList<Integer> blocks = new LinkedList<>();

        int currentIndex = 0;
        boolean currentlyABlock = true;
        for(char c : input.toCharArray()) {
            int length = Integer.parseInt(""+c);
            if(currentlyABlock) {
                for(int curlengthPos = 0; curlengthPos < length; curlengthPos++) {
                    blocks.add(currentIndex);
                }
                currentIndex++;
            }else {
                for(int curlengthPos = 0; curlengthPos < length; curlengthPos++) {
                    blocks.add(-1);
                }
            }
            currentlyABlock = !currentlyABlock;
        }

        return blocks;
    }

    /**
     * Given a list of blocks this performs the checksum algorithm
     * @param blocks
     * @return
     */
    public long checksum(LinkedList<Integer> blocks) {
        long checksum = 0;
        for(int inputPos = 0; inputPos < blocks.size(); inputPos++) {
            if(blocks.get(inputPos) != -1L) {
                checksum += inputPos * blocks.get(inputPos);
            }
        }
        return checksum;
    }

    /**
     * Defragments the files to put them all at the start and return the checksum
     * @param input List of file positions
     * @return Checksum
     */
    public long solvePartOne(String input) {
        LinkedList<Integer> blocks = this.createBlockMap(input);

        //Simple linked list with the blockmap, moving everything from the back to the front
        for(int inputPos = 0; inputPos < blocks.size(); inputPos++) {
            if(blocks.get(inputPos) == -1) {
                int lastElement = blocks.removeLast();
                while(lastElement == -1) {
                    lastElement = blocks.removeLast();
                }
                blocks.set(inputPos, lastElement);
            }
        }

        return this.checksum(blocks);
    }


    /**
     * Defragments the files with the new algorithm and return the checksum
     * @param input List of file positions
     * @return Checksum
     */
    public long solvePartTwo(String input) {
        LinkedList<Integer> blocks = this.createBlockMap(input);
        //Add something on the end to make the algorithms nicer "ending" pieces
        blocks.add(-1);

        Map<Integer, Pair<Integer>> blockLocations = new HashMap<>();
        LinkedList<Pair<Integer>> spaces = new LinkedList<>();

        int processingBlock = -1;

        //Find where all the blocks are
        int currentBlock = -1;
        int currentBlockStartIndex = -1;
        for(int scanIndex = 0; scanIndex < blocks.size(); scanIndex++) {
            if(currentBlock == -1 && blocks.get(scanIndex) != -1L) {
                currentBlock = blocks.get(scanIndex).intValue();

                currentBlockStartIndex = scanIndex;
            }else if(currentBlock != -1 && blocks.get(scanIndex) != currentBlock) {
                blockLocations.put(currentBlock, new Pair<>(currentBlockStartIndex, scanIndex - currentBlockStartIndex));
                currentBlock = blocks.get(scanIndex).intValue();
                currentBlockStartIndex = scanIndex;
            }
            //Not really a fan of this!
            processingBlock = Math.max(currentBlock, processingBlock);
        }

        //TODO: merge the two loops?
        //Find where all the space is
        int currentSpaceStart = -1;
        for(int scanIndex = 0; scanIndex < blocks.size(); scanIndex++) {
            int thisBlock = blocks.get(scanIndex).intValue();
            if(currentSpaceStart == -1 && thisBlock == -1) {
                currentSpaceStart = scanIndex;
            }else if(currentSpaceStart != -1 && thisBlock != -1) {
                spaces.add(new Pair<>(currentSpaceStart, scanIndex - currentSpaceStart));
                currentSpaceStart = -1;
            }
        }

        //Process each block in reverse order and move it to the first free space
        while(processingBlock >= 0L) {
            Pair<Integer> blockDetails = blockLocations.get(processingBlock);
            int blockStartIndex = blockDetails.a;
            int blockSize = blockDetails.b;

            for(int spaceIndex = 0; spaceIndex < spaces.size(); spaceIndex++) {
                //Make sure we don't try and move something backwards and not forward
                Pair<Integer> thisSpace = spaces.get(spaceIndex);
                if(thisSpace.a > blockStartIndex) {
                    break;
                }

                //If there is space, put it here and update the spaces field
                if(thisSpace.b >= blockSize) {
                    for(int blockElementsToCopy = 0; blockElementsToCopy < blockSize; blockElementsToCopy++) {
                        blocks.set(thisSpace.a + blockElementsToCopy, blocks.get(blockStartIndex + blockElementsToCopy));
                        blocks.set(blockStartIndex + blockElementsToCopy, -1);
                    }

                    thisSpace.a += blockSize;
                    thisSpace.b -= blockSize;

                    if(thisSpace.b == 0) {
                        spaces.remove(spaceIndex);
                    }
                    break;
                }

            }

            processingBlock--;
        }

        return this.checksum(blocks);
    }

    public static void main(String[] args) {
        String input = ProblemLoader.loadProblemIntoString(2024, 9);

        Day9 d = new Day9();
        long partOne = d.solvePartOne(input);
        System.out.println("The checksum after defragmentation is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The checksum after changing how we defragment is " + partTwo);
    }
}


