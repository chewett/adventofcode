package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.aoc2022.AoC2022Directory;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.*;

/**
 * --- Day 7: No Space Left On Device ---
 * You can hear birds chirping and raindrops hitting leaves as the expedition proceeds. Occasionally, you can even hear
 * much louder sounds in the distance; how big do the animals get out here, anyway?
 *
 * The device the Elves gave you has problems with more than just its communication system. You try to run a system
 * update:
 *
 * $ system-update --please --pretty-please-with-sugar-on-top
 * Error: No space left on device
 * Perhaps you can delete some files to make space for the update?
 *
 * You browse around the filesystem to assess the situation and save the resulting terminal output (your puzzle input).
 * For example:
 *
 * $ cd /
 * $ ls
 * dir a
 * 14848514 b.txt
 * 8504156 c.dat
 * dir d
 * $ cd a
 * $ ls
 * dir e
 * 29116 f
 * 2557 g
 * 62596 h.lst
 * $ cd e
 * $ ls
 * 584 i
 * $ cd ..
 * $ cd ..
 * $ cd d
 * $ ls
 * 4060174 j
 * 8033020 d.log
 * 5626152 d.ext
 * 7214296 k
 * The filesystem consists of a tree of files (plain data) and directories (which can contain other directories or
 * files). The outermost directory is called /. You can navigate around the filesystem, moving into or out of
 * directories and listing the contents of the directory you're currently in.
 *
 * Within the terminal output, lines that begin with $ are commands you executed, very much like some modern computers:
 *
 * cd means change directory. This changes which directory is the current directory, but the specific result depends on
 * the argument:
 * cd x moves in one level: it looks in the current directory for the directory named x and makes it the current
 * directory.
 * cd .. moves out one level: it finds the directory that contains the current directory, then makes that directory the
 * current directory.
 * cd / switches the current directory to the outermost directory, /.
 * ls means list. It prints out all of the files and directories immediately contained by the current directory:
 * 123 abc means that the current directory contains a file named abc with size 123.
 * dir xyz means that the current directory contains a directory named xyz.
 * Given the commands and output in the example above, you can determine that the filesystem looks visually like this:
 *
 * - / (dir)
 *   - a (dir)
 *     - e (dir)
 *       - i (file, size=584)
 *     - f (file, size=29116)
 *     - g (file, size=2557)
 *     - h.lst (file, size=62596)
 *   - b.txt (file, size=14848514)
 *   - c.dat (file, size=8504156)
 *   - d (dir)
 *     - j (file, size=4060174)
 *     - d.log (file, size=8033020)
 *     - d.ext (file, size=5626152)
 *     - k (file, size=7214296)
 * Here, there are four directories: / (the outermost directory), a and d (which are in /), and e (which is in a).
 * These directories also contain files of various sizes.
 *
 * Since the disk is full, your first step should probably be to find directories that are good candidates for deletion.
 * To do this, you need to determine the total size of each directory. The total size of a directory is the sum of the
 * sizes of the files it contains, directly or indirectly. (Directories themselves do not count as having any intrinsic
 * size.)
 *
 * The total sizes of the directories above can be found as follows:
 *
 * The total size of directory e is 584 because it contains a single file i of size 584 and no other directories.
 * The directory a has total size 94853 because it contains files f (size 29116), g (size 2557), and h.lst (size 62596),
 * plus file i indirectly (a contains e which contains i).
 * Directory d has total size 24933642.
 * As the outermost directory, / contains every file. Its total size is 48381165, the sum of the size of every file.
 * To begin, find all of the directories with a total size of at most 100000, then calculate the sum of their total
 * sizes. In the example above, these directories are a and e; the sum of their total sizes is 95437 (94853 + 584).
 * (As in this example, this process can count files more than once!)
 *
 * Find all of the directories with a total size of at most 100000. What is the sum of the total sizes of those
 * directories?
 *
 * --- Part Two ---
 * Now, you're ready to choose a directory to delete.
 *
 * The total disk space available to the filesystem is 70000000. To run the update, you need unused space of at least
 * 30000000. You need to find a directory you can delete that will free up enough space to run the update.
 *
 * In the example above, the total size of the outermost directory (and thus the total amount of used space) is
 * 48381165; this means that the size of the unused space must currently be 21618835, which isn't quite the 30000000
 * required by the update. Therefore, the update still requires a directory with total size of at least 8381165 to be
 * deleted before it can run.
 *
 * To achieve this, you have the following options:
 *
 * Delete directory e, which would increase unused space by 584.
 * Delete directory a, which would increase unused space by 94853.
 * Delete directory d, which would increase unused space by 24933642.
 * Delete directory /, which would increase unused space by 48381165.
 * Directories e and a are both too small; deleting them would not free up enough space. However, directories d and /
 * are both big enough! Between these, choose the smallest: d, increasing unused space by 24933642.
 *
 * Find the smallest directory that, if deleted, would free up enough space on the filesystem to run the update. What
 * is the total size of that directory?
 */
public class Day7 {

    /**
     * Take the dataset and then process it into a tree with a root element /
     * @param lines Input data to parse and convert to a directory structure
     * @return The root directory with all its sub folders/files inside of it
     */
    private AoC2022Directory parseDataset(List<String> lines) {
        //Start with the root
        AoC2022Directory root = new AoC2022Directory("/", null);
        AoC2022Directory currentDir = root;

        //Begin processing the data
        for(int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);

            // $ is a command
            if(line.startsWith("$")) {
                if (line.equals("$ ls")) {
                    //For this parser, ls we do nothing since the other command is cd
                }else if(line.startsWith("$ cd")) {
                    //Move either up a directory with .. or into one of the directories
                    //This assumes all CD calls will be valid, so would crash if you cd'ed to a non-existent dir
                    String cdDir = line.split(" ")[2];
                    if (cdDir.equals("..")) {
                        currentDir = currentDir.getParent();
                    } else {
                        currentDir = currentDir.getDir(cdDir);
                    }
                }
            //if its not a command, then ls preceded it, so just start parsing data
            }else{
                String[] parts = line.split(" ");
                //Split on whether its a directroy or file and handle that
                if(parts[0].equals("dir")) {
                    currentDir.addDir(new AoC2022Directory(parts[1], currentDir));
                }else{
                    currentDir.addFile(parts[1], Integer.parseInt(parts[0]));
                }
            }
        }

        //Here we are making no more directory changes so we just calculate the sizes once
        //This means we don't need to keep iteratively calculating size and is much more efficient
        root.cacheSize();

        return root;
    }


    /**
     * Work out the directory structure from the input and then find the total size of all directories smaller than
     * 100000
     * @param lines Command input to process
     * @return The total size of all directories smaller than 100000
     */
    public int solvePartOne(List<String> lines) {
        AoC2022Directory root = this.parseDataset(lines);

        //Loop over all the directories and check if their size is below the number.
        //Its a bit naive since actually as soon as one directory is under the number all children will be
        //So you could just sum all children at that point, but I like the recursive implementation here
        Queue<AoC2022Directory> dirsToCheck = new LinkedList<>();
        dirsToCheck.add(root);

        int totalSize = 0;
        while(dirsToCheck.size() > 0) {
            AoC2022Directory dirToHandle = dirsToCheck.poll();
            //Check to see if this is smaller than the number
            if(dirToHandle.getSize() <= 100000) {
                totalSize += dirToHandle.getSize();
            }

            //And then add its children to the list of things to check
            Map<String, AoC2022Directory> dirsToAdd = dirToHandle.getDirs();
            for(Map.Entry<String, AoC2022Directory> e : dirsToAdd.entrySet()) {
                dirsToCheck.add(e.getValue());
            }
        }

        return totalSize;
    }

    /**
     * Work out the directory structure from the input and then find the smallest directory that once we have deleted
     * it will mean there is more than 30000000 space
     * @param lines Command input to process
     * @return The size of the smallest directory so that once it has been deleted there will be more than 30000000 space
     */
    public int solvePartTwo(List<String> lines) {
        AoC2022Directory root = this.parseDataset(lines);

        //Just do some maths to work out what size we need to free up
        int maxSpace = 70000000;
        int currentSpace = root.getSize();
        int freeSpace = maxSpace - currentSpace;
        int spaceToFree = 30000000 - freeSpace;

        int currentSmallestSize = root.getSize();

        //Start a similar iterative/recursive process to work out what directory we can handle
        Queue<AoC2022Directory> dirsToCheck = new LinkedList<>();
        dirsToCheck.add(root);
        while(dirsToCheck.size() > 0) {
            AoC2022Directory dirToHandle = dirsToCheck.poll();
            //See whether this directory is larger than the space we need to free but smaller than the current
            //directory we are planning to delete
            if(dirToHandle.getSize() > spaceToFree && dirToHandle.getSize() < currentSmallestSize) {
                currentSmallestSize = dirToHandle.getSize();
            }

            //Add all the children for the recursive step
            Map<String, AoC2022Directory> dirsToAdd = dirToHandle.getDirs();
            for(Map.Entry<String, AoC2022Directory> e : dirsToAdd.entrySet()) {
                dirsToCheck.add(e.getValue());
            }
        }

        return currentSmallestSize;
    }

    public static void main(String[] args) {
        List<String> assignments = ProblemLoader.loadProblemIntoStringArray(2022, 7);

        Day7 d = new Day7();
        int partOne = d.solvePartOne(assignments);
        System.out.println("The sum of all directories under size 100000 is " + partOne);

        int partTwo = d.solvePartTwo(assignments);
        System.out.println("The smallest directory I can delete to make room for the update is of size " + partTwo);
    }



}
