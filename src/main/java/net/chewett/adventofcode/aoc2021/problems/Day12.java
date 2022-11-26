package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.*;

/**
 * --- Day 12: Passage Pathing ---
 * With your submarine's subterranean subsystems subsisting suboptimally, the only way you're getting out of this cave
 * anytime soon is by finding a path yourself. Not just a path - the only way to know if you've found the best path is
 * to find all of them.
 *
 * Fortunately, the sensors are still mostly working, and so you build a rough map of the remaining caves (your puzzle
 * input). For example:
 *
 * start-A
 * start-b
 * A-c
 * A-b
 * b-d
 * A-end
 * b-end
 * This is a list of how all of the caves are connected. You start in the cave named start, and your destination is the
 * cave named end. An entry like b-d means that cave b is connected to cave d - that is, you can move between them.
 *
 * So, the above cave system looks roughly like this:
 *
 *     start
 *     /   \
 * c--A-----b--d
 *     \   /
 *      end
 * Your goal is to find the number of distinct paths that start at start, end at end, and don't visit small caves more
 * than once. There are two types of caves: big caves (written in uppercase, like A) and small caves (written in
 * lowercase, like b). It would be a waste of time to visit any small cave more than once, but big caves are large
 * enough that it might be worth visiting them multiple times. So, all paths you find should visit small caves at most
 * once, and can visit big caves any number of times.
 *
 * Given these rules, there are 10 paths through this example cave system:
 *
 * start,A,b,A,c,A,end
 * start,A,b,A,end
 * start,A,b,end
 * start,A,c,A,b,A,end
 * start,A,c,A,b,end
 * start,A,c,A,end
 * start,A,end
 * start,b,A,c,A,end
 * start,b,A,end
 * start,b,end
 * (Each line in the above list corresponds to a single path; the caves visited by that path are listed in the order
 * they are visited and separated by commas.)
 *
 * Note that in this cave system, cave d is never visited by any path: to do so, cave b would need to be visited twice
 * (once on the way to cave d and a second time when returning from cave d), and since cave b is small, this is not
 * allowed.
 *
 * Here is a slightly larger example:
 *
 * dc-end
 * HN-start
 * start-kj
 * dc-start
 * dc-HN
 * LN-dc
 * HN-end
 * kj-sa
 * kj-HN
 * kj-dc
 * The 19 paths through it are as follows:
 *
 * start,HN,dc,HN,end
 * start,HN,dc,HN,kj,HN,end
 * start,HN,dc,end
 * start,HN,dc,kj,HN,end
 * start,HN,end
 * start,HN,kj,HN,dc,HN,end
 * start,HN,kj,HN,dc,end
 * start,HN,kj,HN,end
 * start,HN,kj,dc,HN,end
 * start,HN,kj,dc,end
 * start,dc,HN,end
 * start,dc,HN,kj,HN,end
 * start,dc,end
 * start,dc,kj,HN,end
 * start,kj,HN,dc,HN,end
 * start,kj,HN,dc,end
 * start,kj,HN,end
 * start,kj,dc,HN,end
 * start,kj,dc,end
 * Finally, this even larger example has 226 paths through it:
 *
 * fs-end
 * he-DX
 * fs-he
 * start-DX
 * pj-DX
 * end-zg
 * zg-sl
 * zg-pj
 * pj-he
 * RW-he
 * fs-DX
 * pj-RW
 * zg-RW
 * start-pj
 * he-WI
 * zg-he
 * pj-fs
 * start-RW
 * How many paths through this cave system are there that visit small caves at most once?
 *
 * --- Part Two ---
 * After reviewing the available paths, you realize you might have time to visit a single small cave twice.
 * Specifically, big caves can be visited any number of times, a single small cave can be visited at most twice, and
 * the remaining small caves can be visited at most once. However, the caves named start and end can only be visited
 * exactly once each: once you leave the start cave, you may not return to it, and once you reach the end cave, the
 * path must end immediately.
 *
 * Now, the 36 possible paths through the first example above are:
 *
 * start,A,b,A,b,A,c,A,end
 * start,A,b,A,b,A,end
 * start,A,b,A,b,end
 * start,A,b,A,c,A,b,A,end
 * start,A,b,A,c,A,b,end
 * start,A,b,A,c,A,c,A,end
 * start,A,b,A,c,A,end
 * start,A,b,A,end
 * start,A,b,d,b,A,c,A,end
 * start,A,b,d,b,A,end
 * start,A,b,d,b,end
 * start,A,b,end
 * start,A,c,A,b,A,b,A,end
 * start,A,c,A,b,A,b,end
 * start,A,c,A,b,A,c,A,end
 * start,A,c,A,b,A,end
 * start,A,c,A,b,d,b,A,end
 * start,A,c,A,b,d,b,end
 * start,A,c,A,b,end
 * start,A,c,A,c,A,b,A,end
 * start,A,c,A,c,A,b,end
 * start,A,c,A,c,A,end
 * start,A,c,A,end
 * start,A,end
 * start,b,A,b,A,c,A,end
 * start,b,A,b,A,end
 * start,b,A,b,end
 * start,b,A,c,A,b,A,end
 * start,b,A,c,A,b,end
 * start,b,A,c,A,c,A,end
 * start,b,A,c,A,end
 * start,b,A,end
 * start,b,d,b,A,c,A,end
 * start,b,d,b,A,end
 * start,b,d,b,end
 * start,b,end
 * The slightly larger example above now has 103 paths through it, and the even larger example now has 3509 paths
 * through it.
 *
 * Given these new rules, how many paths through this cave system are there?
 */
public class Day12 {

    /**
     * Calculcate all the paths through all the caves, the allowedDuplicate cave is allowed to be visited twice
     * If no caves are allowed to be visited twice, passing in any nonsensical string (such as ALLOW_NO_DUPLICATES) will
     * effectively make it so that all small caves can only be visited twice
     * @param allPaths All paths and locations that we can go to/from
     * @param allowedDuplicate Small cave we can visit twice, pass in a nonsensical string to allow no small caves to be visited twice
     * @return
     */
    private Set<String> findAllPathsThroughTheCave(Map<String, List<String>> allPaths, String allowedDuplicate) {
        List<List<String>> finishedPaths = new ArrayList<>();
        //Keep track of all paths to visit and keep adding to this throughout the investigation
        Queue<List<String>> pathsToInvestigate = new LinkedList<>();
        List<String> initialPath = new ArrayList<>();
        //Start at Start!
        initialPath.add("start");
        pathsToInvestigate.add(initialPath);

        while(pathsToInvestigate.size() > 0) {
            //Pop the next path to investigate
            List<String> currentPath = pathsToInvestigate.poll();
            String currentLocation = currentPath.get(currentPath.size() - 1);
            List<String> nextLocations = allPaths.get(currentLocation);
            //Find all the next locations to try
            for(String nextLocation : nextLocations) {
                List<String> newRoute = new ArrayList<>(currentPath);
                //If this reaches the end, then we stop and add this to the finished routes
                if(nextLocation.equals("end")) {
                    newRoute.add(nextLocation);
                    finishedPaths.add(newRoute);
                }else{
                    //Small cave, check to make sure we haven't visited it
                    if(nextLocation.equals(nextLocation.toLowerCase())) {
                        boolean alreadyVisited = newRoute.contains(nextLocation);
                        if(nextLocation.equals(allowedDuplicate)) {
                            int frequency = Collections.frequency(newRoute, nextLocation);
                            alreadyVisited = frequency > 1;
                        }

                        //If we haven't visited it more than the allowed number of times, its a valid "next investigation"
                        if(!alreadyVisited) {
                            newRoute.add(nextLocation);
                            pathsToInvestigate.add(newRoute);
                        }
                    }else{
                        //Large cave, we can keep going back and forth so that's fine, add it to the list
                        newRoute.add(nextLocation);
                        pathsToInvestigate.add(newRoute);
                    }
                }
            }
        }

        //Put the paths into a set for use with part 2
        Set<String> allFoundPaths = new HashSet<>();
        for(List<String> path : finishedPaths) {
            allFoundPaths.add(String.join(",", path));
        }
        return allFoundPaths;
    }

    /**
     * Creates a mapping with a list of locations you can move to from a given location
     * @param paths List of movement points from A to B
     * @return Map of strings representing the current location and then a List of strings you can move to from there
     */
    private Map<String, List<String>> convertPathsToMapping(List<String> paths) {
        Map<String, List<String>> allPaths = new HashMap<>();

        for(String path : paths) {
            String[] parts = path.split("-");
            String pointOne = parts[0];
            String pointTwo = parts[1];

            if(!allPaths.containsKey(pointOne)) {
                allPaths.put(pointOne, new ArrayList<>());
            }
            allPaths.get(pointOne).add(pointTwo);
            if(!allPaths.containsKey(pointTwo)) {
                allPaths.put(pointTwo, new ArrayList<>());
            }
            allPaths.get(pointTwo).add(pointOne);
        }

        return allPaths;
    }

    /**
     * Attempts to find the number of paths if you can only visit small caves once
     * @param paths List of all the paths you can move through the cave on
     * @return The number of distinct paths through the caves
     */
    public long solvePartOne(List<String> paths) {
        Map<String, List<String>> allPaths = this.convertPathsToMapping(paths);

        Set<String> foundPaths = this.findAllPathsThroughTheCave(allPaths, "ALLOW_NO_DUPLICATES");
        return foundPaths.size();
    }

    /**
     * Attempts to find the number of paths if you can only visit small caves once, or visit one twice
     * @param paths List of all the paths you can move through the cave on
     * @return The number of distinct paths through the caves
     */
    public long solvePartTwo(List<String> paths) {
        Map<String, List<String>> allPaths = this.convertPathsToMapping(paths);

        //Start with the initial set where there are no duplicates allowed
        //And keep adding items into this set to maintain the lack of duplicates (as many will be generated)
        Set<String> allPathsEver = new HashSet<>(this.findAllPathsThroughTheCave(allPaths, "ALLOW_NO_DUPLICATES"));
        for(Map.Entry<String, List<String>> pathMapping : allPaths.entrySet()) {
            String node = pathMapping.getKey();
            //And then for all small caves we also add the ability to visit that cave twice
            if(node.equals(node.toLowerCase()) && !node.equals("start") && !node.equals("end")) {
                allPathsEver.addAll(this.findAllPathsThroughTheCave(allPaths, node));
            }
        }

        return allPathsEver.size();
    }

    public static void main(String[] args) {
        List<String> paths = ProblemLoader.loadProblemIntoStringArray(2021, 12);

        Day12 d = new Day12();
        long partOne = d.solvePartOne(paths);
        System.out.println("How many paths through the system visiting small caves at most once " + partOne);

        long partTwo = d.solvePartTwo(paths);
        System.out.println("How many paths through the system visiting small caves at most once except one which you could visit twice  " + partTwo);
    }

}
